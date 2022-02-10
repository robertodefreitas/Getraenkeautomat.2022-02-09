package my.machine;

import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.Muenze;
import my.machine.kasse.Muenzfach;
import my.machine.waren.Getraenk;
import my.machine.waren.Getraenkefach;
import my.machine.waren.Warenbestand;

import java.util.ArrayList;
import java.util.List;

public class Getraenkeautomat {

    private Warenbestand warenbestand;
    private Geldbestand geldbestand;

    /* constructor */

    /* getters and setters */

    /* methods */
    public GetraenkUndWechselgeld kaufen(Getraenkewunsch auswahl, List<Muenze> einzahlung){
        String methodeName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("INFO [" + methodeName + "] Getränkewunsch: " + auswahl.getFachNummer());

        for (Muenze muenze : einzahlung){
            System.out.println("INFO [" + methodeName + "] Münze: " + muenze.getMuenze());
        }

        /**
         * WIP: 10.02.2022
         * Exception Handling
         * https://www.baeldung.com/java-exceptions
         * https://michaelkipp.de/java/20B%20exceptions-io.html
         */

        /**
         * Prüft ob das gewünschte Getränk vorhanden ist
         * Prüft ob genug Geld eingezahlt ist
         * Prüft ob genug Münzen für das Wechselgeld vorhanden ist
         */
        try {
            Double einzahlungBetrag = geldbestand.umwandelnMuenzen2Betrag(einzahlung);
            //Double wechselgeldBetrag = warenbestand.getWechselgeldBetrag(auswahl, einzahlungBetrag); // alternativ
            Double wechselgeldBetrag = einzahlungBetrag - warenbestand.getGetrankpreis(auswahl);
            if(
                    warenbestand.isGetraenkewunschVorhanden(auswahl) &&
                    warenbestand.isEinzahlungBetragAusreichend(auswahl, einzahlungBetrag) &&
                    geldbestand.isGenugMuenzenFuerWechselgeldVorhanden(wechselgeldBetrag)
            ) {
                warenbestand.getraenkKonsumieren(auswahl);
                
                GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld(
                        warenbestand.getGetraenk(auswahl),
                        geldbestand.umwandelnBetrag2Muenzen(wechselgeldBetrag),
                        "[OK] Das Einkaufen war erfolgreich.");

                return getraenkUndWechselgeld;
            }
        } catch (Exception e) {
            System.out.println("[ERROR]" + e.getMessage());
            return null;
        }
    }


    public void befuelleGetraenkefach(Integer fachNummer, List<Getraenk> getraenke){
        warenbestand.befuelleGetraenkefach(fachNummer, getraenke);
    }

    public void befuelleMuenzfach(List<Muenze> muenzen){
        geldbestand.befuelleMuenzfach(muenzen);
    }

    public void entleereGetraenkefach(){
        List<Getraenkefach> getraenkefaecher = new ArrayList<>();
        warenbestand = new Warenbestand(getraenkefaecher);
    }

    public void entleereMuenzfach(){
        List<Muenzfach> muenzfaecher = new ArrayList<>();
        geldbestand = new Geldbestand(muenzfaecher);
    }
}
