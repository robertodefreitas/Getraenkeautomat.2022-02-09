package my.machine.kasse;

/*
public enum Muenztyp {
    ZWEI_EURO,
    EIN_EURO,
    FUENZIG_CENT,
    ZWANZIG_CENT,
    ZEHN_CENT;
}
*/

import java.util.ArrayList;
import java.util.List;

/**
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Enum.html
 * https://www.baeldung.com/java-enum-values
 */
public enum Muenztyp {
    ZWEI_EURO(200),
    EIN_EURO(100),
    FUENZIG_CENT(50),
    ZWANZIG_CENT(20),
    ZEHN_CENT(10);

    private final Integer cent;
//    public final Integer cent;

    private Muenztyp(Integer cent) {
        this.cent = cent;
    }

//    public Integer getCent(){
//        return cent;
//    }

//    public static Muenztyp typOfCent(Integer gesuchterCent) {
//        for (Muenztyp mt : values()) {
//            if (mt.cent.equals(gesuchterCent)) {
//                return mt;
//            }
//        }
//        return null;
//    }

    /**
     * hier muss static sein um direkt die methode im Geldbestanden verwenden zu dürfen
     */
    public static Integer summeAlleMuenzenCents(List<Muenze> muenzen){
        Integer summeCents = 0;
        for (Muenze muenze : muenzen){
            summeCents = summeCents + muenze.getMuenze().cent;
        }

        return summeCents;
    }

    public static List<Muenze> umwandelnCents2Muenzen(Integer cent){
        List<Muenze> muenzen = new ArrayList<>();

        // ORDER: first: ZWEI_EURO ... last: ZEHN_CENT
        for (Muenztyp muenzetyp: values()){
            Integer muenzeCent = muenzetyp.cent;

            while (cent >= muenzeCent) {
                muenzen.add( new Muenze(muenzetyp) );
                cent = cent - muenzeCent;
            }

//            /**
//             * https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html
//             * ## public static int floorDiv(int x, int y)
//             * For example, floorDiv(4, 3) == 1 and (4 / 3) == 1.
//             *
//             * Returns the floor modulus of the int arguments.
//             * ## public static int floorMod(int x, int y)
//             * floorMod(5, 3) == 2;   and (5 % 3) == 2
//             *     x - the dividend
//             *     y - the divisor
//             */
//            Integer result = Math.floorDiv(cent,muenzeCent);
//            Integer resultMod = Math.floorMod(cent,muenzeCent);
//
//            if (result != 0){
//                for (int i = 0; i < result; i++) {
//                    muenzen.add(bekommenMuenztypVonCent(muenzeCent));
//                }
//                cent = cent - result*muenzeCent;
//            }
//            if (resultMod != 0) {
//                cent = resultMod;
//            }

        }

        return muenzen;
    }


    public static Integer umwandelnMuenzen2Cents(List<Muenze> muenzen){
        Integer centSumme = 0;
        for (Muenze muenze : muenzen){
            centSumme = centSumme + muenze.getMuenze().cent;
        }
        return centSumme;
    }

}