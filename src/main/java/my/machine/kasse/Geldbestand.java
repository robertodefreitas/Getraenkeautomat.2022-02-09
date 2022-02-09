package my.machine.kasse;

import java.util.ArrayList;
import java.util.List;

public class Geldbestand {
    private List<Muenzfach> muenzfaecher;
    private Integer maxMuenzenAnzahlGlobal = 15;

    /* constructor */

    public Geldbestand() {
        this.muenzfaecher.add(new Muenzfach(Muenztyp.ZEHN_CENT,0,maxMuenzenAnzahlGlobal));
        this.muenzfaecher.add(new Muenzfach(Muenztyp.ZWANZIG_CENT,0,maxMuenzenAnzahlGlobal));
        this.muenzfaecher.add(new Muenzfach(Muenztyp.FUENZIG_CENT,0,maxMuenzenAnzahlGlobal));
        this.muenzfaecher.add(new Muenzfach(Muenztyp.EIN_EURO,0,maxMuenzenAnzahlGlobal));
        this.muenzfaecher.add(new Muenzfach(Muenztyp.ZWEI_EURO,0,maxMuenzenAnzahlGlobal));
    }

    /* getters and setters */

    public List<Muenzfach> getMuenzfaecher() {
        return muenzfaecher;
    }

    public void setMuenzfaecher(List<Muenzfach> muenzfaecher) {
        this.muenzfaecher = muenzfaecher;
    }

    /* methods */

    public void befuelleMuenzfaecher(List<Muenze> muenzen){
        /**
         * TO-DO
         */
    }

//    public void entleereMuenzfaecher(){
//        /**
//         * TO-DO
//         */
//    }

    public Double summeAlleMuenzen(){
        /**
         * TO-DO
         */
        return 9.99;
    }

    public Integer umwandelnBetrag2Cents(Double betrag){
        Integer betragCent = 999;
        /**
         * TO-DO
         */
        return betragCent;
    }

    public Double umwandelnCents2Betrag(Integer betragCent){
        Double betrag = 9.1234;
        /**
         * TO-DO
         */
        return betrag;
    }

    public List<Muenze> umwandelnBetrag2Muenzen(Double betrag){
        List<Muenze> muenzen = new ArrayList<>();
        /**
         * TO-DO
         */
        return muenzen;
    }

    public Double umwandelnMuenzen2Betrag(List<Muenze> muenzen){
        Double betrag = 9.1234;
        /**
         * TO-DO
         */
        return betrag;
    }

}
