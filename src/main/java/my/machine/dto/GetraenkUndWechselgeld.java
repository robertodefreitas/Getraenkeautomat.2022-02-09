package my.machine.dto;

import my.machine.kasse.Muenze;
import my.machine.waren.Getraenk;

import java.util.List;

public class GetraenkUndWechselgeld {
    private Getraenk getraenk;
    private List<Muenze> wechselgeld;
    private String fehler;


    /* constructor */

    public GetraenkUndWechselgeld() { }

    // https://michaelkipp.de/java/20B%20exceptions-io.html
    // https://www.baeldung.com/java-exceptions
    // https://www.tutorialspoint.com/can-a-constructor-throw-an-exception-in-java
    public GetraenkUndWechselgeld(Getraenk getraenk, List<Muenze> wechselgeld, String fehler) throws Exception {
        this.getraenk = getraenk;
        this.wechselgeld = wechselgeld;
        this.fehler = fehler;

        if (getraenk == null) {
            throw new Exception("Getränk is nicht vorhanden. (null)");
        }
    }


    /* getters and setters */

    public Getraenk getGetraenk() {
        return getraenk;
    }

    public void setGetraenk(Getraenk getraenk) {
        this.getraenk = getraenk;
    }

    public List<Muenze> getWechselgeld() {
        return wechselgeld;
    }

    public void setWechselgeld(List<Muenze> wechselgeld) {
        this.wechselgeld = wechselgeld;
    }

    public String getFehler() {
        return fehler;
    }

    public void setFehler(String fehler) {
        this.fehler = fehler;
    }


    /* methods */
}
