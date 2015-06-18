//	Animato.java
//	La classe che definisce tutti gli elementi in movimento

package main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Oggetto che gestisce tutti gli elementi animati
 * @author Francesco Zerbinati
*/
public class Animato {
	
	protected int x,y;
	protected int width, height;
	protected String nomeimg;
	
	protected Set set;
	protected LoadImg img;
	
	protected boolean rimosso; // booleano che indica se deve essere disegnato l'Animato
	
	/**Costruttore della classe
	 * 
	 * @param Setup
	 */
	public Animato(Set set) {
		this.set = set;
		img = set.getimg();
	}
	
	/**Rimuove  oggetti animati dallo schermo*/
	public void rimuovi() {
		rimosso= true;
	}
	/**Fornisce a metodi esterni la variabile "rimosso" in modo che si possa sapere se 
	 * un elemento è da ridesegnare o meno
	 * */
	public boolean daRimuovere() {
		return rimosso;
	}
	/**Disegna le immagini con il getimg
	 * @see <LoadImhg.java>
	 * @param g
	 */
	public void paint (Graphics2D g) {
		g.drawImage( img.getimg(nomeimg),x,y,null);
		
	}
	
	/**Setta la coordinata x di un oggetto*/
	public void setX(int i) { x=i; }
	/**Setta la coordinata y di un oggetto*/
	public void setY(int i) { y=i; }
	

	/**Richiede l'immmagine di nome "nomeimg" e passa
	 * il parametro a getimg che si occupa di caricare
	 * l'immagine se non è ancora stata caricata nell'hashmap "immagini"
	 * @param string
	 */
	public void setNomeImg(String nomeimg) {
			
			this.nomeimg=nomeimg;
			BufferedImage image = img.getimg(nomeimg);
			height= image.getHeight();
			width = image.getWidth();
		
		
	}
	
	/**Fornisce l'altezza di un oggetto*/
	public int getHeight() {return height; }
	/**Fornisce la larghezza di un oggetto*/
	public int getWidth() {return width; }
	
	
	/**Gestisce le azioni delle animazioni*/
	public void azione() {} 
	
	/** Disegna un rettangolo attorno all'immagine per controllare collisioni*/
	public Rectangle getLimiti() {
		return new Rectangle(x,y,width,height);
	}
	
	/**Gestisce lo scontro sulle classi che ereditano*/
	public void scontro(Animato a) {}
	
}
