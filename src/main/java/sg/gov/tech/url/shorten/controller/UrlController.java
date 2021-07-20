package sg.gov.tech.url.shorten.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sg.gov.tech.url.shorten.exception.InconsistentRequestException;
import sg.gov.tech.url.shorten.model.ResponseWrapper;
import sg.gov.tech.url.shorten.model.Url;
import sg.gov.tech.url.shorten.processor.UrlProcessor;


@Controller
public class UrlController {

    @Autowired
    UrlProcessor urlProcessor;

    @SneakyThrows
    @PostMapping("/createShortUrl")
    public String createShortUrl(@ModelAttribute Url url, Model model) {

        try {
            ResponseWrapper responseWrapper = urlProcessor.processCreateRequest(url.getLongUrl());
            model.addAttribute("responseWrapper", responseWrapper);
            return "result";
        } catch (InconsistentRequestException exception) {
            throw new InconsistentRequestException(exception.getMessage());
        }
    }

}
