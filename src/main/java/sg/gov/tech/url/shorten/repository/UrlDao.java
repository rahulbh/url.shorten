package sg.gov.tech.url.shorten.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sg.gov.tech.url.shorten.model.Url;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UrlDao implements BaseRepositoryDao<String, Integer, List<Url>> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer insert(String longUrl){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("URL").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("LONGURL", longUrl);
        parameters.put("CREATIONTIME", LocalDateTime.now());

        return simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(
                parameters)).intValue();
    }

    @Override
    public Integer delete(String shortUrl){
        return jdbcTemplate.update("Delete from URL where SHORTURL = ?", shortUrl);
    }

    @Override
    public List<Url> fetch(String id) {
        return jdbcTemplate.query("Select ID, LONGURL from URL where ID = ?", new BeanPropertyRowMapper<>(Url.class), id);
    }

}
