package my.machine.kasse;

import my.machine.waren.WarenbestandFehler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geldbestand {
    private List<Muenzfach> muenzfaecher;


    /* constructor */

    public Geldbestand(){}

    public Geldbestand(Integer maxMuenzenAnzahl) {
        this.muenzfaecher = new ArrayList<>(Arrays.asList(
                new Muenzfach().erstellenMuenzfach(10,maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(20,maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(50,maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(100,maxMuenzenAnzahl),
                new Muenzfach().erstellenMuenzfach(200,maxMuenzenAnzahl)
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
            switch (muenze.bekommenCents()) {
                case 200:
                    hinzufuegenMunzeInMuenzfach(muenze.bekommenCents(),muenze);
                    break;
                case 100:
                    hinzufuegenMunzeInMuenzfach(muenze.bekommenCents(),muenze);
                    break;
                case 50:
                    hinzufuegenMunzeInMuenzfach(muenze.bekommenCents(),muenze);
                    break;
                case 20:
                    hinzufuegenMunzeInMuenzfach(muenze.bekommenCents(),muenze);
                    break;
                case 10:
                    hinzufuegenMunzeInMuenzfach(muenze.bekommenCents(),muenze);
                    break;
            }
        }
    }

    public void abziehenMunzeVonMuenzfach(Integer muenzfachId, Muenze muenze){
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


    public void abziehenMuenzenVonMuenzfaecher(List<Muenze> muenzen){
        for (Muenze muenze : muenzen){
            switch (muenze.bekommenCents()) {
                case 200:
                    abziehenMunzeVonMuenzfach(muenze.bekommenCents(),muenze);
                    break;
                case 100:
                    abziehenMunzeVonMuenzfach(muenze.bekommenCents(),muenze);
                    break;
                case 50:
                    abziehenMunzeVonMuenzfach(muenze.bekommenCents(),muenze);
                    break;
                case 20:
                    abziehenMunzeVonMuenzfach(muenze.bekommenCents(),muenze);
                    break;
                case 10:
                    abziehenMunzeVonMuenzfach(muenze.bekommenCents(),muenze);
                    break;
            }
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
                summeCents = summeCents + muenze.bekommenCents();
            }
        }
        return summeCents;
    }

    public Integer umwandelnBetrag2Cents(Double betrag){
        return (int) Math.round(betrag * 100);
    }

    public Double umwandelnCents2Betrag(Integer cents){
        return (cents.doubleValue() / 100);
    }

    public List<Muenze> umwandelnCents2Muenzen(Integer cents){
        List<Muenze> muenzen = new ArrayList<>();

        // ORDER: first: 200 ... last: 1
        Integer[] muenzeIds = {200, 100, 50, 20, 10};

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
            centsSumme = centsSumme + muenze.bekommenCents();
        }
        return centsSumme;
    }

    public void pruefenMunzfachLeer(Integer muenzfachId) throws GeldbestandFehler {
        for (Muenzfach muenzfach : this.muenzfaecher){
            if(muenzfach.getId().equals(muenzfachId)){
                if (muenzfach.isMuenzfachLeer()){
                    throw new GeldbestandFehler("[pruefenMunzfachLeer] Es ist nicht genug Münzen im Munzfach für Wechselgeld.");
                }
            }
        }
    }

    public void pruefenMuenzenFuerWechselgeld(List<Muenze> muenzen) throws GeldbestandFehler {
        for (Muenze muenze : muenzen){
            switch (muenze.bekommenCents()) {
                case 200:
                    pruefenMunzfachLeer(muenze.bekommenCents());
                    break;
                case 100:
                    pruefenMunzfachLeer(muenze.bekommenCents());
                    break;
                case 50:
                    pruefenMunzfachLeer(muenze.bekommenCents());
                    break;
                case 20:
                    pruefenMunzfachLeer(muenze.bekommenCents());
                    break;
                case 10:
                    pruefenMunzfachLeer(muenze.bekommenCents());
                    break;
            }
        }
    }

    public Muenze bekommenMuenztypVonCents(Integer cents){
        return new Muenzfach().bekommenMuenztypVonCents(cents);
    }

}
