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
            return kaufenErfolgreichAbschliessen(auswahl,einzahlung);
        }
        catch (WarenbestandFehler | GeldbestandFehler fehler){
            return kaufenFehlerhaft(einzahlung,fehler.getMessage());
        }
//        catch (Exception fehler){
//            System.out.println("[ERROR]" + e.getMessage());
//            return kaufenFehlerhaft(einzahlung,"ERROR");
//        }
    }

    public GetraenkUndWechselgeld kaufenErfolgreichAbschliessen(Getraenkewunsch auswahl, List<Muenze> einzahlung) throws WarenbestandFehler{
        this.geldbestand.befuelleMuenzfaecher(einzahlung);
        List<Muenze> wechselgeld = wechselgeldBerechnenMuenze(auswahl,einzahlung);
        this.geldbestand.abziehenMuenzenVonMuenzfaecher(wechselgeld);

        GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
        Getraenk getraenk = this.warenbestand.getraenkKonsumieren(auswahl);
        List<Muenze> wechselgeldMunzen = wechselgeldBerechnenMuenze(auswahl, einzahlung);
        ergebnisGetraenkUndWechselgeld = ergebnisGetraenkUndWechselgeld.kaufenErfolgreich(getraenk, wechselgeldMunzen);

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

    public void pruefenMuenzenFuerWechselgeldAusreichend(Getraenkewunsch auswahl, List<Muenze> einzahlung) throws GeldbestandFehler {
        List<Muenze> wechselgeldMuenze = wechselgeldBerechnenMuenze(auswahl,einzahlung);
        this.geldbestand.pruefenMuenzenFuerWechselgeldAusreichend(wechselgeldMuenze);
    }


    public List<Muenze> wechselgeldBerechnenMuenze(Getraenkewunsch auswahl, List<Muenze> einzahlung){
        try {
            Integer einzahlungCents = this.geldbestand.umwandelnMuenzen2Cents(einzahlung);
            Integer getraenkpreisCents = this.warenbestand.bekommenGetraenkepreis(auswahl);

            //Integer wechselgeldBetrag = warenbestand.getWechselgeldBetrag(auswahl, einzahlungBetrag); // alternativ
            Integer wechselgeldCents = einzahlungCents - getraenkpreisCents;

            return this.geldbestand.umwandelnCents2Muenzen(wechselgeldCents);

        }
        catch (WarenbestandFehler fehler){
            System.out.println("[ERROR] " + fehler.getMessage());
            return new ArrayList<Muenze>();
        }
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
