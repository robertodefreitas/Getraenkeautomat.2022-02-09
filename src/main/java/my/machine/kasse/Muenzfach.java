package my.machine.kasse;

import java.util.List;

public class Muenzfach {
    private List<Muenze> muenzen;
    private Integer maxMuenzenAnzahl;


    /* constructor */

    public Muenzfach(List<Muenze> muenzen, Integer maxMuenzenAnzahl) {
        this.muenzen = muenzen;
        this.maxMuenzenAnzahl = maxMuenzenAnzahl;
    }


    /* getters and setters */

    public List<Muenze> getMuenzen() {
        return muenzen;
    }

    public void setMuenzen(List<Muenze> muenzen) {
        this.muenzen = muenzen;
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
