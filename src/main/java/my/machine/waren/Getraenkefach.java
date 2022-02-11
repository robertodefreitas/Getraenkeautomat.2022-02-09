package my.machine.waren;

import my.machine.dto.Getraenkewunsch;

import java.util.List;

public class Getraenkefach {
    private Integer fachNummer;
    private List<Getraenk> getraenke;
    private Integer maxGetraenkeAnzahl;
    private Double preis;

    /* constructor */

    public Getraenkefach(){
        this.fachNummer = null;
        this.getraenke = null;
        this.maxGetraenkeAnzahl = 0;
        this.preis = 0.0;
    }

    public Getraenkefach(Getraenkewunsch getraenkewunsch){
        this.fachNummer = getraenkewunsch.getFachNummer();
        this.getraenke = null;
        this.maxGetraenkeAnzahl = 0;
        this.preis = 0.0;
    }

    public Getraenkefach(Integer fachNummer, List<Getraenk> getraenke, Integer maxGetraenkeAnzahl, Double preis) {
        this.fachNummer = fachNummer;
        this.getraenke = getraenke;
        this.maxGetraenkeAnzahl = maxGetraenkeAnzahl;
        this.preis = preis;
    }

    /* getters and setters */

    public Integer getFachNummer() {
        return fachNummer;
    }

    public void setFachNummer(Integer fachNummer) {
        this.fachNummer = fachNummer;
    }

    public List<Getraenk> getGetraenke() {
        return getraenke;
    }

    public void setGetraenke(List<Getraenk> getraenke) {
        this.getraenke = getraenke;
    }

    public Integer getMaxGetraenkeAnzahl() {
        return maxGetraenkeAnzahl;
    }

    public void setMaxGetraenkeAnzahl(Integer maxGetraenkeAnzahl) {
        this.maxGetraenkeAnzahl = maxGetraenkeAnzahl;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    /* methods */

    public Getraenk getraenkKonsumieren(Getraenkewunsch getraenkewunsch){
        Integer indexLetzteGetraenkInFach = this.getraenke.size() - 1;
        this.getraenke.remove(indexLetzteGetraenkInFach);
        return this.getraenke.get(0);
    }
//    public Getraenkefach getraenkefachNull(){
//        return new Getraenkefach();
//    }

    public boolean isGetraenkefachLeer(){
        /**
         * TO-DO
         */
        return true;
    }

    public boolean isGetraenkefachVoll(){
        /**
         * TO-DO
         */
        return true;
    }
}
