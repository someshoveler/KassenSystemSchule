/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author marcreinke
 */
public class Protocol {
    private PrintWriter writer;
    private BufferedReader reader;
    private ObjectInputStream oIn;
    private ObjectOutputStream oOut;
    
    public Protocol(){
    InputStream input=server.getInputStream();
    OutputStream output=server.getOutputStream();
    writer =new PrintWriter(server.getOutputStream(),true);
    reader =new BufferedReader(new InputStreamReader((server.getInputStream())));
    }
    
}
