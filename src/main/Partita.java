package main;

import java.util.*;
import java.util.Queue;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;

/**
 * Oggetto che gestisce l'intero gioco, contiene il main e i principali metodi.
 * @author Francesco Zerbinati
*/
public class Partita extends Canvas implements Set { // uso Canvas per usare una BufferStrategy
	
	private LinkedList animati;
	private Giocatore giocatore;
	
	private BufferStrategy strategy;
	private long usedTime;
	
	private LoadImg img=new LoadImg();;
	private BufferedImage bg;
	private int t;	// si occupa di muovere sfondo
		
	private boolean fineGioco=false;
	public boolean start=false;
	private Controlli controlli;
	private Stato stato;
	
	private Alieno a;
	private int n_monsters=Set.N_ALIENI;
	private int alive=Set.N_ALIENI;
	private static int h_monsters=Set.H_ALIENI;
	private int vy,vx;
	public JFrame finestra;
	
	/**Inizializza la finestra di gioco, aggiunge un pannello e setta dimensioni,posizione, crea una 
	 * bufferStrategy che serve per inizializzare una superficie di disegno accelerata dall'hardware video.
	 * Fornisce le funzioni per il ciclo del gioco: inizializzazione-ciclo-fine
	*/
	public Partita() {
		
		finestra = new JFrame("Alien Invasion");
		
		JPanel panel = (JPanel)finestra.getContentPane();
		setBounds(0,0,Set.WIDTH,Set.HEIGHT);
		
		panel.add(this);
		
		finestra.setBounds(60,60,Set.WIDTH,Set.HEIGHT);
		finestra.setVisible(true);
		finestra.setResizable(false);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createBufferStrategy(2);
		// createBufferStrategy 
		strategy = getBufferStrategy();
		
		/**Sono stato costretto ad utilizzare un BufferStrategy(2) poichè se facevo semplicemente, invece di:
		 * Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
		 * un
		 * Graphics2D g = (Graphics2D) getGraphics();
		 * 
		 * ottenevo durante l'animazione solo un fastidioso effetto flickering.
		 * Per questo motivo sono inoltre costretto ad usare un Canvas.
		 */
		
	
	}
	
	/**Aggiunge alieni*/
	public void addAlieno(Set set) {
		
		Alieno a= new Alieno(set);
		a.setX(h_monsters+(int)(Math.random()*(Set.WIDTH-a.getWidth()-h_monsters)));
		a.setY(Set.TOP+(int)(Math.random()*(Set.FOOT-Set.TOP)));
		
		do { 
			vy=( (int)(Math.random()*20-10));
			vx=( (int)(Math.random()*20-10));
		} while(vy==0 && vx==0);
		
		a.setVy(vy);
		a.setVx(vx);
		animati.add(a);
	}
	
	/**Inizializza il mondo di gioco, crea una lista degli elementi che si muovono
	 * sul campo, aggiunge gli avversari, aggiunge il giocatore e ne setta le coordinate default.
	 */
	public void inizializza() {	
		animati=new LinkedList();
		for(int i=0; i<n_monsters; i++)  addAlieno(this);	
		giocatore=new Giocatore(this);	
		controlli=new Controlli(giocatore,this);
		finestra.addKeyListener(controlli);
	}
	
	/**Metodo che aggiunge elementi animati sul campo */
	public void addAnimato(Animato a) {
		animati.add(a);
	}
	
	/**Metodo che fornisce ad altri metodi l'oggetto player*/
	 public Giocatore getGiocatore() {
		 return giocatore;
	 }

	/**Aggiorna il campo di gioco, controlla quali elementi sono da rimuovere, aggiorna il countdown (alive)
	 * per gli avversari ancora vivi
	 */
	public void aggiorna() {
		
		int i=0;
		while(i<animati.size()) {
			
			Animato m=(Animato)animati.get(i);
			if(m.daRimuovere()) { 
				animati.remove(i);  // rimuove l'animato i dalla lista 
				
				if(m instanceof Alieno) {
					alive--; // countdown per vittoria
				}
			}
			else {
				m.azione();
				i++;
			}
			
		}
		giocatore.azione();
	}
	
	/**Controlla se due elementi in movimento si scontrano */
	public void scontri() { 
		
		// Controllo collisione di un elemento con il giocatore
		Rectangle rgiocatore = giocatore.getLimiti();
		for (int i = 0; i < animati.size(); i++) {
			Animato a1 = (Animato)animati.get(i);
			Rectangle r1 = a1.getLimiti();
			if (r1.intersects(rgiocatore)) {
				giocatore.scontro(a1);
				a1.scontro(giocatore);
			}
			
			// Controllo collisione tra mio proiettile e alieni
		  for (int j = i+1; j < animati.size(); j++) {
		  	Animato a2 = (Animato)animati.get(j);
		  	Rectangle r2 = a2.getLimiti();
		  	if (r1.intersects(r2)) {
		  		a1.scontro(a2);
		  		a2.scontro(a1);
		  	}
		  }
		}
	}
	
	/**Setta la variabile fineGioco a true per dichiarare il Game Over */
	public void fine() {
		fineGioco = true;
	}

	/**Apre una nuova finestra dove si può registrare il proprio punteggio*/
	public void openScoreFrame(int final_score) {
		 JFrame f = new JFrame("Record"); 
		 Container c = f.getContentPane(); 
		 
		 PannelloRecord p= new PannelloRecord(final_score);
		 
		 c.add(p); 
		 f.setSize(300,120);  
		 f.setVisible(true);
		 f.setBounds(Set.WIDTH+100,60,300,Set.HEIGHT-160);
		 f.setBackground(Color.black);

	}

	/** Ri-Disegna tutto, raccogliendo le funzioni di disegno dello sfondo animato, dei vari oggetti,
	 * del giocatore, degli stati, include strategy.show()
	 */
	public void disegna(Graphics2D g) {
		
		stato=new Stato(g,giocatore,img);
		bg = img.getimg("stars.gif");
		g.setPaint(new TexturePaint(bg, new Rectangle(-t,0,bg.getWidth(),bg.getHeight())));
		g.fillRect(0,0,getWidth(),getHeight());
		
		for(int i=0; i<animati.size(); i++) {
			Animato m = (Animato)animati.get(i);
			m.paint(g);
		}
		
		
		giocatore.paint(g);
		stato.drawStato(g);
		strategy.show();
	}
	
	/**Metodo per richiamare la SpriteCache dagli altri metodi*/
	public LoadImg getimg() { return img; }

	/**Metodo che controlla tutto il gioco, tiene in loop il gioco finchè non finisce.
	 * Si occupa di disegnare tutte le cose richiamando disegna() e aggiorna() per aggiornarsi.
	 */
	public void run() {
		
		Graphics2D g = (Graphics2D)(strategy.getDrawGraphics());
    	t = 0;
    	boolean pronto;
    	
    	inizializza();
    	disegna(g);
    	stato.drawstart(this,alive,strategy);	
 
    	do {
    		
    		try {
			Thread.sleep(1);
    		} catch (InterruptedException e) {
			e.printStackTrace();
    		}
    		pronto=stato.getStart();;
       	}
    	while(!pronto);
    	
    	while (!fineGioco && alive>0) {
	
    		t++;
    		aggiorna();
    		scontri();
    		disegna(g);
    		
    		try {
    			Thread.sleep(VELOCITA_GIOCO);
    		} catch (InterruptedException e) {}
    	}
    	
    	stato.drawfine(this,alive,strategy);
    	
    }
    
   /**Main che richiama inv.game()*/ 
	public static void main(String[] args) {
		Partita play=new Partita();
		play.run();
	}

}