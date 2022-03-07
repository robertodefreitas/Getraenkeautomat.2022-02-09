package my.machine.waren;

import my.machine.dto.Getraenkewunsch;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

public class Warenbestand {
    private List<Getraenkefach> getraenkefaecher;


    /* constructor */
    //public Warenbestand(){}

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

    // https://michaelkipp.de/java/20B%20exceptions-io.html
    // https://www.baeldung.com/java-exceptions
    // https://www.tutorialspoint.com/can-a-constructor-throw-an-exception-in-java

    public void neuesGetraenkefach(Getraenkewunsch getraenkewunsch){
        this.getraenkefaecher.add(new Getraenkefach(getraenkewunsch));
    }

    public Getraenkefach waehlenGetraenkefach(Getraenkewunsch getraenkewunsch) throws WarenbestandFehler {
        for (Getraenkefach getraenkefach:this.getraenkefaecher){
            if(getraenkefach.getFachNummer() == getraenkewunsch.getFachNummer()){
                return getraenkefach;
            }
        }
        throw new WarenbestandFehler("ERROR Getränkefach ist nicht vorhanden.");
//        throw new WarenbestandFehler("[waehlenGetraenkefach] Getränkefach ist nicht vorhanden. Gewünschte Fach-ID: "
//                + getraenkewunsch.getFachNummer());
    }

    public void pruefenGetraenkewunschVorhanden(Getraenkewunsch getraenkewunsch) throws WarenbestandFehler {
        Getraenkefach getraenkefach = waehlenGetraenkefach(getraenkewunsch);
        if(getraenkefach.isGetraenkefachLeer()){
            throw new WarenbestandFehler("ERROR Getränkewunsch ist nicht vorhanden. Fach ist leer.");
//            throw new WarenbestandFehler("[pruefenGetraenkewunschVorhanden] Getränkewunsch ist nicht vorhanden. Fach ist leer. Gewünschte Fach-ID: "
//                    + getraenkewunsch.getFachNummer());
        }
        /**
         * WICHTIG
         * Es wird nur einmal ein Fehler gefangen. Wenn hier gefangen wird,
         * dann wird im Getraenkeautomat.java nicht mehr über catch gefangen.
         */
//        try {
//            Getraenkefach getraenkefach = waehlenGetraenkefach(getraenkewunsch);
//        } catch (WarenbestandFehler fehler) {
//            System.out.println("[ERROR] " + fehler.getMessage());
//        }
    }

    public void pruefenEinzahlungBetragAusreichend(Getraenkewunsch getraenkewunsch, Integer einzahlungBetrag) throws WarenbestandFehler {
        Getraenkefach getraenkefach = waehlenGetraenkefach(getraenkewunsch);
        Integer getraenkePreis = getraenkefach.getPreis();
        if(einzahlungBetrag < getraenkePreis){
            throw new WarenbestandFehler("ERROR Es wurde nicht genug Geld eingezahlt.");
//            throw new WarenbestandFehler("[pruefenEinzahlungBetragAusreichend] Es wurde nicht genug Geld eingezahlt ("
//                    + einzahlungBetrag
//                    + " Cent). Getränkepreis in Cent: "
//                    + getraenkePreis);
        }
    }

    public Integer bekommenGetraenkepreis(Getraenkewunsch getraenkewunsch) throws WarenbestandFehler {
            Getraenkefach getraenkefach = waehlenGetraenkefach(getraenkewunsch);
            return getraenkefach.getPreis();
    }


    public Getraenk getraenkAusgeben(Getraenkewunsch getraenkewunsch) throws WarenbestandFehler {
        Getraenkefach getraenkefach = waehlenGetraenkefach(getraenkewunsch);
        return getraenkefach.getraenkKonsumieren(getraenkewunsch);
    }

    public void befuelleGetraenkefach(Getraenkewunsch getraenkewunsch, List<Getraenk> getraenkeZuBefuellen) throws WarenbestandFehler{
        for (Getraenkefach getraenkefach:this.getraenkefaecher){
            // wir nützen hier zweimal stream um die Liste zusammen zu fügen (concat)
            if(getraenkefach.getFachNummer() == getraenkewunsch.getFachNummer()){
                for (Getraenk getraenk:getraenkeZuBefuellen){
                    if(getraenkefach.isGetraenkefachVoll()){
                        throw new WarenbestandFehler("[befuelleGetraenkefach] Getränkefach ist voll. Fach-ID: " + getraenkewunsch.getFachNummer());
                        /**
                         * TO-DO
                         * Wenn die Getränkefach voll ist, wird der Getränkerest rausgegeben
                         */
                    }
                    getraenkefach.getGetraenke().add(getraenk);
                }
                // return stop the loop and exit from methode
                return;
                // break stop the loop but not exit from methode
                //break;
            }
        }

        throw new WarenbestandFehler("[befuelleGetraenkefach] Getränkefach ist nicht vorhanden. Fach-ID: " + getraenkewunsch.getFachNummer());
    }

    /**
     * Diese Methode funktioniert mit dem lambda funktion concat
     * ich habe aber ander geschrieben (s. oben) um die Methode isVoll zu verwenden
     * also ohne lambda und concat
     */
/*
    public void befuelleGetraenkefach(Getraenkewunsch getraenkewunsch, List<Getraenk> getraenkeZuBefuellen) throws WarenbestandFehler{
        for (Getraenkefach getraenkefach:this.getraenkefaecher){

            // wir nützen hier zweimal stream um die Liste zusammen zu fügen (concat)
            if(getraenkefach.getFachNummer() == getraenkewunsch.getFachNummer()){
                List<Getraenk> resultsGetraenke = Stream.concat(
                        getraenkefach.getGetraenke().stream(),
                        getraenkeZuBefuellen.stream())
                        .collect(Collectors.toList());
                getraenkefach.setGetraenke(resultsGetraenke);
                // return stop the loop and exit from methode
                return;
                // break stop the loop but not exit from methode
                //break;
            }
        }

        throw new WarenbestandFehler("[befuelleGetraenkefach] Getränkefach ist nicht vorhanden. Fach-ID: " + getraenkewunsch.getFachNummer());
    }
*/

    public void entleereGetraenkefaecher(){
        for (Getraenkefach getraenkefach : this.getraenkefaecher){
            List<Getraenk> keineGetraenke = new ArrayList<>();
            getraenkefach.setGetraenke(keineGetraenke);
        }
    }


    public Integer summeAlleGetraenkeMitName(String getraenkName){
        Integer summeGetraenke = 0;
        for (Getraenkefach getraenkefach : this.getraenkefaecher){
            if(!getraenkefach.isGetraenkefachLeer()){
                if(getraenkefach.getGetraenke().get(0).getName() == getraenkName){
                    summeGetraenke = summeGetraenke + getraenkefach.getGetraenke().size();
                }
            }
        }
        return summeGetraenke;
    }

    public Integer summeAlleGetraenkeInGetraenkefach(Getraenkewunsch auswahl) throws WarenbestandFehler {
        for (Getraenkefach getraenkefach : this.getraenkefaecher){
            if(getraenkefach.getFachNummer() == auswahl.getFachNummer()){
                return getraenkefach.getGetraenke().size();
            }
        }

        throw new WarenbestandFehler("[summeAlleGetraenkeInGetraenkefach] Getränkefach ist nicht vorhanden. Fach-ID: " + auswahl.getFachNummer());
    }
}
