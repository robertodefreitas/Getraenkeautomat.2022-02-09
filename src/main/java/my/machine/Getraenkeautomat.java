package my.machine;

import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.GeldbestandFehler;
import my.machine.kasse.Muenze;
import my.machine.waren.Getraenk;
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
         * Exception Handling bei der Methode kaufen(...)
         * https://www.baeldung.com/java-exceptions
         * https://michaelkipp.de/java/20B%20exceptions-io.html
         * http://tutorials.jenkov.com/java-exception-handling/catching-multiple-exceptions.html
         */
        try{
            this.warenbestand.pruefenGetraenkewunschVorhanden(auswahl);
            pruefenEinzahlungBetragAusreichend(auswahl,einzahlung);
            pruefenMuenzenFuerWechselgeldAusreichend(auswahl,einzahlung);

            this.geldbestand.befuelleMuenzfaecher(einzahlung);
            List<Muenze> wechselgeldMunzen = abziehenMuenzenVonMuenzfaecher(auswahl,einzahlung);
            Getraenk getraenk = this.warenbestand.getraenkAusgeben(auswahl);

            return kaufenErfolgreichAbschliessen(getraenk,wechselgeldMunzen);
        }
        catch (WarenbestandFehler | GeldbestandFehler fehler){
            return kaufenFehlerhaft(einzahlung,fehler.getMessage());
        }
//        catch (Exception fehler){
//            System.out.println("[ERROR]" + e.getMessage());
//            return kaufenFehlerhaft(einzahlung,"ERROR");
//        }
    }

    public List<Muenze> abziehenMuenzenVonMuenzfaecher(Getraenkewunsch auswahl, List<Muenze> einzahlung) throws WarenbestandFehler {
        List<Muenze> wechselgeldMuenze = wechselgeldBerechnenMuenze(auswahl,einzahlung);
        this.geldbestand.abziehenMuenzenVonMuenzfaecher(wechselgeldMuenze);
        return wechselgeldMuenze;
    }

    public GetraenkUndWechselgeld kaufenErfolgreichAbschliessen(Getraenk ausgewaehltesGetraenk, List<Muenze> wechselgeldMunzen) {
        GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
        ergebnisGetraenkUndWechselgeld = ergebnisGetraenkUndWechselgeld.kaufenErfolgreich(ausgewaehltesGetraenk, wechselgeldMunzen);
        return ergebnisGetraenkUndWechselgeld;
    }

    public GetraenkUndWechselgeld kaufenFehlerhaft(List<Muenze> einzahlung, String fehler) {
        GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
        ergebnisGetraenkUndWechselgeld = ergebnisGetraenkUndWechselgeld.kaufenFehlerhaft(einzahlung,fehler);
        return ergebnisGetraenkUndWechselgeld;
    }

    public void pruefenEinzahlungBetragAusreichend(Getraenkewunsch auswahl, List<Muenze> einzahlung) throws WarenbestandFehler {
        Integer einzahlungCents = geldbestand.umwandelnMuenzen2Cents(einzahlung);
        this.warenbestand.pruefenEinzahlungBetragAusreichend(auswahl, einzahlungCents);
    }

    public void pruefenMuenzenFuerWechselgeldAusreichend(Getraenkewunsch auswahl, List<Muenze> einzahlung) throws GeldbestandFehler, WarenbestandFehler {
        List<Muenze> wechselgeldMuenze = wechselgeldBerechnenMuenze(auswahl,einzahlung);
        this.geldbestand.pruefenMuenzenFuerWechselgeldAusreichend(wechselgeldMuenze);
    }


    public List<Muenze> wechselgeldBerechnenMuenze(Getraenkewunsch auswahl, List<Muenze> einzahlung) throws WarenbestandFehler {
            Integer einzahlungCents = this.geldbestand.umwandelnMuenzen2Cents(einzahlung);
            Integer getraenkpreisCents = this.warenbestand.bekommenGetraenkepreis(auswahl);

            //Integer wechselgeldBetrag = warenbestand.getWechselgeldBetrag(auswahl, einzahlungBetrag); // alternativ
            Integer wechselgeldCents = einzahlungCents - getraenkpreisCents;

            List<Muenze> wechselgeldMuenze = this.geldbestand.umwandelnCents2Muenzen(wechselgeldCents);

            return wechselgeldMuenze;
    }

    public void befuelleGetraenkefach(Getraenkewunsch auswahl, List<Getraenk> getraenke){
        try {
            this.warenbestand.befuelleGetraenkefach(auswahl, getraenke);
        }
        catch (WarenbestandFehler fehler){
                System.out.println("[ERROR] " + fehler.getMessage());
        }
    }

    public void befuelleMuenzfach(List<Muenze> muenzen){
        this.geldbestand.befuelleMuenzfaecher(muenzen);
    }

    public void entleereGetraenkefaecher(){
        this.warenbestand.entleereGetraenkefaecher();
    }

    public void entleereMuenzfaecher(){
        this.geldbestand.entleereMuenzfaecher();
    }

    public Integer summeAlleGetraenkeMitName(String getraenkName){
        return this.warenbestand.summeAlleGetraenkeMitName(getraenkName);
    }

    public Integer summeAlleGetraenkeInGetraenkefach(Getraenkewunsch auswahl){
        try {
            return this.warenbestand.summeAlleGetraenkeInGetraenkefach(auswahl);
        }
        catch (WarenbestandFehler fehler){
            System.out.println("[ERROR] " + fehler.getMessage());
            return 0;
        }
    }

    public Integer summeAlleMuenzenCentsKasse(){
        return this.geldbestand.summeAlleMuenzenCents();
    }
}
