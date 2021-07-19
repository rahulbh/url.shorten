package sg.gov.tech.url.shorten.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContextUtils;
import sg.gov.tech.url.shorten.model.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/error")
public class ErrorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/redirect")
    public String redirect(Model model, HttpServletRequest request){
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            ResponseWrapper responseWrapper = (ResponseWrapper) inputFlashMap.get("responseWrapper");
            logger.debug("Redirect Model: [{}]", responseWrapper);
            model.addAttribute("responseWrapper", responseWrapper);
        }
        return "error";
    }
}
