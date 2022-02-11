package my.machine;

import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.Muenze;
import my.machine.kasse.Muenzfach;
import my.machine.waren.Getraenk;
import my.machine.waren.Getraenkefach;
import my.machine.waren.Warenbestand;
import my.machine.waren.WarenbestandFehler;

import java.util.ArrayList;
import java.util.List;

public class Getraenkeautomat {

    private Warenbestand warenbestand;
    private Geldbestand geldbestand;

    /* constructor */
    public Getraenkeautomat(Warenbestand warenbestand, Geldbestand geldbestand){
        this.warenbestand = warenbestand;
        this.geldbestand = geldbestand;
    }

    /* getters and setters */

    /* methods */

    public void neuesGetraenkefach(Getraenkewunsch getraenkewunsch){
        this.warenbestand.neuesGetraenkefach(getraenkewunsch);
    }

    public GetraenkUndWechselgeld kaufen(Getraenkewunsch auswahl, List<Muenze> einzahlung){
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
            Getraenk getraenk = this.warenbestand.getraenkKonsumieren(auswahl);
            this.geldbestand.befuelleMuenzfaecher(einzahlung);
            List<Muenze> wechselgeldMuenzen = this.geldbestand.umwandelnCents2Muenzen(wechselgeldBerechnenCents(auswahl,einzahlung));
            this.geldbestand.abziehenMuenzenVonMuenzfaecher(wechselgeldMuenzen);
            GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
            return ergebnisGetraenkUndWechselgeld.kaufenErfolgreich(getraenk, wechselgeldMuenzen);

        }
        catch (WarenbestandFehler fehler){
            System.out.println("[ERROR]" + fehler.getMessage());
            GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
            return ergebnisGetraenkUndWechselgeld.kaufenFehlerhaft(einzahlung,"ERROR");
        }
        catch (Exception e){
            System.out.println("[ERROR]" + e.getMessage());
            GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
            return ergebnisGetraenkUndWechselgeld.kaufenFehlerhaft(einzahlung,"ERROR");
        }

//        try {
//            if(
//                    warenbestand.isGetraenkewunschVorhanden(auswahl) &&
//                    warenbestand.isEinzahlungBetragAusreichend(auswahl, einzahlungBetrag) &&
//                    geldbestand.isGenugMuenzenFuerWechselgeldVorhanden(wechselgeldBetrag)
//            ) {
//                warenbestand.getraenkKonsumieren(auswahl);
//
//                GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld(
//                        warenbestand.getGetraenk(auswahl),
//                        geldbestand.umwandelnBetrag2Muenzen(wechselgeldBetrag),
//                        "[OK] Das Einkaufen war erfolgreich.");
//
//                return getraenkUndWechselgeld;
//            }
//
//            return null;
//
//        } catch (Exception e) {
//            System.out.println("[ERROR]" + e.getMessage());
//            return null;
//        }
    }

    public Integer wechselgeldBerechnenCents(Getraenkewunsch auswahl, List<Muenze> einzahlung){
        Integer einzahlungCents = this.geldbestand.umwandelnMuenzen2Cents(einzahlung);
        Integer getraenkpreisCents = this.geldbestand.umwandelnBetrag2Cents(this.warenbestand.bekommenGetraenkepreis(auswahl));

        //Integer wechselgeldBetrag = warenbestand.getWechselgeldBetrag(auswahl, einzahlungBetrag); // alternativ
        Integer wechselgeldCents = einzahlungCents - getraenkpreisCents;

        return wechselgeldCents;
    }

    public void befuelleGetraenkefach(Getraenkewunsch getraenkewunsch, List<Getraenk> getraenke){
        try {
            this.warenbestand.befuelleGetraenkefach(getraenkewunsch, getraenke);
        } catch (WarenbestandFehler fehler){
                System.out.println("[ERROR]" + fehler.getMessage());
        }
    }

    public void befuelleMuenzfach(List<Muenze> muenzen){
        this.geldbestand.befuelleMuenzfaecher(muenzen);
    }

    public void entleereGetraenkefach(){
        List<Getraenkefach> getraenkefaecher = new ArrayList<>();
        this.warenbestand = new Warenbestand(getraenkefaecher);
    }

    public void entleereMuenzfach(){
        List<Muenzfach> muenzfaecher = new ArrayList<>();
        this.geldbestand = new Geldbestand();
    }

    public Integer summeAlleMuenzeKasse(){
        return geldbestand.summeAlleMuenzenCents();
    }
}
