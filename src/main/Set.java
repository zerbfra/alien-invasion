//	Set.java
// Configurazione

package main;

/**
 * Interfaccia che contiene tutte le costanti di gioco.
 * Contiene anche alcune dichiarazioni di metodi richiamati dall'esterno per Partita.java
 * @author Francesco Zerbinati
*/
public interface Set {
	
	// CAMPO DI GIOCO E FINESTRA
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int TOP = 40;
	public static final int FOOT = 415;
	
	public static final int VELOCITA_GIOCO = 40;
	
	// PROPRIETA' DEL PLAYER
	
	public static final int MAX_VITA = 200;
	public static final int MAX_BOMBE = 3;
	public static final int V_GIOCATORE = 5;
	public static final int T_SCUDO = 2000;
	public static final int N_SCUDI= 2;
	
	// PROPRIETA' ARMI PLAYER
	
	public static final int V_MISSILE=10;
	public static final int T_FIRE=200;
	public static final int V_BOMBE=10;
	
	// PROPRIETA' DEI MOSTRI
	
	
	public static final int N_ALIENI = 20;
	public static final int H_ALIENI = 50;
	public static final int V_LASER = 6;
	public static final double F_LASER = 0.04;
	
	
	public LoadImg getimg();
	public void addAnimato(Animato a); 
	public Giocatore getGiocatore();
	
	public void fine();
	
}
