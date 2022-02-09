package my.machine.waren;

public class Getraenkefach {
    private Integer fachNummer;
    private Getraenk getraenk;
    private Integer getraenkeAnzahl;
    private Integer maxGetraenkeAnzahl;
    private Double preis;

    /* constructor */

    public Getraenkefach(Integer fachNummer, Getraenk getraenk, Integer getraenkeAnzahl, Integer maxGetraenkeAnzahl, Double preis) {
        this.fachNummer = fachNummer;
        this.getraenk = getraenk;
        this.getraenkeAnzahl = getraenkeAnzahl;
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

    public Getraenk getGetraenk() {
        return getraenk;
    }

    public void setGetraenk(Getraenk getraenk) {
        this.getraenk = getraenk;
    }

    public Integer getGetraenkeAnzahl() {
        return getraenkeAnzahl;
    }

    public void setGetraenkeAnzahl(Integer getraenkeAnzahl) {
        this.getraenkeAnzahl = getraenkeAnzahl;
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
