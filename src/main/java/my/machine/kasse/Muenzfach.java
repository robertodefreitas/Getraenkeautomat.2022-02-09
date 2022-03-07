package my.machine.kasse;

import java.util.ArrayList;
import java.util.List;

public class Muenzfach {
    private String id;
    //private Muenztyp id;
    private List<Muenze> muenzen;
    private Integer maxMuenzenAnzahl;


    /* constructor */

    public Muenzfach(){}

    public Muenzfach(String id, List<Muenze> muenzen, Integer maxMuenzenAnzahl) {
        this.id = id;
        this.muenzen = muenzen;
        this.maxMuenzenAnzahl = maxMuenzenAnzahl;
    }

    private Muenzfach(String id, Integer maxMuenzenAnzahl) {
        this.id = id;
        this.muenzen = new ArrayList<>();
        this.maxMuenzenAnzahl = maxMuenzenAnzahl;
    }

    /* getters and setters */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Muenzfach erstellenMuenzfach(String id, Integer maxMuenzenAnzahl){
        return new Muenzfach(id, maxMuenzenAnzahl);
    }

    public boolean isMuenzfachLeer(){
        if (muenzen.size() == 0){
            return true;
        }
        return false;
    }

    public boolean isMuenzfachVoll(){
        if (muenzen.size() == maxMuenzenAnzahl){
            return true;
        }
        return false;
    }

//    public Muenze bekommenMuenztypVonCents(Integer cents){
//        return new Muenze(cents);
//    }

}
