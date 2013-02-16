/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
import Server.Kasse;
import Server.Kassierer;
import Server.Kunde;
import Server.Ware;
import java.util.Scanner;
import java.net.*;
import java.io.*;

/**
 *
 * @author marcreinke
 */
public class Userpanel {
    private Scanner sc = new Scanner(System.in);
    private Ware ware;
    private Kunde kunde;
    private Kassierer kassierer;
    private Kasse kasse;
    private PrintWriter writer;
    private BufferedReader reader;
    private ObjectInputStream oIn;
    private ObjectOutputStream oOut;
    private int authResult;
    private int securitylevel;
    
public Userpanel() throws IOException {
    System.out.println("Connecting to Server...");
    Socket server=new Socket("127.0.0.1",1234);
    InputStream input=server.getInputStream();
    OutputStream output=server.getOutputStream();
    writer =new PrintWriter(server.getOutputStream(),true);
    reader =new BufferedReader(new InputStreamReader((server.getInputStream())));
    securitylevel= authenticate();
    if (securitylevel == 0) {
        addClient();
        securitylevel = 1;
    }
    else{
        mainMenu();
    }
    
   
    
    
    
    

}

public int authenticate() throws IOException{
    reader.readLine();
    String user= sc.next();
    writer.println(user);
    reader.readLine();
    String password= sc.next();
    writer.println(password);
    authResult = reader.read();
    
    return authResult;
}

public void addArticle (){
    
}

    private void addClient() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void mainMenu() {
        writer.print(0);
        System.out.println("Your Security level: "+securitylevel);
        reader.readLine();
    }
    
    
    
}
