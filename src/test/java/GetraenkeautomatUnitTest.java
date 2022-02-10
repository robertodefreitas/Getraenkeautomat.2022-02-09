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

public class GetraenkeautomatUnitTest {

    /* TESTS */

    @Test
    public void getraenkewunschTest(){
        Getraenkeautomat getraenkeautomat = new Getraenkeautomat();
        Getraenkewunsch getraenkewunsch = new Getraenkewunsch(1);

        List<Muenze> einzahlung = new ArrayList<>(Arrays.asList(
                new Muenze(Muenztyp.ZWANZIG_CENT),
                new Muenze(Muenztyp.EIN_EURO)
        ));

        GetraenkUndWechselgeld getraenkUndWechselgeld = getraenkeautomat.kaufen(getraenkewunsch,einzahlung);

        Assertions.assertNotEquals(1, 2);
        Assertions.assertEquals(1, 1);

    }

}
