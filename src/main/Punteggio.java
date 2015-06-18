//Punteggio.java
//Classe del punteggio
package main;

import java.io.*;


/**Questa classe tiene in memoria il punteggio del giocatore durante la
 * partita e viene inoltre utilizzata nel momento in cui il punteggio
 * viene salvato su file binario, usando anche il nome del giocatore.
 * @author Francesco Zerbinati
 */
public class Punteggio implements Comparable, Serializable {

	private String giocatore;
	private int punti;
	
	/**Fornisce il nome del giocatore*/
	public String getGiocatore() {
		return giocatore;
	}
	
	/**Imposta il nome del giocatore*/
	public void setGiocatore(String nome) {
		giocatore = nome;
	}

	/**Restituisce il punteggio*/
	public int getPunti() {
		return punti;
	}

	/**Imposta il punteggio a un valore*/
	public void setPunti(int punti) {
		this.punti = punti;
	}
	
	/**Aggiunge punti al punteggio esistente.
	 * Utilizzato durante il gioco.
	 * @param punti
	 */
	public void add(int punti) {
		this.punti+=punti;
	}

	/**Metodo per confrontare due punteggi, per stabilire
	 * il maggiore e il minore, serve per richiamare il metodo
	 * Collections.sort(), altrimenti non funziona.
	 */
    public  int compareTo(Object o)  {
        Punteggio that = (Punteggio)o;
        if(that.getPunti() < this.getPunti()) return -1; //punteggio in ingresso minore
        if(that.getPunti() > this.getPunti()) return 1; // punteggio in ingresso maggiore
        return 0; // punteggi uguali
    }

}