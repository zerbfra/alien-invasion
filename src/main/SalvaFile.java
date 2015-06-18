// SalvaFile.java
// Fornisce i metodi di scrittura/lettura su file binario
package main;
import java.io.*;
import java.util.*;

/**Classe che fornisce i metodo di scrittura e lettura su un file binario.
 * Crea una LinkedList di oggetti Punteggio e la salva, tenendo sul file
 * solo i 10 records migliori.
 * @author Francesco Zerbinati
 *
 */
public class SalvaFile {
	
	/**Il metodo salva la lista di punteggi. 
	 * In sequesta esegue le seguenti: 
	 * - legge la lista dal file
	 * - aggiunge alla lista il nuovo punteggio fatto
	 * - ordina la lista in ordine decrescente per punteggio
	 * - Se la dimensione della lista è maggiore di 10, rimuove l'ultimo punteggio ovvero il più basso.
	 * - Salva la nuova lista creata sul file "records.dat"
	 * @param p
	 */

	public void salva(Punteggio p) {
		  
		//creo lista serializzabile   
		LinkedList<Punteggio> lista = new LinkedList();
		lista=leggi();
		lista.add(p);
		Collections.sort(lista);
		if(lista.size()>10) lista.removeLast();

		//Serializzo la lista
		try { 
			OutputStream file = new FileOutputStream("records.dat");
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			try{
				output.writeObject(lista);
			}
			finally{
				output.close();
				output.flush();
			}
		}  
		catch(IOException ex){}
	}
	
	/**Il metodo legge il file dati.
	 * In ordine vengono eseguite le seguenti:
	 * - Viene creata una lista vuota
	 * - Vengono inserite le informazioni del file nella nuova lista creata
	 * - Viene ordinata la lista
	 * - Viene ritornata la lista letta
	 * @return
	 */
	public LinkedList leggi() {

		//deserializzo lista
		LinkedList<Punteggio> lista=new LinkedList();
		 
		try{
			InputStream file = new FileInputStream( "records.dat" );
			InputStream buffer = new BufferedInputStream( file );
			ObjectInput input = new ObjectInputStream ( buffer );
			
			lista = (LinkedList<Punteggio>) input.readObject();
			input.close();
		}
		catch(IOException e) {} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		    
		Collections.sort(lista);
		return lista;
	}
	
}
