/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import java.util.Scanner;
import java.net.*;
import java.io.*;

/**
 *
 * @author marcreinke
 */
public class Adminpanel {
    private Scanner sc = new Scanner(System.in);
    private Ware ware;
    private Kunde kunde;
    private Kassierer kassierer;
    private Kasse kasse;
    private PrintWriter writer;
    private BufferedReader reader;
    
public Adminpanel() throws IOException {
    System.out.println("Connecting to Server...");
    Socket server=new Socket("127.0.0.1",1234);
    InputStream input=server.getInputStream();
    OutputStream output=server.getOutputStream();
    writer =new PrintWriter(server.getOutputStream(),true);
    reader =new BufferedReader(new InputStreamReader((server.getInputStream())));
    

}

public void addArtikel (){
    
}
    
    
    
}
