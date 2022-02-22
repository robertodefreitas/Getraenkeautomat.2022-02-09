package my.machine;

import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.GeldbestandFehler;
import my.machine.kasse.Muenze;
import my.machine.waren.Getraenk;
import my.machine.waren.Warenbestand;
import my.machine.waren.WarenbestandFehler;

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
         * DONE
         * Exception Handling bei der Methode kaufen(...)
         * https://www.baeldung.com/java-exceptions
         * https://michaelkipp.de/java/20B%20exceptions-io.html
         */

        try{
            this.warenbestand.pruefenGetraenkewunschVorhanden(auswahl);

//            Double einzahlungBetrag = geldbestand.umwandelnCents2Betrag(geldbestand.umwandelnMuenzen2Cents(einzahlung));
//            this.warenbestand.pruefenEinzahlungBetragAusreichend(auswahl, einzahlungBetrag);
            this.warenbestand.pruefenEinzahlungBetragAusreichend(auswahl, geldbestand.umwandelnCents2Betrag(geldbestand.umwandelnMuenzen2Cents(einzahlung)));

            List<Muenze> wechselgeldMuenzen = this.geldbestand.umwandelnCents2Muenzen(wechselgeldBerechnenCents(auswahl,einzahlung));
            this.geldbestand.pruefenMuenzenFuerWechselgeld(wechselgeldMuenzen);

            this.geldbestand.befuelleMuenzfaecher(einzahlung);
            this.geldbestand.abziehenMuenzenVonMuenzfaecher(wechselgeldMuenzen);

            GetraenkUndWechselgeld ergebnisGetraenkUndWechselgeld = new GetraenkUndWechselgeld();
//            Getraenk getraenk = this.warenbestand.getraenkKonsumieren(auswahl);
//            return ergebnisGetraenkUndWechselgeld.kaufenErfolgreich(getraenk, wechselgeldMuenzen);
            return ergebnisGetraenkUndWechselgeld.kaufenErfolgreich(this.warenbestand.getraenkKonsumieren(auswahl), wechselgeldMuenzen);

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
        try {
            Integer einzahlungCents = this.geldbestand.umwandelnMuenzen2Cents(einzahlung);
            Integer getraenkpreisCents = this.geldbestand.umwandelnBetrag2Cents(this.warenbestand.bekommenGetraenkepreis(auswahl));

            //Integer wechselgeldBetrag = warenbestand.getWechselgeldBetrag(auswahl, einzahlungBetrag); // alternativ
            Integer wechselgeldCents = einzahlungCents - getraenkpreisCents;

            return wechselgeldCents;

        } catch (WarenbestandFehler fehler){
            System.out.println("[ERROR] " + fehler.getMessage());
            return 0;
        }
    }

    public void befuelleGetraenkefach(Getraenkewunsch auswahl, List<Getraenk> getraenke){
        try {
            this.warenbestand.befuelleGetraenkefach(auswahl, getraenke);
        } catch (WarenbestandFehler fehler){
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
        } catch (WarenbestandFehler fehler){
            System.out.println("[ERROR] " + fehler.getMessage());
            return 0;
        }
    }

    public Integer summeAlleMuenzenCentsKasse(){
        return this.geldbestand.summeAlleMuenzenCents();
    }
}
