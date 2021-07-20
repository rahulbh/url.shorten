package sg.gov.tech.url.shorten.controller;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import sg.gov.tech.url.shorten.exception.InconsistentRequestException;
import sg.gov.tech.url.shorten.model.ResponseWrapper;
import sg.gov.tech.url.shorten.model.Url;
import sg.gov.tech.url.shorten.processor.UrlProcessor;


@Controller
public class RedirectController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UrlProcessor urlProcessor;

    @GetMapping("/")
    public String indexPage(Model model){
        model.addAttribute("url", new Url());
        return "index";
    }

    @SneakyThrows
    @GetMapping("/{shortUrl:.*}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        logger.info("ShortUrl: [{}]", shortUrl);

        try{
            final RedirectView redirectView = new RedirectView("", true);
            ResponseWrapper responseWrapper = urlProcessor.retrieveUrl(shortUrl);
            redirectView.setUrl(responseWrapper.getUrl().getLongUrl());
            return redirectView;
        }
        catch (InconsistentRequestException exception){
            throw new InconsistentRequestException("Error fetching long Url for the shortened input, please recheck your input");
        }
    }

}
