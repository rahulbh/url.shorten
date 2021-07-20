package sg.gov.tech.url.shorten.processor;

import sg.gov.tech.url.shorten.exception.InconsistentRequestException;
import sg.gov.tech.url.shorten.model.ResponseWrapper;

public interface IBaseProcessor <T, U>{

    U processCreateRequest(T t) throws InconsistentRequestException;
    U retrieveUrl(T t) throws InconsistentRequestException;
}
