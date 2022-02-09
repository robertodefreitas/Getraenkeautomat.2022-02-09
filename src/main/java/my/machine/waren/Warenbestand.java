package my.machine.waren;

import my.machine.kasse.Muenzfach;

import java.util.List;

public class Warenbestand {
    private List<Getraenkefach> getraenkefaecher;

    /* constructor */
    public Warenbestand(List<Getraenkefach> getraenkefaecher) {
        this.getraenkefaecher = getraenkefaecher;
    }

    /* getters and setters */

    public List<Getraenkefach> getGetraenkefaecher() {
        return getraenkefaecher;
    }

    public void setGetraenkefaecher(List<Getraenkefach> getraenkefaecher) {
        this.getraenkefaecher = getraenkefaecher;
    }

    /* methods */

    public void befuelleGetraenkefach(Integer getraenkefachNummer, Integer getraenkeAnzahl){
        /**
         * TO-DO
         */
    }

//    public void entleereGetraenkefach(){
//        /**
//         * TO-DO
//         */
//    }

    public Double summeAlleGetraenke(){
        /**
         * TO-DO
         */
        return 9.99;
    }
}
