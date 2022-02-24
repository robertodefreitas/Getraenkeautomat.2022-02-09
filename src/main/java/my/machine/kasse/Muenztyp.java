package my.machine.kasse;

/*
public enum Muenztyp {
    ZEHN_CENT,
    ZWANZIG_CENT,
    FUENZIG_CENT,
    EIN_EURO,
    ZWEI_EURO;
}
*/

/**
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Enum.html
 * https://www.baeldung.com/java-enum-values
 */
public enum Muenztyp {
    ZEHN_CENT(10),
    ZWANZIG_CENT(20),
    FUENZIG_CENT(50),
    EIN_EURO(100),
    ZWEI_EURO(200);

//    private final Integer cent;
    public final Integer cent;

    private Muenztyp(Integer cent) {
        this.cent = cent;
    }

//    public Integer getCent(){
//        return cent;
//    }
//
//    public static Muenztyp typOfCent(Integer gesuchterCent) {
//        for (Muenztyp mt : values()) {
//            if (mt.cent.equals(gesuchterCent)) {
//                return mt;
//            }
//        }
//        return null;
//    }

}