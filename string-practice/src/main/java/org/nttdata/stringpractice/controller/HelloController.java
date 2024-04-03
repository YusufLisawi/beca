package org.nttdata.stringpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Map<String, String> model) {
        model.put("message", "helloooooo");
        model.put("name", "yusuf");
        return "hello";
    }
}
