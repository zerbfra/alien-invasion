// Giocatore.java
// Classe del giocatore

package main;

import java.awt.event.KeyEvent;

/**
 * Classe che gestisce il giocatore, riceve gli input, si occupa
 * di sparare, del doppio colpo, dello scudo, della vita del giocatore
 * @author Francesco Zerbinati
*/
public class Giocatore extends Animato {
		

	//public int punti;
	private int vita;
	private int scudi;
	private int bombe;
	
	public Punteggio punti=new Punteggio();
	
	protected int vx,vy;
	
	private boolean shieldon,fireoff;
	private long scudostart,scudostop,scudotime,maxscudotime;
	private long firestart,firestop,firetime,maxfiretime;

	/**Costruttore della classe, carica l'immagine del giocatore,
	 * fissa la velocità di spostamento, la vita, le bombe, lo scudo.
	 * @param Setup
	 */
	public Giocatore(Set set) {
		super(set);
		setX(10);
		setY(Set.HEIGHT/2);
		setNomeImg("nave.gif");
		
		
		vita= Set.MAX_VITA;
		bombe= Set.MAX_BOMBE;
		maxscudotime = Set.T_SCUDO;
		scudi= Set.N_SCUDI;
		maxfiretime= Set.T_FIRE;
		
	}
	
	/**Riceve in input gli spostamenti sull'asse x e y
	 * che la navicella deve fare.
	 * @param vx
	 * @param vy
	 */
	public void setMovimenti(int vx, int vy) {
		this.vx=vx;
		this.vy=vy;
	}
	
	/**Aziona il movimento del giocatore e controlla che la navicella 
	 * non vada al di fuori del campo di gioco, riposizionandola correttamente.
	 * Si preoccupa di controllare se lo scudo è attivo e nel caso lo sia
	 * controlla che non sia passato maxscudotime millisecondi dall'attivazione.
	 * Controlla anche con la variabile fireoff che sia possibile sparare in modo
	 * da evitare la pressione "forsennata" del tasto fuoco da parte dell'utente.
	 */
	public void azione() {
		
		x+=vx;
		y+=vy;
		
		/**Controllo se fuoriesco dai bordi*/
		if(x<0) x=0;
		if(x> Set.WIDTH-getWidth()) x = Set.WIDTH - getWidth();
		if(y< Set.TOP) y=Set.TOP;
		if(y> Set.FOOT) y = Set.FOOT;
		
		/**Controllo sul fuoco*/
		if(fireoff) {
			firestop= System.currentTimeMillis();
			firetime= firestop-firestart;
			if(firetime>=maxfiretime) fireoff=false;	
		}
		
		/**Controllo sullo scudo*/
		if(shieldon) {
			scudostop= System.currentTimeMillis();
			scudotime= scudostop-scudostart;
			if(scudotime>=maxscudotime) shieldon=false;	
		}
	
	}
	

	
	
	/**Attiva lo scudo e controlla che sia possibile
	 * attivarlo.
	 */
	public void shieldOn() {
		
		if(scudi!=0 && !shieldon) {
			scudostart= System.currentTimeMillis();
			shieldon=true;
			scudi--;
		}
				
	}
	
	/**Funzione che disabilita la funzione fire per maxfiretime millisecondi*/
	public void fireOff() {
		
		firestart= System.currentTimeMillis();
		fireoff=true;		
		
	}
	
	/**Funzione richiamata per sparare*/
	public void fire() {
		
		fireOff();
		Missile m= new Missile(set,this);
		set.addAnimato(m);
		
	}
	/**Funzione richiamata per il doppio colpo*/
	public void doublefire() {
		
		fireOff();		
		set.addAnimato( new SuperFire(set,0,this));
		set.addAnimato( new SuperFire(set,1,this));		
	}
	/**Funzione richiamata per sparare le bombe*/
	public void fireBomba() {
		if(bombe == 0) return;
		set.addAnimato( new Bomba(set,0,this));
		set.addAnimato( new Bomba(set,1,this));
		set.addAnimato( new Bomba(set,2,this));
		
		bombe--;	
	}
	
	/**Controlla se la navicella si scontra con un mostro
	 * e in tal caso aggiunge 5 punti allo score ma rimuove 
	 * 25 di vita al giocatore.
	 * Nel caso la navicella riceva un laser viene aggiunta una
	 * penalità e nel caso lo scudo non sia attivo viene aggiunta
	 * un'ulteriore penalità e viene tolto 20 di vita.
	 * Controlla inoltre se la vita è >0, in caso contrario si dichiara
	 * la fine del gioco.
	 */
	public void scontro(Animato a) {
		if (a instanceof Alieno) {
				a.rimuovi();
				punti.add(5);	
				addVita(-25);
		}
		
		
		if (a instanceof RaggioLaser ) {
			a.rimuovi();
			punti.add(-2);
			if(!shieldon) {
				addVita(-20);
				punti.add(-2);
			}
			
		}

		if (getVita() < 0) set.fine();
		
	}	 
	
	
	/**Ritorna agli altri metodi i punti del giocatore,
	 * fa riferimento alla classe Punteggio.
	 * @return
	 */
	public int getPunti() {   return punti.getPunti(); }	
	
	/**Ritorna la vita rimanente
	 * @return life
	 */
	public int getVita() { return vita; }
	
	/**Setta la vita a i
	 * @param i
	 */
	public void setVita(int i) { vita = i;  }
	
	/**Metodo per aggiungere/togliere(con valori negativi)
	 * la vita
	 * @param i
	 */
	public void addVita(int i) {vita+=i;}
	
	/**Ritorna agli altri metodi la variabile fireoff*/
	public boolean getFireoff() {
		return fireoff;
	}
	
	/**Ritorna se lo scudo è attivo o meno*/
	public boolean getScudo() { return shieldon; }
	
	/**Ritorna il numero di scudi ancora attivabili*/
	public int getNScudi() { return scudi; }
	
	/**Ritorna il numero di bombe ancora disponibili
	 * @return bombe
	 */
	public int getBombe() {  return bombe;  }
	
	/**Aggiunge un ulteriore bomba "bonus" solo se sono finite */
	public void addBomba() { if(bombe==0) bombe++; }

}
