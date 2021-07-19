package sg.gov.tech.url.shorten.processor;

import sg.gov.tech.url.shorten.model.ResponseWrapper;

public interface IBaseProcessor <T, U>{

    U processCreateRequest(T t);
    U retrieveUrl(T t);
}
