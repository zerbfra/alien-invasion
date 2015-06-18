//	LoadImg.java
//	Si preoccupa di caricare le immagini per poi visualizzare


package main;

import java.net.URL;
import java.awt.image.*;

import javax.imageio.ImageIO;
import java.util.HashMap;

/**
 * Oggetto che gestisce il caricamento delle immagini.
 * Il caricamento viene fatto tramite load che reperisce
 * l'url dell'immagine.
 * In seguito tramite getimg() che viene richiamato da tutte
 * le altre classi per richiedere un immagine va a cercare nell'HashMap
 * l'immagine, se ancora non è stata caricata la carica tramite il metodo
 * load.
 * @author Francesco Zerbinati
*/
public class LoadImg {
	
	public HashMap immagini;
	
	/**Costruttore che crea l'hashmap immagini*/
	public LoadImg() {
		immagini= new HashMap();
	}
	
	/** Carica l'immagine tramite un url e la ritorna*/
	public BufferedImage load (String nome) {
		URL url=null;
		try {
			url= getClass().getClassLoader().getResource(nome); // determina il percorso del nome di file che gli passi in base alla posizione dei file del tuo programma sul filesystem
			return ImageIO.read(url);							// lo fa usando un meccanismo predefinito di Java che è il ClassLoader
		} catch (Exception e) {
			System.out.println("Non si può caricare immagine "+nome);
			return null;
		}
	}
	
	/**Il metodo cerca nell'hashmap immagini (tramite meotodo get) l'immagine con il
	 * nome che è stato passato.
	 * Se l'immagine non è presente la carica tramite il metodo load e la inserisce nell'hashmap 
	 * con il metodo put.
	 * @param nome
	 * @return
	 */
	public BufferedImage getimg(String nome) {
		BufferedImage img=(BufferedImage)immagini.get(nome);
		if(img==null) {
			img=load("res/"+nome);
			immagini.put(nome, img);
		}
		return img;
	}

	
}
