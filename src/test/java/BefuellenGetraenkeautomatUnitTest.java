import my.machine.Getraenkeautomat;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.Muenze;
import my.machine.kasse.Muenztyp;
import my.machine.waren.Getraenk;
import my.machine.waren.Getraenkefach;
import my.machine.waren.Warenbestand;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BefuellenGetraenkeautomatUnitTest {

    private static Getraenkeautomat getraenkeautomat;

    /* methods */

//    @BeforeAll
    public static void startGetraenkeautomat() {
        Warenbestand warenbestand = startenUndBefuellenWarenbestand();
        Geldbestand geldbestand = startenUndBefuellenGeldbestand();
        getraenkeautomat = new Getraenkeautomat(warenbestand, geldbestand);
    }

    public static Warenbestand startenUndBefuellenWarenbestand() {
        List<Getraenkefach> getraenkefaecher = new ArrayList<>(Arrays.asList(
                new Getraenkefach(1, mehrereGetraenkeBekommenWasser(), 11, 120), // 0.91 -> 1.0 keine Münze kleiner 10c
                new Getraenkefach(2, mehrereGetraenkeBekommenCola(), 11, 123),
                new Getraenkefach(3, mehrereGetraenkeBekommenWasser(), 11, 199)
        ));

        return new Warenbestand(getraenkefaecher);
    }

    public static Geldbestand startenUndBefuellenGeldbestand() {
        Geldbestand geldbestand = new Geldbestand(15);
        geldbestand.befuelleMuenzfaecher(mehrereMuenzeBekommen());
        return geldbestand;
    }


    public static List<Getraenk> mehrereGetraenkeBekommenWasser() {
        List<Getraenk> getraenke = new ArrayList<>(Arrays.asList(
                new Getraenk("wasser"),
                new Getraenk("wasser")
        ));
        return getraenke;
    }

    public static List<Getraenk> mehrereGetraenkeBekommenCola() {
        List<Getraenk> getraenke = new ArrayList<>(Arrays.asList(
                new Getraenk("cola"),
                new Getraenk("cola")
        ));
        return getraenke;
    }


    public static List<Muenze> mehrereMuenzeBekommen() {
        List<Muenze> mehrereMuenze = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZEHN_CENT),
                new Muenze(Muenztyp.ZEHN_CENT),
                new Muenze(Muenztyp.ZEHN_CENT),
                new Muenze(Muenztyp.ZEHN_CENT),

                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.ZWANZIG_CENT),

                new Muenze(Muenztyp.FUENZIG_CENT),
                new Muenze(Muenztyp.FUENZIG_CENT),

                new Muenze(Muenztyp.EIN_EURO),
                new Muenze(Muenztyp.EIN_EURO),
                new Muenze(Muenztyp.ZWEI_EURO),
                new Muenze(Muenztyp.EIN_EURO),
                new Muenze(Muenztyp.ZWEI_EURO),
                new Muenze(Muenztyp.EIN_EURO)
        ));

        return mehrereMuenze;
    }
}