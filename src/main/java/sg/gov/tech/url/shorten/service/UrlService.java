package sg.gov.tech.url.shorten.service;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.gov.tech.url.shorten.model.Url;
import sg.gov.tech.url.shorten.repository.UrlDao;
import sg.gov.tech.url.shorten.utility.BaseConversionUtility;

import java.util.List;

@Service
public class UrlService implements BaseService<String, Url>{

    public static Logger logger = LoggerFactory.getLogger(UrlService.class);

    @Autowired
    UrlDao urlDao;

    @Override
    public boolean validate(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }

    @Override
    public Url generateAndPersist(String longUrl){
        Url url = Url.builder().longUrl(longUrl).build();
        Integer idInBase10 = urlDao.insert(longUrl);
        url.setShortUrl("http://localhost:8080/" + BaseConversionUtility.encodeTo62(idInBase10));
        logger.info("Generated Url: [{}]", url);
        return url;
    }

    @Override
    public Url retrieveById(String id) {
        List<Url> urls = urlDao.fetch(id);
        return !urls.isEmpty() ? urls.get(0): null;
    }


}
