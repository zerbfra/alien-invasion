//SuperFire.java
// Classe del doppio colpo
package main;

/**Oggetto che gestisce il doppio colpo.
 * 
 * @author Francesco Zerbinati
 */
public class SuperFire extends Animato{
	
	/**Costruttore del colpo doppio. ne setta coordinate iniziali
	 * in base alla variabile int nm passata (primo missile e secondo missile).
	 * Setta l'immagine.
	 * @param set
	 * @param nm
	 * @param g
	 */
	public SuperFire(Set set,int nm,Giocatore g) {
		
		super(set);

		x=g.x+15;
		switch(nm) {
		case 0: y=g.y+3; break;
		case 1: y=g.y+27; break;
		}
		
		setNomeImg("superfire.gif");
		
	}
	/**Tramite ereditarietˆ modifica il metodo azione per muovere i missili*/
	public void azione() {
		x+=Set.V_MISSILE;
		if(x>Set.WIDTH) rimuovi();
	}	

}
