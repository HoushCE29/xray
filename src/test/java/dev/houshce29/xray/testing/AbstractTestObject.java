package dev.houshce29.xray.testing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractTestObject {
    public static String ABSTRACT_STRING_FIELD_VALUE = "abstractStringFieldValue";
    public static String[] ABSTRACT_STRING_ARRAY_VALUE = {"a", "b", "c", "d"};
    public static List<String> ABSTRACT_STRING_LIST_VALUE = Collections.unmodifiableList(Arrays.asList("x", "y", "z"));
    private final String abstractStringField = ABSTRACT_STRING_FIELD_VALUE;
    private final String[] abstractStringArray = ABSTRACT_STRING_ARRAY_VALUE;
    private final List<String> abstractStringList = ABSTRACT_STRING_LIST_VALUE;
}
