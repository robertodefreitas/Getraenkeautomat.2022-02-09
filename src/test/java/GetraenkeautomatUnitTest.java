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
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetraenkeautomatUnitTest {

    Getraenkeautomat getraenkeautomat;


    /* TESTS */

    @Test
    public void kaufenTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        startGetraenkeautomat();

        Assertions.assertEquals(1000, getraenkeautomat.summeAlleMuenzenCentsKasse());

        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals(1100, getraenkeautomat.summeAlleMuenzenCentsKasse());
        Assertions.assertEquals("wasser", getraenkUndWechselgeld.getGetraenk().getName());
        Assertions.assertNotEquals("ERROR", getraenkUndWechselgeld.getFehler());

        getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals(1100, getraenkeautomat.summeAlleMuenzenCentsKasse());
        Assertions.assertEquals("ERROR", getraenkUndWechselgeld.getFehler());

    }


    @Test
    public void pruefenGetraenkewunschVorhandenTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(5);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.EIN_EURO)
        ));

        startGetraenkeautomat();
        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals("ERROR", getraenkUndWechselgeld.getFehler());
    }


    @Test
    public void pruefenEinzahlungBetragAusreichendTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.FUENZIG_CENT)
        ));

        startGetraenkeautomat();

        Assertions.assertEquals(1000, getraenkeautomat.summeAlleMuenzenCentsKasse());

        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals(1000, getraenkeautomat.summeAlleMuenzenCentsKasse());
        Assertions.assertEquals("ERROR", getraenkUndWechselgeld.getFehler());

    }

    @Test
    public void pruefenMuenzenFuerWechselgeldTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        startGetraenkeautomat();
        Assertions.assertEquals(1000, getraenkeautomat.summeAlleMuenzenCentsKasse());

        getraenkeautomat.entleereMuenzfaecher();
        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);
        Assertions.assertEquals("ERROR", getraenkUndWechselgeld.getFehler());
    }


    @Test
    public void befuelleGetraenkefachTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        List<Getraenk> getraenkeWasser = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            getraenkeWasser.add(new Getraenk("wasser"));
        }

        startGetraenkeautomat();
        Assertions.assertEquals(1, getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        getraenkeautomat.entleereGetraenkefaecher();
        Assertions.assertEquals(0, getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        getraenkeautomat.befuelleGetraenkefach(getraenkewunsch, getraenkeWasser);
        Assertions.assertEquals(10, getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        getraenkeautomat.befuelleGetraenkefach(getraenkewunsch, getraenkeWasser);
        Assertions.assertEquals(11, getraenkeautomat.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));
    }


    /**
     * Nur für mich persöhnlich
     */
    @Test
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


    /* methods */

    public void startGetraenkeautomat(){

        /**
         * Start Warenbestand
         */

        List<Getraenk> getraenkeWasser1 = new ArrayList<>(Arrays.asList(
                new Getraenk("wasser")
        ));

        List<Getraenk> getraenkeWasser2 = new ArrayList<>(Arrays.asList(
                new Getraenk("wasser"),
                new Getraenk("wasser")
        ));

        List<Getraenk> getraenkeCola = new ArrayList<>(Arrays.asList(
                new Getraenk("cola"),
                new Getraenk("cola")
        ));

        List<Getraenkefach> getraenkefaecher = new ArrayList<>(Arrays.asList(
                new Getraenkefach(1,getraenkeWasser1,11,91), // 0.91 -> 1.0 keine Münze kleiner 10c
                new Getraenkefach(2,getraenkeCola,11,123),
                new Getraenkefach(3,getraenkeWasser2,11,99)
        ));

        Warenbestand warenbestand = new Warenbestand(getraenkefaecher);


        /**
         * Start Geldbestand
         */

        List<Muenze> kasseStartenMitMuenzen = new ArrayList<>(Arrays.asList(
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

        Geldbestand geldbestand = new Geldbestand(15);

        geldbestand.befuelleMuenzfaecher(kasseStartenMitMuenzen);


        /**
         * Start Getraenkeautomat
         */
        this.getraenkeautomat = new Getraenkeautomat(warenbestand,geldbestand);
    }

}
