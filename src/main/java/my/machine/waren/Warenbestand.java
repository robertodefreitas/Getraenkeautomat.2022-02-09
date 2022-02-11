package my.machine.waren;

import my.machine.dto.Getraenkewunsch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Warenbestand {
    private List<Getraenkefach> getraenkefaecher;

    /* constructor */
    public Warenbestand(){}

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

        throw new WarenbestandFehler("Getraenkefach ist nicht vorhanden. Fach-ID: "
                        + getraenkewunsch.getFachNummer());
    }

    public Getraenk bekommenGetraenk(Getraenkewunsch getraenkewunsch){
        try {
            Getraenkefach getraenkefach = waehlenGetraenkefach(getraenkewunsch);
            return getraenkefach.getGetraenke().get(0);
        } catch (WarenbestandFehler fehler) {
            System.out.println("Fehler! " + fehler.getMessage());
            return null;
        }
    }

    public Double bekommenGetraenkepreis(Getraenkewunsch getraenkewunsch){
        try {
            Getraenkefach getraenkefach = waehlenGetraenkefach(getraenkewunsch);
            return getraenkefach.getPreis();
        } catch (WarenbestandFehler fehler) {
            System.out.println("Fehler! " + fehler.getMessage());
            return null;
        }
    }


    public Getraenk getraenkKonsumieren(Getraenkewunsch getraenkewunsch) throws WarenbestandFehler {
        try {
            Getraenkefach getraenkefach = waehlenGetraenkefach(getraenkewunsch);
            return getraenkefach.getraenkKonsumieren(getraenkewunsch);
        } catch (WarenbestandFehler fehler) {
            System.out.println("Fehler! " + fehler.getMessage());
            return null;
        }
    }

    public void befuelleGetraenkefach(Getraenkewunsch getraenkewunsch, List<Getraenk> getraenkeZuBefuellen) throws WarenbestandFehler{
        for (Getraenkefach getraenkefach:this.getraenkefaecher){
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

        throw new WarenbestandFehler("Getraenkefach ist nicht vorhanden. Fach-ID: "
                + getraenkewunsch.getFachNummer());
    }

    public void entleereGetraenkefach(){
        getraenkefaecher = new ArrayList<>();
    }

    public Integer summeAlleGetraenke(Getraenk getraenk){
        Integer summeGetraenke = 0;
        for (Getraenkefach getraenkefach : this.getraenkefaecher){
            if(getraenkefach.getGetraenke().get(0).getName() == getraenk.getName()){
                summeGetraenke = summeGetraenke + getraenkefach.getGetraenke().size();
            }
        }
        return summeGetraenke;
    }
}
