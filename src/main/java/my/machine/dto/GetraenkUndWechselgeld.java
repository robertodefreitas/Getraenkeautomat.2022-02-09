package my.machine.dto;

import my.machine.kasse.Muenze;
import my.machine.waren.Getraenk;

import java.util.List;

public class GetraenkUndWechselgeld {
    private Getraenk getraenk;
    private List<Muenze> wechselgeld;
    private String fehler;


    /* constructor */

//    public GetraenkUndWechselgeld() { }

//    public GetraenkUndWechselgeld(Getraenk getraenk, List<Muenze> wechselgeld, String fehler) {
//        this.getraenk = getraenk;
//        this.wechselgeld = wechselgeld;
//        this.fehler = fehler;
//    }

    private GetraenkUndWechselgeld(Getraenk getraenk, List<Muenze> wechselgeld) {
        this.getraenk = getraenk;
        this.wechselgeld = wechselgeld;
        this.fehler = null;
    }

    private GetraenkUndWechselgeld(List<Muenze> einzahlung, String fehler) {
        this.getraenk = null;
        this.wechselgeld = einzahlung;
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

    public static GetraenkUndWechselgeld kaufenErfolgreich(Getraenk getraenk, List<Muenze> wechselgeld){
        return new GetraenkUndWechselgeld(getraenk, wechselgeld);
    }

    public static GetraenkUndWechselgeld kaufenFehlerhaft(List<Muenze> einzahlung, String fehler){
        return new GetraenkUndWechselgeld(einzahlung, fehler);
    }
}
