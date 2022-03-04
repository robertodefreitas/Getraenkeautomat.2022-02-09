import my.machine.Getraenkeautomat;
import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Geldbestand;
import my.machine.kasse.Muenze;
import my.machine.kasse.Muenztyp;
import my.machine.waren.Getraenk;
import my.machine.waren.Getraenkefach;
import my.machine.waren.Warenbestand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetraenkeautomatUnitTest {

    private static Getraenkeautomat getraenkeautomat;

    /* methods */

    @BeforeAll
    public static void startGetraenkeautomat(){
        Warenbestand warenbestand = startenUndBefuellenWarenbestand();
        Geldbestand geldbestand = startenUndBefuellenGeldbestand();
        getraenkeautomat = new Getraenkeautomat(warenbestand,geldbestand);
    }

    public static Warenbestand startenUndBefuellenWarenbestand(){
        List<Getraenkefach> getraenkefaecher = new ArrayList<>(Arrays.asList(
                new Getraenkefach(1,mehrereGetraenkeBekommenWasser(),11,120), // 0.91 -> 1.0 keine Münze kleiner 10c
                new Getraenkefach(2,mehrereGetraenkeBekommenCola(),11,123),
                new Getraenkefach(3,mehrereGetraenkeBekommenWasser(),11,199)
        ));

        return new Warenbestand(getraenkefaecher);
    }

    public static Geldbestand startenUndBefuellenGeldbestand(){
        Geldbestand geldbestand = new Geldbestand(15);
        geldbestand.befuelleMuenzfaecher(mehrereMuenzeBekommen());
        return geldbestand;
    }


    public static List<Getraenk> mehrereGetraenkeBekommenWasser(){
        List<Getraenk> getraenke = new ArrayList<>(Arrays.asList(
                new Getraenk("wasser"),
                new Getraenk("wasser")
        ));
        return getraenke;
    }

    public static List<Getraenk> mehrereGetraenkeBekommenCola(){
        List<Getraenk> getraenke = new ArrayList<>(Arrays.asList(
                new Getraenk("cola"),
                new Getraenk("cola")
        ));
        return getraenke;
    }


    public static List<Muenze> mehrereMuenzeBekommen(){
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

        return  mehrereMuenze;
    }

    /* TESTS */

    @Test
    public void kaufenTest() throws Exception {
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        Getraenk getraenkExpected = new Getraenk("wasser");
        List<Muenze> wechselgeldExpected = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.FUENZIG_CENT),
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.ZEHN_CENT)
        ));
        GetraenkUndWechselgeld getraenkUndWechselgeldExpected = new GetraenkUndWechselgeld();
        getraenkUndWechselgeldExpected = getraenkUndWechselgeldExpected.kaufenErfolgreich(getraenkExpected,wechselgeldExpected);

        Assertions.assertEquals(1000, getraenkeautomat.summeAlleMuenzenCentsKasse());

        GetraenkUndWechselgeld getraenkUndWechselgeldActual = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals(1120, getraenkeautomat.summeAlleMuenzenCentsKasse());
        Assertions.assertEquals(getraenkExpected.getName(), getraenkUndWechselgeldActual.getGetraenk().getName());
        for (int i = 0; i < getraenkUndWechselgeldExpected.getWechselgeld().size(); i++) {
            Assertions.assertEquals(
                    getraenkUndWechselgeldExpected.getWechselgeld().get(i).getMuenze(),
                    getraenkUndWechselgeldActual.getWechselgeld().get(i).getMuenze()
            );
        }
    }

    @Test
    public void getraenkIstNichtVorhandenTest() throws Exception {
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(3);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO),
                new Muenze(Muenztyp.ZWEI_EURO),
                new Muenze(Muenztyp.ZWEI_EURO),
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        GetraenkUndWechselgeld getraenkUndWechselgeldActual;
        Integer summeAlleGetraenke = getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch);
        while (summeAlleGetraenke > 0) {
            getraenkUndWechselgeldActual = getraenkeautomat.kaufen(getraenkewunsch, einzahlung);
            getraenkeautomat.befuelleMuenzfach(mehrereMuenzeBekommen());

            einzahlung = getraenkUndWechselgeldActual.getWechselgeld();
            summeAlleGetraenke = getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch);
        }

        getraenkUndWechselgeldActual = getraenkeautomat.kaufen(getraenkewunsch, einzahlung);
        Assertions.assertEquals("ERROR Getränkewunsch ist nicht vorhanden. Fach ist leer.", getraenkUndWechselgeldActual.getFehler());
    }

    @Test
    public void pruefenGetraenkewunschVorhandenTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(5);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.EIN_EURO)
        ));

        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);
        Assertions.assertEquals("ERROR Getränkefach ist nicht vorhanden.", getraenkUndWechselgeld.getFehler());
    }


    @Test
    public void pruefenEinzahlungBetragAusreichendTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.FUENZIG_CENT)
        ));

        getraenkeautomat.entleereMuenzfaecher();
        getraenkeautomat.befuelleMuenzfach(mehrereMuenzeBekommen());

        Assertions.assertEquals(1000, getraenkeautomat.summeAlleMuenzenCentsKasse());

        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals(1000, getraenkeautomat.summeAlleMuenzenCentsKasse());
        Assertions.assertEquals("ERROR Es wurde nicht genug Geld eingezahlt.", getraenkUndWechselgeld.getFehler());

    }

    @Test
    public void pruefenMuenzenFuerWechselgeldTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        Assertions.assertEquals(1000, getraenkeautomat.summeAlleMuenzenCentsKasse());

        getraenkeautomat.entleereMuenzfaecher();
        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals("ERROR Es ist nicht genug Münzen im Munzfach für Wechselgeld.", getraenkUndWechselgeld.getFehler());
    }


    @Deprecated
    public void befuelleGetraenkefachTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        List<Getraenk> getraenkeWasser = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            getraenkeWasser.add(new Getraenk("wasser"));
        }

        Assertions.assertEquals(1, getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        getraenkeautomat.entleereGetraenkefaecher();
        Assertions.assertEquals(0, getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        getraenkeautomat.befuelleGetraenkefach(getraenkewunsch, getraenkeWasser);
        Assertions.assertEquals(10, getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        getraenkeautomat.befuelleGetraenkefach(getraenkewunsch, getraenkeWasser);
        Assertions.assertEquals(11, getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));
    }


    /**
     * ## ##################################################
     * ## Nur für mich persöhnlich
     * ## ##################################################
     */

    /**
     * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Enum.html
     * https://www.baeldung.com/java-enum-values
     */
    @Deprecated
    public void enumTest() {
        /**
         * Assertions.assertEquals("ZEHN_CENT",Muenztyp.ZEHN_CENT);
         * org.opentest4j.AssertionFailedError:
         *      expected: java.lang.String@f0f2775<ZEHN_CENT>
         *      but was: my.machine.kasse.Muenztyp@5a4aa2f2<ZEHN_CENT>
         */
        Assertions.assertNotEquals("ZEHN_CENT",Muenztyp.ZEHN_CENT);
        Assertions.assertEquals(Muenztyp.valueOf("ZEHN_CENT"),Muenztyp.ZEHN_CENT);
        Assertions.assertEquals("ZEHN_CENT",Muenztyp.ZEHN_CENT.name());
        Assertions.assertEquals("ZEHN_CENT",Muenztyp.ZEHN_CENT.toString());
        //Assertions.assertEquals(10,Muenztyp.ZEHN_CENT.cent);
        //Assertions.assertEquals("ZEHN_CENT",Muenztyp(10));

        Assertions.assertEquals(0,Muenztyp.ZEHN_CENT.ordinal());
        Assertions.assertEquals(4,Muenztyp.ZWEI_EURO.ordinal());

        Assertions.assertEquals(Muenztyp.class,Muenztyp.ZEHN_CENT.getDeclaringClass());

        Muenztyp muenze101 = Muenztyp.ZEHN_CENT;
        Muenztyp muenze102 = Muenztyp.ZEHN_CENT;
        Muenztyp muenze103 = muenze101;
        Assertions.assertTrue(muenze101.equals(muenze102));
        Assertions.assertTrue(muenze101.equals(muenze103));


        Assertions.assertEquals(10,EnumTest.A0.valueInt);
    }


    @Deprecated
    public void demoSchleifeMuenzen(){
        String methodeName = new Object(){}.getClass().getEnclosingMethod().getName();

        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(2);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZEHN_CENT),
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        startGetraenkeautomat();
        System.out.println("INFO Menge: " + getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        String getraenkName = "wasser";
        System.out.println("INFO [" + methodeName + "] Getränkeanzahl: "
                + getraenkeautomat.summeAlleGetraenkeMitName(getraenkName) + " " + getraenkName);

        getraenkName = "cola";
        System.out.println("INFO [" + methodeName + "] Getränkeanzahl: "
                + getraenkeautomat.summeAlleGetraenkeMitName(getraenkName) + " " + getraenkName);


        System.out.println("INFO [" + methodeName + "] Summe Kasse (Cent) vor Kaufen: " + getraenkeautomat.summeAlleMuenzenCentsKasse());

        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);
        getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        System.out.println("INFO [" + methodeName + "] Summe Kasse (Cent) nach Kaufen: " + getraenkeautomat.summeAlleMuenzenCentsKasse());


        getraenkName = "wasser";
        Integer summe = getraenkeautomat.summeAlleGetraenkeMitName(getraenkName);
        System.out.println("INFO [" + methodeName + "] Getränkeanzahl: "
                + summe + " " + getraenkName);

        getraenkName = "cola";
        System.out.println("INFO [" + methodeName + "] Getränkeanzahl: "
                + getraenkeautomat.summeAlleGetraenkeMitName(getraenkName) + " " + getraenkName);


        for (Muenze muenze : einzahlung){
            System.out.println("INFO [" + methodeName + "] Einzahlung Münze: " + muenze.getMuenze());
        }

        for (Muenze muenze : getraenkUndWechselgeld.getWechselgeld()){
            System.out.println("INFO [" + methodeName + "] Wechselgeld Münze: " + muenze.getMuenze());
        }
    }

}
