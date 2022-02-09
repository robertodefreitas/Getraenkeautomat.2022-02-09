package my.machine;

import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.Muenze;
import my.machine.kasse.Muenzfach;
import my.machine.kasse.Muenztyp;
import my.machine.waren.Getraenk;
import my.machine.waren.Getraenkefach;
import my.machine.waren.Warenbestand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Getraenkeautomat {

    private Warenbestand warenbestand;
    private Geldbestand geldbestand;

    /* constructor */

    /* getters and setters */

    /* methods */
    public GetraenkUndWechselgeld kaufen(Getraenkewunsch auswahl, List<Muenze> einzahlung){
        GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld();
        /**
         * TO-DO
         */
        return getraenkUndWechselgeld;
    }

    public void befuelleAutomat(Integer getraenkefachNummer, Integer getraenkeAnzahl, List<Muenze> muenzen){
        warenbestand.befuelleGetraenkefach(getraenkefachNummer, getraenkeAnzahl);
        geldbestand.befuelleMuenzfaecher(muenzen);
    }

    public void entleereAutomat(){
        /**
         * TO-DO
         */
    }
}
