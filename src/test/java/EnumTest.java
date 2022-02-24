import java.util.HashMap;
import java.util.Map;

/**
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Enum.html
 * https://www.baeldung.com/java-enum-values
 */
public enum EnumTest {
    A0(10),
    B1(20),
    C2(50),
    D3(100),
    E4(200);

    public final Integer valueInt;

    private EnumTest(Integer valueInt) {
        this.valueInt = valueInt;
    }

//    private static final Map<Integer, EnumTest> BY_VALUE = new HashMap<>();
//
//    static {
//        for (EnumTest et : values()) {
//            BY_VALUE.put(et.valueInt, et);
//        }
//    }
//
//    public static EnumTest valueOfName(Integer valueInt) {
//        return BY_VALUE.get(valueInt);
//    }

}