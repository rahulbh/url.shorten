package sg.gov.tech.url.shorten.exception;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
public class InconsistentRequestException extends RuntimeException {

    public static Logger logger = LoggerFactory.getLogger(InconsistentRequestException.class);

    public InconsistentRequestException(String errorMessage) {
        super(errorMessage);
    }

}
