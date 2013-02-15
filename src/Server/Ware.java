/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author marcreinke
 */
public class Ware {
    private int id;
    private String Name;
    private float preis;
    private int bestand;
    private int mindestbestand;
    
    public Ware(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
    }

    public int getMindestbestand() {
        return mindestbestand;
    }

    public void setMindestbestand(int mindestbestand) {
        this.mindestbestand = mindestbestand;
    }
    
}
