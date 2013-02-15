/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import java.net.*;
import java.io.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**
 *
 * @author marcreinke
 */
public class Server {

   
    public void configure(String host, String dbname, String dbuser, String password){

        Properties props = new Properties();
       try {
    		//set the properties value
                props.setProperty("dbhost", host);
    		props.setProperty("database", dbname);
    		props.setProperty("dbuser", dbuser);
    		props.setProperty("dbpassword", password);
 
    		//save properties to project root folder
    		props.store(new FileOutputStream("config.properties"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
        
                
    
    }
    
    public static void main(String[] args) {
       Server confInst = new Server();
       confInst.configure();
       
    }

    private ServerSocket server1= new ServerSocket(1234);
    private Socket client;
    private InputStream input;
    private OutputStream output;

public Server() throws IOException {
    Socket client=server1.accept();
    InputStream input=client.getInputStream();
    OutputStream output=client.getOutputStream();
    
}
    


