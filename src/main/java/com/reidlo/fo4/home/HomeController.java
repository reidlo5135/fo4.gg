package com.reidlo.fo4.home;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final Logger log = Logger.getLogger(getClass());

    @GetMapping(value = "/")
    public String home() {
        log.info("HOME!");
        return "index";
    }
}
