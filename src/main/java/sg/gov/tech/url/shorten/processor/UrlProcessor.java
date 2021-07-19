package sg.gov.tech.url.shorten.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.url.shorten.model.ResponseWrapper;
import sg.gov.tech.url.shorten.model.Url;
import sg.gov.tech.url.shorten.service.UrlService;
import sg.gov.tech.url.shorten.utility.BaseConversionUtility;

import java.util.Objects;

@Component
public class UrlProcessor implements IBaseProcessor<String, ResponseWrapper> {

    @Autowired
    UrlService urlService;

    @Override
    public ResponseWrapper processCreateRequest(String longUrl) {

        ResponseWrapper.ResponseWrapperBuilder responseWrapperBuilder = ResponseWrapper.builder();
        boolean isValid = urlService.validate(longUrl);
        responseWrapperBuilder.isValid(isValid);

        if (isValid) {
            Url generatedUrl = urlService.generateAndPersist(longUrl);
            responseWrapperBuilder.url(generatedUrl);
        }
        return responseWrapperBuilder.build();

    }

    @Override
    public ResponseWrapper retrieveUrl(String shortUrl){
        ResponseWrapper.ResponseWrapperBuilder responseWrapperBuilder = ResponseWrapper.builder();

        String id = BaseConversionUtility.decodeTo10(shortUrl.toCharArray());
        Url url = urlService.retrieveById(id);
        if (Objects.nonNull(url)){
            responseWrapperBuilder.url(url);
            responseWrapperBuilder.isValid(true);
        }
        else
        {
            responseWrapperBuilder.isValid(false);
        }
        return responseWrapperBuilder.build();
    }


}
