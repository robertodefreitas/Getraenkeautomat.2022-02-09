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
        String methodeName = new Object(){}.getClass().getEnclosingMethod().getName();

        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);
        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.EIN_EURO)
        ));

        startGetraenkeautomat();
        System.out.println("INFO [" + methodeName + "] Summe Kasse (Cent): " + getraenkeautomat.summeAlleMuenzeKasse());
        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);
        System.out.println("INFO [" + methodeName + "] Summe Kasse (Cent): " + getraenkeautomat.summeAlleMuenzeKasse());

        for (Muenze muenze : einzahlung){
            System.out.println("INFO [" + methodeName + "] Einzahlung Münze: " + muenze.getMuenze());
        }

        for (Muenze muenze : getraenkUndWechselgeld.getWechselgeld()){
            System.out.println("INFO [" + methodeName + "] Wechselgeld Münze: " + muenze.getMuenze());
        }

        Assertions.assertEquals("wasser", getraenkUndWechselgeld.getGetraenk().getName());
        Assertions.assertNotEquals("ERROR", getraenkUndWechselgeld.getFehler());

    }


    /* methods */

    public void startGetraenkeautomat(){

        /**
         * Start Warenbestand
         */
        List<Getraenk> getraenkeWasser = new ArrayList<>(Arrays.asList(
                new Getraenk("wasser"),
                new Getraenk("wasser")
        ));

        List<Getraenk> getraenkeCola = new ArrayList<>(Arrays.asList(
                new Getraenk("cola"),
                new Getraenk("cola")
        ));

        List<Getraenkefach> getraenkefaecher = new ArrayList<>(Arrays.asList(
                new Getraenkefach(1,getraenkeWasser,11,0.99),
                new Getraenkefach(2,getraenkeCola,11,1.52),
                new Getraenkefach(3,getraenkeWasser,11,0.99)
        ));

        Warenbestand warenbestand = new Warenbestand(getraenkefaecher);


        /**
         * Start Geldbestand
         */

        List<Muenze> kasseStartenMitMuenzen = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZEHN_CENT),
                new Muenze(Muenztyp.ZEHN_CENT),

                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.ZWANZIG_CENT),

                new Muenze(Muenztyp.FUENZIG_CENT),
                new Muenze(Muenztyp.FUENZIG_CENT),

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
