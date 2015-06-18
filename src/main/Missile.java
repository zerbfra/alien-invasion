// Missile.Java
// Classe dei proiettili

package main;

/**
 * Oggetto dell'arma principale del giocatore
 * @author Francesco Zerbinati
*/
public class Missile extends Animato {

	/**Costruttore della classe, setta coordinate iniziali in base a 
	 * quelle del giocatore, setta immagine. 
	 * @param set
	 * @param g
	 */
	public Missile(Set set,Giocatore g) {
		super(set);
		
		setX(g.x+g.getWidth());
		setY(g.y+g.getHeight()/2-getHeight()/2);
		setNomeImg("missile.gif");
	}
	
	/**Per ereditˆ da Animato esegue il movimento*/
	public void azione() {
		x+=Set.V_MISSILE;
		if(x>Set.WIDTH) rimuovi();
	}	

}
