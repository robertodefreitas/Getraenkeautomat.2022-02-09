package my.machine;

import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.Muenze;
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

        GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld();
        return getraenkUndWechselgeld;
    }

    public void befuelleAutomat(Integer getraenkefachNummer, Integer getraenkeAnzahl, List<Muenze> muenzen){
        warenbestand.befuelleGetraenkefach(getraenkefachNummer, getraenkeAnzahl);
        geldbestand.befuelleMuenzfaecher(muenzen);
    }

    public void entleereAutomat(){
        List<Getraenkefach> getraenkefaecher = new ArrayList<>();
        warenbestand = new Warenbestand(getraenkefaecher);
        geldbestand = new Geldbestand();
    }
}
