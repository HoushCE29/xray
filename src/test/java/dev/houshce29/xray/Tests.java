package dev.houshce29.xray;

import dev.houshce29.xray.testing.TestObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Tests {
    private Object object;

    @Before
    public void beforeEach() {
        object = new TestObject();
    }

    @Test
    public void testFieldXray() {
        Assert.assertEquals(TestObject.VALUE, Xray.xray(object, "value"));
        Assert.assertEquals(TestObject.ABSTRACT_STRING_FIELD_VALUE, Xray.xray(object, "abstractStringField"));
        Assert.assertEquals(TestObject.PRIVATE_CLASS_VALUE, Xray.xray(object, "nested.value"));
        Assert.assertEquals(TestObject.MAP.get("dut"), Xray.xray(object, "map.dut"));
    }

    @Test
    public void testArrayXray() {
        Assert.assertEquals(TestObject.ARRAY_VALUE[1], Xray.xray(object, "arrayValue[1]"));
        Assert.assertEquals(TestObject.ARRAY_OF_ARRAYS[1][1], Xray.xray(object, "arrayOfArrays[1][1]"));
        Assert.assertEquals(TestObject.LIST.get(2), Xray.xray(object, "stringList[2]"));
        Assert.assertEquals(TestObject.LIST_OF_LISTS.get(0).get(1), Xray.xray(object, "listOfLists[0][1]"));
        Assert.assertEquals(TestObject.ABSTRACT_STRING_ARRAY_VALUE[3], Xray.xray(object, "abstractStringArray[3]"));
        Assert.assertEquals(TestObject.ABSTRACT_STRING_LIST_VALUE.get(1), Xray.xray(object, "abstractStringList[1]"));
    }

    @Test
    public void testMixedXray() {
        Assert.assertEquals(TestObject.PRIVATE_CLASS_VALUE, Xray.xray(object, "nestedList[0].value"));
        Assert.assertEquals(TestObject.LIST_MAP.get("dogs").get(0), Xray.xray(object, "listMap.dogs[0]"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseFailure() {
        Xray.xray(object, "%%%");
    }
}
