package my.machine.kasse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geldbestand {
    private List<Muenzfach> muenzfaecher;


    /* constructor */

    public Geldbestand(){}

    public Geldbestand(Integer maxMuenzenAnzahl) {
        this.muenzfaecher = new ArrayList<>(Arrays.asList(
                new Muenzfach().erstellenMuenzfach(Muenztyp.ZEHN_CENT.getCent(), maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(Muenztyp.ZWANZIG_CENT.getCent(), maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(Muenztyp.FUENZIG_CENT.getCent(), maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(Muenztyp.EIN_EURO.getCent(), maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(Muenztyp.ZWEI_EURO.getCent(), maxMuenzenAnzahl)
        ));
    }


    /* getters and setters */

    public List<Muenzfach> getMuenzfaecher() {
        return muenzfaecher;
    }

    public void setMuenzfaecher(List<Muenzfach> muenzfaecher) {
        this.muenzfaecher = muenzfaecher;
    }


    /* methods */

//    public void erstellenMuenzfaecher(Integer maxMuenzenAnzahl){
//        this.muenzfaecher = new ArrayList<>(Arrays.asList(
//                new Muenzfach().erstellenMuenzfach(10,maxMuenzenAnzahl),
//                new Muenzfach().erstellenMuenzfach(20,maxMuenzenAnzahl),
//                new Muenzfach().erstellenMuenzfach(50,maxMuenzenAnzahl),
//                new Muenzfach().erstellenMuenzfach(100,maxMuenzenAnzahl),
//                new Muenzfach().erstellenMuenzfach(200,maxMuenzenAnzahl)
//        ));
//    }

    public void hinzufuegenMunzeInMuenzfach(Integer muenzfachId, Muenze muenze){
        for (Muenzfach muenzfach : this.muenzfaecher){
            if(muenzfach.getId().equals(muenzfachId)){
                Integer indexOfMuenzfach = muenzfaecher.indexOf(muenzfach);
                muenzfach.getMuenzen().add(muenze);
                this.muenzfaecher.set(indexOfMuenzfach,muenzfach);
            }
        }
    }

    public void befuelleMuenzfaecher(List<Muenze> muenzen){
        for (Muenze muenze : muenzen){
            //hinzufuegenMunzeInMuenzfach(muenze.bekommenCents(), muenze);
            hinzufuegenMunzeInMuenzfach(muenze.getMuenze().getCent(), muenze);
        }
    }

    public void abziehenMunzeVonMuenzfach(Integer muenzfachId, Muenze muenze) {
        for (Muenzfach muenzfach : this.muenzfaecher){
            if(muenzfach.getId().equals(muenzfachId)){
                Integer indexOfMuenzfach = muenzfaecher.indexOf(muenzfach);

                //muenzfach.getMuenzen().remove(new Muenze(muenzfachId)); // so funktioniert nicht
                List<Muenze> muenzen = muenzfach.getMuenzen();
                muenzen.remove(muenzen.size()-1);
                muenzfach.setMuenzen(muenzen);

                this.muenzfaecher.set(indexOfMuenzfach,muenzfach);
            }
        }
    }


    public void abziehenMuenzenVonMuenzfaecher(List<Muenze> muenzen) {
        for (Muenze muenze : muenzen){
            //abziehenMunzeVonMuenzfach(muenze.bekommenCents(),muenze);
            abziehenMunzeVonMuenzfach(muenze.getMuenze().getCent(), muenze);
        }
    }

    public void entleereMuenzfaecher(){
        for (Muenzfach muenzfach : this.muenzfaecher){
            List<Muenze> keineMuenze = new ArrayList<>();
            muenzfach.setMuenzen(keineMuenze);
        }
    }

    public Integer summeAlleMuenzenCents(){
        Integer summeCents = 0;
        for (Muenzfach muenzfach : this.muenzfaecher){
            for (Muenze muenze : muenzfach.getMuenzen()){
                summeCents = summeCents + muenze.getMuenze().getCent();
            }
        }
        return summeCents;
    }

//    // https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio
//    public Integer umwandelnBetrag2Cents(Double betrag){
//        return (int) Math.round(betrag * 100);
//    }
//
//    public Double umwandelnCents2Betrag(Integer cents){
//        return (cents.doubleValue() / 100);
//    }

    public List<Muenze> umwandelnCents2Muenzen(Integer cents){
        List<Muenze> muenzen = new ArrayList<>();

        // ORDER: first: ZWEI_EURO ... last: ZEHN_CENT
        Integer[] muenzeIds = {
                Muenztyp.ZWEI_EURO.getCent(),
                Muenztyp.EIN_EURO.getCent(),
                Muenztyp.FUENZIG_CENT.getCent(),
                Muenztyp.ZWANZIG_CENT.getCent(),
                Muenztyp.ZEHN_CENT.getCent()};

        for (Integer muenzeId: muenzeIds){
            Integer muenzeCents = muenzeId;
            /**
             * https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
             * ## public static int floorDiv(int x, int y)
             * For example, floorDiv(4, 3) == 1 and (4 / 3) == 1.
             *
             * Returns the floor modulus of the int arguments.
             * ## public static int floorMod(int x, int y)
             * floorMod(5, 3) == 2;   and (5 % 3) == 2
             *     x - the dividend
             *     y - the divisor
             */
            Integer result = Math.floorDiv(cents,muenzeCents);
            Integer resultMod = Math.floorMod(cents,muenzeCents);

            if (result != 0){
                for (int i = 0; i < result; i++) {
                    muenzen.add(bekommenMuenztypVonCents(muenzeCents));
                }
                cents = cents - result*muenzeCents;
            }
            if (resultMod != 0) {
                cents = resultMod;
            }
        }

        return muenzen;
    }

    public Integer umwandelnMuenzen2Cents(List<Muenze> muenzen){
        Integer centsSumme = 0;
        for (Muenze muenze : muenzen){
            centsSumme = centsSumme + muenze.getMuenze().getCent();
        }
        return centsSumme;
    }

    public void pruefenMunzfachLeer(Integer muenzfachId) throws GeldbestandFehler {
        for (Muenzfach muenzfach : this.muenzfaecher){
            if(muenzfach.getId().equals(muenzfachId)){
                if (muenzfach.isMuenzfachLeer()){
                    throw new GeldbestandFehler("ERROR Es ist nicht genug Münzen im Munzfach für Wechselgeld.");
                }
            }
        }
    }

    public void pruefenMuenzenFuerWechselgeldAusreichend(List<Muenze> muenzen) throws GeldbestandFehler {
        for (Muenze muenze : muenzen){
            pruefenMunzfachLeer(muenze.getMuenze().getCent());
        }
    }

    public Muenze bekommenMuenztypVonCents(Integer cents){
        return new Muenzfach().bekommenMuenztypVonCents(cents);
    }

}
