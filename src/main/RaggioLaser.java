// RaggioLaser.java
// Classe del laser sparato dagli alieni

package main;

/**
 * Oggetto raggio laser degli alieni
 * @author Francesco Zerbinati
*/
public class RaggioLaser extends Animato {
	
	/**Costruttore di laser: setta posizione iniziale rispetto a
	 * quella dell'alieno che lo spara e ne setta l'immagine*/
	public RaggioLaser(Set set,Alieno a) {
		
		super(set);
		setX(a.x+2);
		setY(a.y + a.getHeight()/2 - getHeight()/2);
		
		setNomeImg("laser.gif");
		

	}
	
	/**Gestisce il movimento del laser*/
	public void azione() {
		x-=Set.V_LASER;
		if (y<0) rimuovi();

	}

}
