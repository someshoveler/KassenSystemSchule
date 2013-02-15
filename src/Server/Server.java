/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import java.io.*;
import java.net.*;
import java.util.Properties;
/**
 *
 * @author marcreinke
 */
public class Server {
    private ServerSocket server= new ServerSocket(1234);
    private Socket client;
    private InputStream input;
    private OutputStream output;	
    private boolean listening;
    
    public Server()  throws IOException {
			
		server = new ServerSocket(1234);
		System.out.println("Now I am listening!");
		listening = true;
		
	}
    
    public void configure(){
        Properties props = new Properties();
       try {
    		//set the properties value
    		props.setProperty("database", "localhost");
    		props.setProperty("dbuser", "mkyong");
    		props.setProperty("dbpassword", "password");
 
    		//save properties to project root folder
    		props.store(new FileOutputStream("config.properties"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
        
                
    
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Server server = new Server();
			server.run();
		} catch (IOException e) {
			System.out.println(e);
		}
		

	}


    private void run() {
				
	while (listening) {
			
            try {
	        // Solange der Server nicht beendet wurde
			while (listening) {
	        	// warte auf eingehende Verbindung
	        	// wenn Verbindung kommt, wird ein Socket durch accept erzeugt
	        	    Socket clientSocket = server.accept();
	        	    // Erzeuge einen neuen Thread, der sich um die Verbindung kümmert
	        		WorkerThread myWorker = new WorkerThread(clientSocket,this);
	        		// Starte den WorkerThread
	        		myWorker.start();
	        		
	        		System.out.println("Client erfolgreich dem Thread zugeordnet!");
	        	}
	        }
			 catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
}
