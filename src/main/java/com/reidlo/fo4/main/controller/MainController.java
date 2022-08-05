package com.reidlo.fo4.main.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    private final Logger log = Logger.getLogger(getClass());

    @GetMapping(value = "/")
    public String home() {
        log.info("MainController homepage");
        return "index";
    }

    @GetMapping(value = "/details/pvp/{nickname}/{level}/{pvpName}/{pvpDate}")
    public String pvpDetails(@PathVariable String nickname, @PathVariable String level, @PathVariable String pvpName, @PathVariable String pvpDate) {
        log.info("move to pvpDetails");
        log.info("Details pvpPath : " + nickname + ", " + level + ", " + pvpName + ", " + pvpDate);
        return "/match/pvpDetails";
    }

    @GetMapping(value = "/details/coach/{nickname}/{level}/{coachName}/{coachDate}")
    public String coachDetails(@PathVariable String nickname, @PathVariable String level, @PathVariable String coachName, @PathVariable String coachDate) {
        log.info("move to coachDetails");
        log.info("Details coachPath : " + nickname + ", " + level + ", " + coachName + ", " + coachDate);
        return "/match/coachDetails";
    }
}
