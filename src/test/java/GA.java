import my.machine.Getraenkeautomat;
import my.machine.kasse.Geldbestand;
import my.machine.waren.Getraenkefach;
import my.machine.waren.Warenbestand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GA {

    private static Getraenkeautomat getraenkeautomat;
    private static Integer maxGetraenkeAnzahl = 11;
    private static Integer maxMuenzenAnzahl = 15;

    public Getraenkeautomat getGetraenkeautomat() {
        return getraenkeautomat;
    }

    public void setGetraenkeautomat(Getraenkeautomat getraenkeautomat) {
        this.getraenkeautomat = getraenkeautomat;
    }

    /* methods */

    public static Getraenkeautomat starten() {
        Warenbestand warenbestand = startenUndBefuellenWarenbestand();
        Geldbestand geldbestand = startenUndBefuellenGeldbestand();
        getraenkeautomat = new Getraenkeautomat(warenbestand, geldbestand);
        return getraenkeautomat;
    }

    private static Warenbestand startenUndBefuellenWarenbestand() {
        List<Getraenkefach> getraenkefaecher = new ArrayList<>(Arrays.asList(
                new Getraenkefach(1, Befuellen.mehrereGetraenkeWasser(), maxGetraenkeAnzahl, 120), // 0.91 -> 1.0 keine Münze kleiner 10c
                new Getraenkefach(2, Befuellen.mehrereGetraenkeCola(), maxGetraenkeAnzahl, 123),
                new Getraenkefach(3, Befuellen.mehrereGetraenkeWasser(), maxGetraenkeAnzahl, 199)
        ));

        return new Warenbestand(getraenkefaecher);
    }

    private static Geldbestand startenUndBefuellenGeldbestand() {
        Geldbestand geldbestand = new Geldbestand(maxMuenzenAnzahl);
        geldbestand.befuelleMuenzfaecher(Befuellen.mehrereMuenze());
        return geldbestand;
    }

}