package sg.gov.tech.url.shorten.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import sg.gov.tech.url.shorten.model.ResponseWrapper;
import sg.gov.tech.url.shorten.model.Url;
import sg.gov.tech.url.shorten.processor.UrlProcessor;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/{shortUrl:.*}")
    public RedirectView redirect(@PathVariable String shortUrl, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){
        logger.info("ShortUrl: [{}]", shortUrl);
        ResponseWrapper responseWrapper = urlProcessor.retrieveUrl(shortUrl);

        final RedirectView redirectView = new RedirectView("", true);
        if (responseWrapper.isValid()){
            redirectView.setUrl(responseWrapper.getUrl().getLongUrl());
        }
        else
        {
            redirectView.setUrl("/error/redirect");
            redirectAttributes.addFlashAttribute("responseWrapper", responseWrapper);
            request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.NOT_FOUND);
        }
        return redirectView;
    }

}
