package sg.gov.tech.url.shorten.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import sg.gov.tech.url.shorten.exception.InconsistentRequestException;
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
    public ResponseWrapper processCreateRequest(String longUrl) throws InconsistentRequestException {

        ResponseWrapper.ResponseWrapperBuilder responseWrapperBuilder = ResponseWrapper.builder();

        boolean isValid = urlService.validate(longUrl);

        responseWrapperBuilder.isValid(isValid);

        if (isValid) {
            try {
                Url generatedUrl = urlService.generateAndPersist(longUrl);
                responseWrapperBuilder.url(generatedUrl);
                return responseWrapperBuilder.build();
            } catch (Exception exception) {
                throw new InconsistentRequestException("Your URL is too long or has invalid characters. Please check your URL and try again");
            }
        }
        else
            throw new InconsistentRequestException("Input Url is not valid. Please recheck your url and ensure it has Protocol and Top level domain included");
    }

    @Override
    public ResponseWrapper retrieveUrl(String shortUrl) throws DataAccessException, InconsistentRequestException {
        ResponseWrapper.ResponseWrapperBuilder responseWrapperBuilder = ResponseWrapper.builder();

        String id = BaseConversionUtility.decodeTo10(shortUrl.toCharArray());

        Url url = urlService.retrieveById(id);
        if (Objects.nonNull(url)) {
            responseWrapperBuilder.url(url);
            responseWrapperBuilder.isValid(true);
            return responseWrapperBuilder.build();
        } else {
            throw new InconsistentRequestException();
        }
    }


}
