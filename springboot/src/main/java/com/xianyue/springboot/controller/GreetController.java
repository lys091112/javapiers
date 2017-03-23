package com.xianyue.springboot.controller;

import com.xianyue.springboot.domain.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Xianyue
 */
@RestController
public class GreetController {
    private final static String tempate = "Hello, %s";
    private final AtomicInteger counter = new AtomicInteger(0);

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value = "name", required = false) String name) {
        return new Greeting(counter.incrementAndGet(), String.format(tempate, name));
    }
}
