package sg.gov.tech.url.shorten.processor;

import sg.gov.tech.url.shorten.exception.InconsistentRequestException;

public interface IBaseProcessor <T, U>{

    U generateUrl(T t) throws InconsistentRequestException;
    U retrieveUrl(T t) throws InconsistentRequestException;
}
