package sg.gov.tech.url.shorten.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sg.gov.tech.url.shorten.model.ResponseWrapper;
import sg.gov.tech.url.shorten.model.Url;
import sg.gov.tech.url.shorten.processor.UrlProcessor;

@Controller
public class UrlController {

    @Autowired
    UrlProcessor urlProcessor;

    @PostMapping("/createShortUrl")
    public String createShortUrl(@ModelAttribute Url url, Model model){

        ResponseWrapper responseWrapper = urlProcessor.processCreateRequest(url.getLongUrl());
        model.addAttribute("responseWrapper", responseWrapper);
        return "result";
    }

}
