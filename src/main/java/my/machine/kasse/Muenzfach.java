package my.machine.kasse;

public class Muenzfach {
    private Muenztyp muenze;
    private Integer muenzenAnzahl;
    private Integer maxMuenzenAnzahl;


    /* constructor */

    public Muenzfach(Muenztyp muenze, Integer muenzenAnzahl, Integer maxMuenzenAnzahl) {
        this.muenze = muenze;
        this.muenzenAnzahl = muenzenAnzahl;
        this.maxMuenzenAnzahl = maxMuenzenAnzahl;
    }


    /* getters and setters */

    public Muenztyp getMuenze() {
        return muenze;
    }

    public void setMuenze(Muenztyp muenze) {
        this.muenze = muenze;
    }

    public Integer getMuenzenAnzahl() {
        return muenzenAnzahl;
    }

    public void setMuenzenAnzahl(Integer muenzenAnzahl) {
        this.muenzenAnzahl = muenzenAnzahl;
    }

    public Integer getMaxMuenzenAnzahl() {
        return maxMuenzenAnzahl;
    }

    public void setMaxMuenzenAnzahl(Integer maxMuenzenAnzahl) {
        this.maxMuenzenAnzahl = maxMuenzenAnzahl;
    }


    /* methods */

    public boolean isMuenzfachLeer(){
        /**
         * TO-DO
         */
        return true;
    }

    public boolean isMuenzfachVoll(){
        /**
         * TO-DO
         */
        return true;
    }
}
