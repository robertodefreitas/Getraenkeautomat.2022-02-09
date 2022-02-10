package my.machine.waren;

import java.util.List;

public class Getraenkefach {
    private Integer fachNummer;
    private List<Getraenk> getraenke;
    private Integer maxGetraenkeAnzahl;
    private Double preis;

    /* constructor */


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
