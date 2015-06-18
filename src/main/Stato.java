//Stato.java
//Disegna la barra di stato con le informazioni sulla partita
package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;

/**Classe che disegna la barra di stato con le informazioni della partita.
 * Mostra punti, vita del giocatore, bombe rimanenti, scudi rimanenti.
 * A termine partita fornisce al gioco la funzione drawFine che disegna i punteggi
 * finali sulla schermata di gioco.
 * @author Francesco Zerbinati
 */
public class Stato implements ImageObserver,KeyListener {
	
	Graphics2D g;
	Giocatore giocatore;
	private LoadImg img;
	public boolean start=false;

	
	public Stato(Graphics2D g,Giocatore giocatore,LoadImg img) {
		this.g=g;
		this.giocatore=giocatore;
		this.img=img;
		
	}
	

	/**Disegna la barra di vita del giocatore e la aggiorna in base alle condizioni 
	 * dello scudo (attivato/disattivato)
	 * @param g
	 */
	public void drawVita(Graphics2D g) {
		
		if(giocatore.getScudo()) g.setPaint(Color.green);
		else g.setPaint(Color.blue);
			
		g.fillRect(190,10, giocatore.getVita(), 10); // x,y,lunghezza,altezza
		g.setFont(new Font("Arial",Font.BOLD,15));
		g.setPaint(Color.white);
		g.drawString("Vita ",120,20);
		         
	}
	/** Visualizza il punteggio durante il gioco */
	public void drawPunti(Graphics2D g) {
		
		g.setFont(new Font("Arial",Font.BOLD,15));
		g.setPaint(Color.white);
		g.drawString("Punti: ",20,20);
		g.setPaint(Color.red);
		
		g.drawString(giocatore.getPunti()+"",80,20);
	
	}
	/** Visualizza le ammonizioni speciali rimanenti*/     
	public void drawBombe(Graphics2D g) {
		int xBase = 210+Set.MAX_VITA+10;
		for (int i = 0; i < giocatore.getBombe();i++) {
			BufferedImage bomb = img.getimg("bomba.gif");
			g.drawImage( bomb ,xBase+i*bomb.getWidth(),8,this);
		}
	}
	/** Visualizza gli scudi ancora utilizzabili */
	public void drawScudi(Graphics2D g) {
		
		BufferedImage bomb = img.getimg("bomba.gif"); // si pu˜ far meglio?
		int xBase = 210+Set.MAX_VITA+Set.MAX_BOMBE*bomb.getWidth()+30;
		for (int i = 0; i < giocatore.getNScudi();i++) {
			BufferedImage shield = img.getimg("shield.gif");
			g.drawImage( shield ,xBase+i*(shield.getWidth()+5),9,this);
		}
	}
     
	/**Si occupa di visualizzare paintScore(), paintLife(),paintAmmo(),paintShields(),paintfps*/
	public void drawStato(Graphics2D g) {
		drawPunti(g);
		drawVita(g);
		drawBombe(g);
		drawScudi(g);
		
	}
	/**Metodo che disegna la schermata di introduzione*/
	public void drawstart(Partita partita,int alive,BufferStrategy strategy) {
		BufferedImage logo = img.getimg("logo.png");
		g.drawImage( logo ,20,150,this);
		partita.finestra.addKeyListener(this);
		strategy.show();
	}
	/**Metodo che riceve l'input di invio per iniziare la partita*/
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {	
		case KeyEvent.VK_ENTER: setStart();
		}	
	}
	/**Metodo per settare la variabile di inizio start*/
	public void setStart() {
		start=true;	
	}
	/**Metodo che restituisce la variabile start*/
	public boolean getStart() {
		return start;
	}
	
	/**Visualizza la fine del gioco, in base ai parametri alive e gameEnded stampa
	 * Game Over oppure schermata di vittoria.
	 */
	public void drawfine(Partita partita,int alive,BufferStrategy strategy) {
		
		int final_score;
		g.setColor(Color.white);
		g.setFont(new Font("Verdana",Font.BOLD,20));
		if(alive>0) g.drawString("GAME OVER",Set.WIDTH/2-50,Set.HEIGHT/2);
		else g.drawString("HAI VINTO!",Set.WIDTH/2-50,Set.HEIGHT/2);
		
		final_score= giocatore.getPunti()+giocatore.getVita()*5+giocatore.getNScudi()*10+giocatore.getBombe()*10; 
		g.setFont(new Font("Verdana",Font.BOLD,15));
		g.drawString("Punteggio: "+giocatore.getPunti()+"      Uccisi: "+(Set.N_ALIENI-alive),Set.WIDTH/2-50,Set.HEIGHT/2+25);
		g.drawString("Bonus vita rimanente: "+giocatore.getVita()*5,Set.WIDTH/2-50,Set.HEIGHT/2+60);
		g.drawString("Bonus bombe rimanenti: "+giocatore.getBombe()*10,Set.WIDTH/2-50,Set.HEIGHT/2+80);
		g.drawString("Bonus scudi rimanenti: "+giocatore.getNScudi()*10,Set.WIDTH/2-50,Set.HEIGHT/2+100);
		g.setFont(new Font("Verdana",Font.BOLD,20));
		g.drawString("Punteggio totale: "+final_score,Set.WIDTH/2-50,Set.HEIGHT/2+140);
		
		strategy.show();
		if(final_score>0) partita.openScoreFrame(final_score);
	}

	/**Creato per completare l'estensione ImageObserver*/
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
