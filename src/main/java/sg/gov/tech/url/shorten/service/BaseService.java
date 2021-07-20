package sg.gov.tech.url.shorten.service;

import sg.gov.tech.url.shorten.model.Url;

import java.sql.SQLException;

public interface BaseService <T, U>{
    public boolean validate(T t);
    public U generateAndPersist(T t) throws SQLException, Exception;
    public U retrieveById(T t);
}
