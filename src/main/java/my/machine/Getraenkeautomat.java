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
         * TO-DO
         */

        /**
         * Prüft ob das gewünschte Getränk vorhanden ist
         */
        if(warenbestand.isGetraenkewunschVorhanden(auswahl)){
            System.out.println("INFO [" + methodeName + "] Getränkewunsch ist vorhanden. Auswahl: " + auswahl.getFachNummer());


            /**
             * Prüft ob genug Geld eingezahlt ist
             */
            Double einzahlungBetrag = geldbestand.umwandelnMuenzen2Betrag(einzahlung);
            if (warenbestand.isEinzahlungBetragAusreichend(auswahl, einzahlungBetrag)){
                System.out.println("INFO [" + methodeName + "] Es wurde genug Geld eingezahlt. Einzahlungsbetrag: " + einzahlungBetrag);


                /**
                 * Prüft ob genug Münzen für das Wechselgeld vorhanden ist
                 */
                Double wechselgeldBetrag = einzahlungBetrag - warenbestand.getGetrankpreis(auswahl);
                //Double wechselgeldBetrag = warenbestand.getWechselgeldBetrag(auswahl, einzahlungBetrag); // alternativ

                if(geldbestand.isGenugMuenzenFuerWechselgeldVorhanden()){
                    System.out.println("INFO [" + methodeName + "] Es ist genug Münzen für das Wechselgeld da. Wechselgeldbetrag: " + wechselgeldBetrag);

                    Getraenk getraenk = warenbestand.getGetraenk(auswahl);
                    warenbestand.getraenkKonsumieren(auswahl);

                    List<Muenze> wechselgeld = geldbestand.umwandelnBetrag2Muenzen(wechselgeldBetrag);

                    String status = "[OK] Einkaufen erfolgreich abgeschlossen.";

                    GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld(getraenk, wechselgeld, status);

                    return getraenkUndWechselgeld;

                } else {
                    System.out.println("ERRO [" + methodeName + "] Es ist nicht genug Münzen für das Wechselgeld da. Wechselgeldbetrag: " + wechselgeldBetrag);

                    String status = "[ERROR] Wegen Münzenmangel ist das Einkaufen nicht abgeschlossen.";
                    Getraenk getraenkNULL = warenbestand.getGetraenkNULL;
                    GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld(getraenkNULL, einzahlung, status);
                    return getraenkUndWechselgeld;
                }


            } else {
                System.out.println("ERRO [" + methodeName + "] Es wurde nicht genug Geld eingezahlt. Einzahlungsbetrag: " + einzahlungBetrag);

                String status = "[ERROR] Zu wenig Geld eingezahlt.";
                Getraenk getraenkNULL = warenbestand.getGetraenkNULL;
                GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld(getraenkNULL, einzahlung, status);
                return getraenkUndWechselgeld;
            }


        } else {
            System.out.println("ERRO [" + methodeName + "] Getränkewunsch ist nicht vorhanden. Auswahl: " + auswahl.getFachNummer());

            String status = "[ERROR] Getränkewunsch ist nicht vorhanden.";
            Getraenk getraenkNULL = warenbestand.getGetraenkNULL;
            GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld(getraenkNULL, einzahlung, status);
            return getraenkUndWechselgeld;
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
