package sg.gov.tech.url.shorten.service;

import sg.gov.tech.url.shorten.model.Url;

public interface BaseService <T, U>{
    public boolean validate(T t);
    public U generateAndPersist(T t);
    public U retrieveById(T t);
}
