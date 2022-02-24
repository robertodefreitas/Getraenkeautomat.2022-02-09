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
                new Muenzfach().erstellenMuenzfach(Muenztyp.ZEHN_CENT.name(), maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(Muenztyp.ZWANZIG_CENT.name(), maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(Muenztyp.FUENZIG_CENT.name(), maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(Muenztyp.EIN_EURO.name(), maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(Muenztyp.ZWEI_EURO.name(), maxMuenzenAnzahl)
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

    public void hinzufuegenMunzeInMuenzfach(Muenze muenze){
        for (Muenzfach muenzfach : this.muenzfaecher){
            if(muenzfach.getId().equals(muenze.getMuenze().name())){
                Integer indexOfMuenzfach = muenzfaecher.indexOf(muenzfach);
                muenzfach.getMuenzen().add(muenze);
                this.muenzfaecher.set(indexOfMuenzfach,muenzfach);
            }
        }
    }

    public void befuelleMuenzfaecher(List<Muenze> muenzen){
        for (Muenze muenze : muenzen){
            //hinzufuegenMunzeInMuenzfach(muenze.bekommenCents(), muenze);
            hinzufuegenMunzeInMuenzfach(muenze);
        }
    }

    public void abziehenMunzeVonMuenzfach(Muenze muenze) {
        for (Muenzfach muenzfach : this.muenzfaecher){
            if(muenzfach.getId().equals(muenze.getMuenze().name())){
                Integer indexOfMuenzfach = muenzfaecher.indexOf(muenzfach);

                //muenzfach.getMuenzen().remove(new Muenze(muenzfachId)); // so funktioniert nicht
                List<Muenze> muenzen = muenzfach.getMuenzen();

                if(muenzen.size() > 0){
                    Integer letzteMuenzeIndex = muenzen.size() - 1;
                    muenzen.remove(letzteMuenzeIndex.intValue());
                    muenzfach.setMuenzen(muenzen);
                    this.muenzfaecher.set(indexOfMuenzfach,muenzfach);
                }
            }
        }
    }


    public void abziehenMuenzenVonMuenzfaecher(List<Muenze> muenzen) {
        for (Muenze muenze : muenzen){
            //abziehenMunzeVonMuenzfach(muenze.bekommenCents(),muenze);
            abziehenMunzeVonMuenzfach(muenze);
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
                summeCents = summeCents + muenze.getMuenze().cent;
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

    public List<Muenze> umwandelnCents2Muenzen(Integer cent){
        List<Muenze> muenzen = new ArrayList<>();

        // ORDER: first: ZWEI_EURO ... last: ZEHN_CENT
        Muenztyp[] allMuenzetyp = {
                Muenztyp.ZWEI_EURO,
                Muenztyp.EIN_EURO,
                Muenztyp.FUENZIG_CENT,
                Muenztyp.ZWANZIG_CENT,
                Muenztyp.ZEHN_CENT};

        for (Muenztyp muenzetyp: allMuenzetyp){
            Integer muenzeCent = muenzetyp.cent;

            while (cent >= muenzeCent) {
                muenzen.add( new Muenze(muenzetyp) );
                cent = cent - muenzeCent;
            }

//            /**
//             * https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
//             * ## public static int floorDiv(int x, int y)
//             * For example, floorDiv(4, 3) == 1 and (4 / 3) == 1.
//             *
//             * Returns the floor modulus of the int arguments.
//             * ## public static int floorMod(int x, int y)
//             * floorMod(5, 3) == 2;   and (5 % 3) == 2
//             *     x - the dividend
//             *     y - the divisor
//             */
//            Integer result = Math.floorDiv(cent,muenzeCent);
//            Integer resultMod = Math.floorMod(cent,muenzeCent);
//
//            if (result != 0){
//                for (int i = 0; i < result; i++) {
//                    muenzen.add(bekommenMuenztypVonCent(muenzeCent));
//                }
//                cent = cent - result*muenzeCent;
//            }
//            if (resultMod != 0) {
//                cent = resultMod;
//            }

        }

        return muenzen;
    }

    public Integer umwandelnMuenzen2Cents(List<Muenze> muenzen){
        Integer centSumme = 0;
        for (Muenze muenze : muenzen){
            centSumme = centSumme + muenze.getMuenze().cent;
        }
        return centSumme;
    }

    public void pruefenMunzfachLeer(String muenzfachId) throws GeldbestandFehler {
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
            pruefenMunzfachLeer(muenze.getMuenze().name());
        }
    }

    public Muenze bekommenMuenztypVonCent(Integer cent){
        return new Muenzfach().bekommenMuenztypVonCents(cent);
    }

}
