package com.xianyue.basictype.regex;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 */
public class RegexPiersTest {


    @Before
    public void setUp() {
    }

    @Test
    public void findExclueSpecifyRegex() throws Exception {
        List<String> originStr = ImmutableList.of("ExternalCall/HTTP/mock/register_service1",
                "ExternalCall/HTTP/mock/all", "ExternalCall/HTTP/mock/allWeb");
        Optional<List<String>> actual = RegexPiers.findExclueSpecifyRegex(originStr);
        assertTrue(actual.isPresent());
        assertThat(actual.get().size(), is(2));
        assertEquals(originStr.get(0), actual.get().get(0));
        assertEquals(originStr.get(2), actual.get().get(1));
    }

}
