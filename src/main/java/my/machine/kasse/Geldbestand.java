package my.machine.kasse;

import java.util.ArrayList;
import java.util.List;

public class Geldbestand {
    private List<Muenzfach> muenzfaecher;

    /* constructor */

    public Geldbestand(List<Muenzfach> muenzfaecher) {
        this.muenzfaecher = muenzfaecher;
    }

    /* getters and setters */

    public List<Muenzfach> getMuenzfaecher() {
        return muenzfaecher;
    }

    public void setMuenzfaecher(List<Muenzfach> muenzfaecher) {
        this.muenzfaecher = muenzfaecher;
    }

    /* methods */

    public void befuelleMuenzfach(List<Muenze> muenzen){
        /**
         * TO-DO
         */
    }

//    public void entleereMuenzfach(){
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
