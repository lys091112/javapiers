package com.xianyue.third.mocktiotest;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * 一些基本的测试方法
 */
public class HamcrestUseTest {

    @Test
    public void testAdd() {

        //一般匹配符
        int s = 2;

        //allOf：所有条件必须都成立，测试才通过
        assertThat(s, allOf(greaterThan(1), lessThan(3)));
        //anyOf：只要有一个条件成立，测试就通过
        assertThat(s, anyOf(greaterThan(1), lessThan(1)));
        //anything：无论什么条件，测试都通过
        assertThat(s, anything());
        //is：变量的值等于指定值时，测试通过
        assertThat(s, is(2));
        //not：和is相反，变量的值不等于指定值时，测试通过
        assertThat(s, not(1));

        //数值匹配符
        double d = 10.0 / 3.0;
        //closeTo：浮点型变量的值在3.0±0.5范围内，测试通过
        assertThat(d, closeTo(3.0, 0.5));
        //greaterThan：变量的值大于指定值时，测试通过
        assertThat(d, greaterThan(3.0));
        //lessThan：变量的值小于指定值时，测试通过
        assertThat(d, lessThan(3.5));
        //greaterThanOrEuqalTo：变量的值大于等于指定值时，测试通过
        assertThat(d, greaterThanOrEqualTo(3.3));
        //lessThanOrEqualTo：变量的值小于等于指定值时，测试通过
        assertThat(d, lessThanOrEqualTo(3.4));

        //字符串匹配符
        String n = "Magci";
        //containsString：字符串变量中包含指定字符串时，测试通过
        assertThat(n, containsString("ci"));
        //startsWith：字符串变量以指定字符串开头时，测试通过
        assertThat(n, startsWith("Ma"));
        //endsWith：字符串变量以指定字符串结尾时，测试通过
        assertThat(n, endsWith("i"));
        //euqalTo：字符串变量等于指定字符串时，测试通过
        assertThat(n, equalTo("Magci"));
        //equalToIgnoringCase：字符串变量在忽略大小写的情况下等于指定字符串时，测试通过
        assertThat(n, equalToIgnoringCase("magci"));
        //equalToIgnoringWhiteSpace：字符串变量在忽略头尾任意空格的情况下等于指定字符串时，测试通过
        assertThat(n, equalToIgnoringWhiteSpace(" Magci   "));

        //集合匹配符
        List<String> l = Arrays.asList("Magci");
        //hasItem：Iterable变量中含有指定元素时，测试通过
        assertThat(l, hasItem("Magci"));

        Map<String, String> m = ImmutableMap.<String, String>of("mgc", "Magci");
        //hasEntry：Map变量中含有指定键值对时，测试通过
        assertThat(m, hasEntry("mgc", "Magci"));
        //hasKey：Map变量中含有指定键时，测试通过
        assertThat(m, hasKey("mgc"));
        //hasValue：Map变量中含有指定值时，测试通过
        assertThat(m, hasValue("Magci"));

        //assertEquals()方法，用来查看对象中存的值是否是期待的值，与字符串比较中使用的equals()方法类似
        assertEquals(1, 1);
        //assertSame()和assertNotSame()方法，用来比较两个对象的引用是否相等和不相等，类似于通过“==”和“!=”比较两个对象
        assertSame("abc", "abc");
        assertNotSame("abc", "abcd");
        //assertNull()和assertNotNull()方法，用来查看对象是否为空和不为空
        assertNull("should be null", null);
        assertNotNull("should be not null", "abc");
        //assertFalse()和assertTrue()方法，用来查看变量是是否为false或true，如果assertFalse()查看的变量的值是false则测试成功，如果是true则失败，assertTrue()
        // 与之相反
        assertTrue("failure - should be true", true);
        assertFalse("failure - should be false", false);
    }

}
