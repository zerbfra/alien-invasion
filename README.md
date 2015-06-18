# alien-invasion
Progetto Java, Informatica C, Laurea Triennale

##Introduzione al gioco
 
Il progetto che presento è un gioco scritto in Java che simula una battaglia nello spazio tra la propria navicella e navicelle aliene.
Lo scopo del gioco è quello di distruggere tutte le navi aliene ed ottenere un punteggio sufficientemente alto per entrare nella top ten dei punteggi.
 
Durante il gioco si possono utilizzare per rispondere al fuoco nemico le seguenti:
 
_Armi_
* Colpo singolo
* Doppio Colpo (SuperFire)
* Bombe
_Strumenti di difesa_
* Scudo
 
Durante il gioco la navicella del giocatore può spostarsi nel campo di battaglia sull'asse x e y a piacimento e può in qualsiasi momento attivare i dispositivi di difesa o contrattaccare.
Vi è un numero massimo di scudi utilizzabili cosi come un numero di bombe predefinito.
Il numero di bombe e scudi utilizzato influenza lo score finale cosi come la vita rimanente al termine del gioco.
 
Se la vita del giocatore si annulla si ha il Game Over, se invece il giocatore elimina tutti gli avversari si ha la fine del gioco con il salvataggio del punteggio record.
 
##Aspetti tecnici
 
Il programma è composto dalle seguenti classi:
 
 
Classi per il funzionamento del gioco:
 
·       Partita
o   Oggetto che gestisce l'intero gioco, contiene il main e i principali metodi.
·       Controlli
o   Gestisce l'input da tastiera
·       LoadImg
o   Oggetto che gestisce il caricamento delle immagini.
·       Stato
o   Classe che disegna la barra di stato con le informazioni della partita.
 
 
Classi per il movimento delle immagini:
 
·       Animato
o   Oggetto che gestisce tutti gli elementi animati (tranne il Giocatore che è azionato dall'utente)
 
Classi degli elementi sul campo:
 
·       Giocatore
o   Classe che gestisce il giocatore, riceve gli input, si occupa di sparare, del doppio colpo, dello scudo, della vita del giocatore
 
·       Alieno
o   Oggetto che disegna e gestisce il comportamento degli alieni, ereditando i metodi da Animato
 
Classi delle armi (ereditano comportamenti dalla classe Animato):
 
·       RaggioLaser
o   Oggetto raggio laser degli alieni
·       Bomba
o   Oggetto che gestisce l'arma speciale bomba
·       Missile
o   Oggetto dell'arma principale del giocatore
·       SuperFire
o   Oggetto che gestisce il doppio colpo.
 
Gestione dei records:
 
·       Pannello Record
o   La classe si occupa di disegnare il pannello per inserire i record.
·       Punteggio
o   Questa classe tiene in memoria il punteggio del giocatore durante la partita e viene inoltre utilizzata nel momento in cui il punteggio viene salvato su file binario, usando anche il nome del giocatore.
·       SalvaFile
o   Classe che fornisce i metodo di scrittura e lettura su un file binario.
 
 
 
#Note sulle scelte di sviluppo
 
Nello sviluppo sono state utilizzate:
* Ereditarietà di metodi (presente in tutte le classi degli animati ovvero Alieno, Missile, Bomba, SuperFire)
* Polimorfismo (il metodo azione() delle varie classi degli animati è una forma polimorfa del metodo azione() della classe Animato.java)
 
Le due soluzioni sono risultate molto utili infatti ho ottenuto:
 
1.     Semplificazione radicale del codice
a.     Un'unica lista contente tutti gli oggetti animati
b.     Posso disegnare/azionare (con disegna() e azione()) tutti gli elementi con un unico ciclo, ciclando solo una variabile Animato m su cui vado ad applicare i metodi (che sono poi differenti a seconda dell’entità dell’oggetto che sto considerando)
2.     Facilità di integrazione di nuovi elementi animata elevata (basta creare una nuova classe per aggiungere nuovi tipi di alieni o proiettili)
3.      Facilità di programmazione
 
Ho creato un interfaccia (Set.java) che mi è servita per dichiarare tutte le costanti di gioco, in modo da poter modificare in futuro un qualsiasi parametro come velocità di gioco, vita del giocatore, scudi e bombe a disposizione ecc…
L’interfaccia creata è stata necessaria anche per rendere disponibili alle altre classi metodi proprio di Partita.java come getimg(),addAnimato()….
 
Per il caricamento delle immagini da file ho creato la classe LoadImg.java che dispone di due metodi:
 
1.     load(String nome)
2.     getimg(String nome)
 
