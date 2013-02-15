/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author marcreinke
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


// Klasse WorkerThread
// kümmert sich um die Verbindung eines Clients

public class WorkerThread extends Thread {

    private Socket socket = null;
    private PrintWriter writer = null;
    private BufferedReader bReader = null;
    private Server s;
    int zahl1;
    int zahl2;

    
    public WorkerThread(Socket socket, Server s) {
	super("WorkerThread");
	this.socket = socket;
	this.s=s;
	
	try {
	    // Setze Reader und Writer für den ClientSocket
		writer = new PrintWriter(socket.getOutputStream(), true);
		bReader = new BufferedReader(
			    new InputStreamReader(
			    socket.getInputStream()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    // Methode run()
    // Startmethode des Threads
    public void run() {  
    	Protocol protocol;
    	String request = "";

	try {
		protocol = new Protocol(this);
	    
	    while(true) {
	    	
            System.out.println("Zustand: "+ protocol.getZustandNr());
            
			try {
				// Warte auf Request des Clients
				while (!bReader.ready());
				request = bReader.readLine();
				System.out.println("In: " + request);
				s.test();
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Frage beim Protokoll, ob der Client-Request gültig ist (abh. vom Zustand des Protokolls)
			if (protocol.isValid(request)) {
				    // wenn gültig --> führe u.U eine Aktion durch
					protocol.performAction(request);
					// schicke dem Client die richtige Antwort zurück (abh. vom Zustand)
					writer.println(protocol.getResponse(request));
					// veranlasse das Protokoll einen Zustandswechel durchzuführen				
					protocol.changeState(request);
			}
			else
			{
				    // Protokollfehler --> Abbruch der Kommunikation durch Verlassen der Schleife
					writer.println(protocol.getError());
					System.out.println("Protokollehler --> Abbruch der Verbindung");
					break;
			}
			
			// Signalisiert das Protokoll einen Abbruch, der durch den USer veranlasst wurde?
			if (protocol.terminate()) {
				System.out.println("User beendet die Unterhaltung! --> Bye");
				break;
			}		
			
		}
	    	
	    // Abräumen des Sockets
	    writer.close();
	    bReader.close();
	    socket.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
   }

	/**
	 *führt die eigentliche Augabe des Servers aus, nämlich 2 Zahlen zu addieren
	 *
	 */
	public int add() {
		return zahl1 + zahl2;
	}
	
	/**
	 * speichert die vom Client erhaltene Zahl in Zahl1
	 */

	public void setZahl1(int zahl) {
		// TODO Auto-generated method stub
		zahl1 = zahl;
		
	}

	/**
	 * speichert die vom Client erhaltene Zahl in Zahl2
	 */

	public void setZahl2(int zahl) {
		// TODO Auto-generated method stub
		zahl2 = zahl;
		
	}

	/**
	 * löscht die vom Client erhaltenen und gespeicherten Zahlen
	 */
	public void reset() {
		// TODO Auto-generated method stub
		zahl1 = 0;
		zahl2 = 0;
		
	}


}