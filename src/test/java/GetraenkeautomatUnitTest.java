import my.machine.Getraenkeautomat;
import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Muenze;
import my.machine.kasse.Muenztyp;
import my.machine.waren.Getraenk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetraenkeautomatUnitTest {

    Getraenkeautomat ga = GA.starten();
    
    /* methods */

//    @BeforeAll


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
        //GetraenkUndWechselgeld getraenkUndWechselgeldExpected = new GetraenkUndWechselgeld();
        //getraenkUndWechselgeldExpected = getraenkUndWechselgeldExpected.kaufenErfolgreich(getraenkExpected,wechselgeldExpected);
        GetraenkUndWechselgeld getraenkUndWechselgeldExpected = GetraenkUndWechselgeld.kaufenErfolgreich(getraenkExpected,wechselgeldExpected);

        Assertions.assertEquals(1000, ga.summeAlleMuenzenCentsKasse());

        GetraenkUndWechselgeld getraenkUndWechselgeldActual = ga.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals(1120, ga.summeAlleMuenzenCentsKasse());
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
        Integer summeAlleGetraenke = ga.summeAlleGetraenkeInGetraenkefach(getraenkewunsch);
        while (summeAlleGetraenke > 0) {
            getraenkUndWechselgeldActual = ga.kaufen(getraenkewunsch, einzahlung);
            ga.befuelleMuenzfach(Befuellen.mehrereMuenze());

            einzahlung = getraenkUndWechselgeldActual.getWechselgeld();
            summeAlleGetraenke = ga.summeAlleGetraenkeInGetraenkefach(getraenkewunsch);
        }

        getraenkUndWechselgeldActual = ga.kaufen(getraenkewunsch, einzahlung);
        Assertions.assertEquals("ERROR Getränkewunsch ist nicht vorhanden. Fach ist leer.", getraenkUndWechselgeldActual.getFehler());
    }

    @Test
    public void pruefenGetraenkewunschVorhandenTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(5);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.EIN_EURO)
        ));

        GetraenkUndWechselgeld getraenkUndWechselgeld = ga.kaufen(getraenkewunsch,einzahlung);
        Assertions.assertEquals("ERROR Getränkefach ist nicht vorhanden.", getraenkUndWechselgeld.getFehler());
    }


    @Test
    public void pruefenEinzahlungBetragAusreichendTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.FUENZIG_CENT)
        ));

        ga.entleereMuenzfaecher();
        ga.befuelleMuenzfach(Befuellen.mehrereMuenze());

        Assertions.assertEquals(1000, ga.summeAlleMuenzenCentsKasse());

        GetraenkUndWechselgeld getraenkUndWechselgeld = ga.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals(1000, ga.summeAlleMuenzenCentsKasse());
        Assertions.assertEquals("ERROR Es wurde nicht genug Geld eingezahlt.", getraenkUndWechselgeld.getFehler());

    }

    @Test
    public void pruefenMuenzenFuerWechselgeldTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        Assertions.assertEquals(1000, ga.summeAlleMuenzenCentsKasse());

        ga.entleereMuenzfaecher();
        Assertions.assertEquals(0, ga.summeAlleMuenzenCentsKasse());

        GetraenkUndWechselgeld getraenkUndWechselgeld = ga.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertEquals("ERROR Es ist nicht genug Münzen im Munzfach für Wechselgeld.", getraenkUndWechselgeld.getFehler());
    }


    @Deprecated
    //@Test
    public void befuelleGetraenkefachTest(){
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        List<Getraenk> getraenkeWasser = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            getraenkeWasser.add(new Getraenk("wasser"));
        }

        Assertions.assertEquals(2, ga.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        ga.entleereGetraenkefaecher();
        Assertions.assertEquals(0, ga.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        ga.befuelleGetraenkefach(getraenkewunsch, getraenkeWasser);
        Assertions.assertEquals(10, ga.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

        ga.befuelleGetraenkefach(getraenkewunsch, getraenkeWasser);
        Assertions.assertNotEquals(20, ga.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));
        Assertions.assertEquals(11, ga.summeAlleGetraenkeInGetraenkefach(getraenkewunsch));

    }
}
