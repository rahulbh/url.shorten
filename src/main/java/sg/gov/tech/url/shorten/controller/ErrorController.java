package sg.gov.tech.url.shorten.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/redirect")
    public String redirect(Model model){
        logger.info("Redirect Model: [{}]", model);
        return "error";
    }
}
