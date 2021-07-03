package dev.houshce29.xray.testing;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestObject extends AbstractTestObject {
    public static final String VALUE = "TestObject value";
    public static final String[] ARRAY_VALUE = {"foo", "bar", "baz"};
    public static final String[][] ARRAY_OF_ARRAYS = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8"}};
    public static final List<String> LIST = Collections.unmodifiableList(Arrays.asList("11", "22", "33"));
    public static final List<List<String>> LIST_OF_LISTS = Collections.unmodifiableList(Arrays.asList(
            Collections.unmodifiableList(Arrays.asList("a", "b")),
            Collections.unmodifiableList(Arrays.asList("c", "d")),
            Collections.unmodifiableList(Arrays.asList("e", "f"))
    ));
    public static final String PRIVATE_CLASS_VALUE = "PrivateClass value";
    public static final Map<String, String> MAP;
    public static final Map<String, List<String>> LIST_MAP;
    private final String value = VALUE;
    private final String[] arrayValue = ARRAY_VALUE;
    private final String[][] arrayOfArrays = ARRAY_OF_ARRAYS;
    private final List<String> stringList = LIST;
    private final List<List<String>> listOfLists = LIST_OF_LISTS;
    private final PrivateClass nested = new PrivateClass();
    private final List<PrivateClass> nestedList = Collections.singletonList(new PrivateClass());
    private final Map<String, String> map = MAP;
    private final Map<String, List<String>> listMap = LIST_MAP;

    static {
        Map<String, String> baseMap = new HashMap<>();
        baseMap.put("gus", "quux");
        baseMap.put("dut", "beetle");
        MAP = Collections.unmodifiableMap(baseMap);
        Map<String, List<String>> baseListMap = new HashMap<>();
        baseListMap.put("dogs", Collections.unmodifiableList(Arrays.asList("Dutty", "Beetle")));
        baseListMap.put("chickens", Collections.unmodifiableList(Arrays.asList("Nuggetz", "Tweak")));
        LIST_MAP = Collections.unmodifiableMap(baseListMap);
    }

    private static final class PrivateClass {
        private String value = PRIVATE_CLASS_VALUE;
    }
}
