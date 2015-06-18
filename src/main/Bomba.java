//	Bomba.java
//	Classe delle bombe

package main;

/**
 * Oggetto che gestisce l'arma speciale bomba
 * @author Francesco Zerbinati
*/
public class Bomba extends Animato {
		
		protected int bombspeed = Set.V_BOMBE;
		protected int vx;
		protected int vy;
		
		/** Costruisce l'oggetto Bomba e ne assegna le coordinate iniziali.
		 * In base al parametro nb ricevuto adegua la direzione dello spostamento.
		 * 
		 * @param Setup
		 * @param nb
		 * @param x
		 * @param y
		 */
		public Bomba(Set set,int nb,Giocatore g) {
			
			super(set);
			
			x=g.x+10;
			y=g.y+10;
			setNomeImg("bomba.gif");
			
			switch(nb) {
			case 0: vx= bombspeed; vy=-bombspeed;break;
			case 1: vx= bombspeed; vy=0 ;break;
			case 2: vx= bombspeed; vy=bombspeed;break;
			}
					
			
		}
		/** Tramite ereditˆ fornisco il metodo azione*/
		public void azione() {
			y+=vy;
			x+=vx;
			if(y<Set.TOP || y>Set.FOOT || x<0 || x>Set.WIDTH) rimuovi();
		}
}
