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

    public GetraenkUndWechselgeld(Getraenk getraenk, List<Muenze> wechselgeld, String fehler) {
        this.getraenk = getraenk;
        this.wechselgeld = wechselgeld;
        this.fehler = fehler;
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
