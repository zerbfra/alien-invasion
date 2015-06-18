// Controlli.java
// Gestisce l'input da tastiera

package main;

import java.awt.event.*;

/**Gestisce l'input da tastiera
 * @author Francesco Zerbinati
 */
public class Controlli implements KeyListener{
	
	private boolean up,down,left,right,start;
	private int vx;
	private int vy;
	private int velox;
	private Giocatore giocatore;

	
	/**Il costruttore, avendo in input il giocatore e il set
	 * assegna la velocitˆ del giocatore.
	 * @param g
	 * @param set
	 */
	public Controlli(Giocatore g,Set set) {
		
		giocatore=new Giocatore(set);
		giocatore=g;
		velox = Set.V_GIOCATORE;

	}
	
	/**Switch per gli input da tastiera quando vengono
	 * premuti i tasti.
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		
		boolean fireoff=giocatore.getFireoff();
		switch(e.getKeyCode()) {
		case KeyEvent.VK_DOWN : down =true; break;
		case KeyEvent.VK_UP : up= true; break;
		case KeyEvent.VK_LEFT : left=true; break;
		case KeyEvent.VK_RIGHT: right=true; break;
		case KeyEvent.VK_SPACE: if(!fireoff) giocatore.fire(); break;
		case KeyEvent.VK_D: if(!fireoff) giocatore.doublefire(); break;
		case KeyEvent.VK_B: giocatore.fireBomba(); break;
		case KeyEvent.VK_R: giocatore.addBomba(); break;
		case KeyEvent.VK_S: giocatore.shieldOn(); break;
		case KeyEvent.VK_L: if(giocatore.getVita()<50) giocatore.addVita(100); break; // cheat ;)
		
		}
		
		aggiornaMovimento();
	}
	/**Switch per gli input da tastiera quando vengono
	 * rilasciati i tasti.
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_DOWN : down =false; break;
		case KeyEvent.VK_UP : up= false; break;
		case KeyEvent.VK_LEFT : left=false; break;
		case KeyEvent.VK_RIGHT: right=false; break;
		}
		
		aggiornaMovimento();
	}
	
	/**Aggiorna lo spostamento in conseguenza alle variabili
	 * down,up,left,right
	 */
	protected void aggiornaMovimento() {
		vx=0; vy=0;
		
		if(down) vy= velox;
		if(up) vy= -velox;
		
		if(left) vx=-velox;
		if(right) vx=velox;
		
		giocatore.setMovimenti(vx,vy);
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
