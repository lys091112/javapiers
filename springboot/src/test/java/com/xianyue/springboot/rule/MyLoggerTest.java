package com.xianyue.springboot.rule;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;

public class MyLoggerTest {

    @Rule
    public final TestLogger testLogger = new TestLogger();

    @Test
    public void testMyLogger() {
//        final Logger logger = testLogger.getLogger();
//        logger.warn("my test own logger");
          System.out.println("hello");
    }
}
