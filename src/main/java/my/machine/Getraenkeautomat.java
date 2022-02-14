package my.machine;

import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.GeldbestandFehler;
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

        try{
            this.warenbestand.pruefenGetraenkewunschVorhanden(auswahl);

            Double einzahlungBetrag = geldbestand.umwandelnCents2Betrag(geldbestand.umwandelnMuenzen2Cents(einzahlung));
            this.warenbestand.pruefenEinzahlungBetragAusreichend(auswahl, einzahlungBetrag);

            List<Muenze> wechselgeldMuenzen = this.geldbestand.umwandelnCents2Muenzen(wechselgeldBerechnenCents(auswahl,einzahlung));
            this.geldbestand.pruefenMuenzenFuerWechselgeld(wechselgeldMuenzen);

            Getraenk getraenk = this.warenbestand.getraenkKonsumieren(auswahl);
            this.geldbestand.befuelleMuenzfaecher(einzahlung);
            this.geldbestand.abziehenMuenzenVonMuenzfaecher(wechselgeldMuenzen);
            GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
            return ergebnisGetraenkUndWechselgeld.kaufenErfolgreich(getraenk, wechselgeldMuenzen);

        }
        catch (WarenbestandFehler fehler){
            System.out.println("[ERROR] " + fehler.getMessage());
            GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
            return ergebnisGetraenkUndWechselgeld.kaufenFehlerhaft(einzahlung,"ERROR");
        }
        catch (GeldbestandFehler fehler){
            System.out.println("[ERROR] " + fehler.getMessage());
            GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
            return ergebnisGetraenkUndWechselgeld.kaufenFehlerhaft(einzahlung,"ERROR");
        }
//        catch (Exception e){
//            System.out.println("[ERROR]" + e.getMessage());
//            GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
//            return ergebnisGetraenkUndWechselgeld.kaufenFehlerhaft(einzahlung,"ERROR");
//        }

    }

    public Integer wechselgeldBerechnenCents(Getraenkewunsch auswahl, List<Muenze> einzahlung){
        Integer einzahlungCents = this.geldbestand.umwandelnMuenzen2Cents(einzahlung);
        Integer getraenkpreisCents = this.geldbestand.umwandelnBetrag2Cents(this.warenbestand.bekommenGetraenkepreis(auswahl));

        //Integer wechselgeldBetrag = warenbestand.getWechselgeldBetrag(auswahl, einzahlungBetrag); // alternativ
        Integer wechselgeldCents = einzahlungCents - getraenkpreisCents;

        return wechselgeldCents;
    }

    public void befuelleGetraenkefach(Getraenkewunsch auswahl, List<Getraenk> getraenke){
        try {
            this.warenbestand.befuelleGetraenkefach(auswahl, getraenke);
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

    public void entleereMuenzfaecher(){
        geldbestand.entleereMuenzfaecher();
    }

    public Integer summeAlleGetraenkeMitName(String getraenkName){
        return warenbestand.summeAlleGetraenkeMitName(getraenkName);
    }

    public Integer summeAlleMuenzeKasse(){
        return geldbestand.summeAlleMuenzenCents();
    }
}
