//	Alieno.java
//	Classe che definisce l'oggetto alieno.

package main;

/**
 * Oggetto che disegna e gestisce il comportamento degli alieni,
 * ereditando i metodi da Animato
 * @author Francesco Zerbinati
*/
public class Alieno extends Animato {
	
	private int vy,vx;
	
	/**Costruttore*/
	public Alieno(Set set) {
		super(set);
		setNomeImg("alieno.gif");
	}
	
	/**Gestisce il movimento degli alieni, controlla che non escano dall'area di gioco
	 * (compresa tra TOP e FOOT)
	 * */
	public void azione() {
		y+=vy;
		x+=vx;
		if(y<Set.TOP || y>Set.FOOT) vy=-vy;
		if(x<0 || x>Set.WIDTH) vx=-vx;
		if (Math.random()<Set.F_LASER) fire(this);

	}

	/**Setta la velocitˆ di spostamento*/
	public void setVy(int i) { vy=i; }
	
	public void setVx(int i) {vx=i; }
	
	
	/**In caso di collisione con arma del player aggiunge score 
	 * al giocatore e rimuove l'alieno e il missile
	 */
	public void scontro(Animato a) {
		if(a instanceof Missile || a instanceof Bomba || a instanceof SuperFire) {
			rimuovi();
			a.rimuovi();
			set.getGiocatore().punti.add(20);
		}

	}

	/**Metodo per far sparare l'alieno*/	
	public void fire(Alieno a) {
		RaggioLaser m = new RaggioLaser(set,a);
		set.addAnimato(m);
	}

}
