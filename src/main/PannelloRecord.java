// PannelloRecord.java
package main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.*;

/**La classe si occupa di disegnare il pannello per inserire i record.
 * Il pannello è composto da due textbox (una modificabile con il nome e 
 * l'altra non modificabile dove compare il punteggio), due JButton che permettono
 * di inserire il punteggio fatto oppure di visualizzare i 10 punteggi migliori.
 * In basso è presente una textarea dove vengono stampati i records.
 * @author Francesco Zerbinati
 *
 */
public class PannelloRecord extends JPanel implements ActionListener { 
	
	 JButton b,b2; 
	 JTextField txt;
	 JTextField txt2;
	 static JTextArea records;
	 public int score;
	 
	 SalvaFile s=new SalvaFile();
	 
	 /**Costruttore della classe, inserisce tutti gli elementi grafici del pannello*/
	 public PannelloRecord(int score){  
		 super(); 
		 this.score=score; 
		 b = new JButton(" Inserisci "); 
		 b2= new JButton(" Visualizza records ");
		 txt=new JTextField("Nome", 20);
		 txt2=new JTextField(Integer.toString(score),20);
		 txt2.setEditable(false);
		 records= new JTextArea(11, 20);
		 records.setEditable(false);
		 
		 b.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
		 b2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
		 b.setForeground(Color.green);
		 b2.setForeground(Color.green);
		 
		 records.setBackground(Color.black);
		 records.setForeground(Color.green);
		 
		 txt.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		 txt2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		 txt.setBackground(Color.black);
		 txt.setForeground(Color.green);
		 txt2.setBackground(Color.black);
		 txt2.setForeground(Color.red);
	
		 b.addActionListener(this); 
		 b2.addActionListener(this); 
		 add(txt); 
		 add(txt2);
		 add(b);
		 add(b2);
		 add(records);
	 }
	 
	 /**Si occupa di reperire i record tramite la funzione leggi della classe
	  * SalvaFile, poi li scrive nella textarea
	  */
	 public void readFile() { 
		 records.setText("");
		 String testo="";
		 LinkedList<Punteggio> lista=new LinkedList();
		 lista=s.leggi();
		 for(Punteggio rec: lista){
			 testo+=rec.getGiocatore()+"\t\t";
	         testo+=rec.getPunti()+"\n";
	         
	          
	        }
		 
		 records.setText(testo);
	 }

	 /**In base al JButton premuto esegue la funzione associata,
	  * reperita dalla classe SalvaFile.
	  */
	 public void actionPerformed(ActionEvent arg) {
		
		String pressed = arg.getActionCommand();
		JButton bottone=(JButton) arg.getSource();
		
		if (bottone==b) {
			b.setEnabled(false);
			Punteggio p = new Punteggio();
			p.setGiocatore(txt.getText());
			p.setPunti(Integer.parseInt(txt2.getText()));
			s.salva(p);
			readFile();
		} else {
			readFile();	 
		} 		
} 
		
}