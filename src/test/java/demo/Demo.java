package demo;

import my.machine.Getraenkeautomat;
import my.machine.dto.GetraenkUndWechselgeld;
import my.machine.dto.Getraenkewunsch;
import my.machine.kasse.Muenze;
import my.machine.kasse.Muenztyp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    private Getraenkeautomat getraenkeautomat;

    /**
     * ## ##################################################
     * ## Nur für mich persöhnlich
     * ## ##################################################
     */

    /**
     * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Enum.html
     * https://www.baeldung.com/java-enum-values
     */
    //@Deprecated
    @Test
    public void enumTest() {
        /**
         * Assertions.assertEquals("ZEHN_CENT",Muenztyp.ZEHN_CENT);
         * org.opentest4j.AssertionFailedError:
         *      expected: java.lang.String@f0f2775<ZEHN_CENT>
         *      but was: my.machine.kasse.Muenztyp@5a4aa2f2<ZEHN_CENT>
         */
        Assertions.assertNotEquals("ZEHN_CENT", Muenztyp.ZEHN_CENT);
        Assertions.assertEquals(Muenztyp.valueOf("ZEHN_CENT"),Muenztyp.ZEHN_CENT);
        Assertions.assertEquals("ZEHN_CENT",Muenztyp.ZEHN_CENT.name());
        Assertions.assertEquals("ZEHN_CENT",Muenztyp.ZEHN_CENT.toString());
        //Assertions.assertEquals(10,Muenztyp.ZEHN_CENT.cent);
        //Assertions.assertEquals("ZEHN_CENT",Muenztyp(10));

        Assertions.assertEquals(4,Muenztyp.ZEHN_CENT.ordinal());
        Assertions.assertEquals(0,Muenztyp.ZWEI_EURO.ordinal());

        Assertions.assertEquals(Muenztyp.class,Muenztyp.ZEHN_CENT.getDeclaringClass());

        Muenztyp muenze101 = Muenztyp.ZEHN_CENT;
        Muenztyp muenze102 = Muenztyp.ZEHN_CENT;
        Muenztyp muenze103 = muenze101;
        Assertions.assertTrue(muenze101.equals(muenze102));
        Assertions.assertTrue(muenze101.equals(muenze103));


        Assertions.assertEquals(10, EnumTest.A0.valueInt);
    }


    @Deprecated
    //@Test
    public void demoSchleifeMuenzen(){
        String methodeName = new Object(){}.getClass().getEnclosingMethod().getName();

        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(2);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZEHN_CENT),
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.ZWEI_EURO)
        ));

        //startGetraenkeautomat();

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
