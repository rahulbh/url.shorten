package sg.gov.tech.url.shorten.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    private String id;
    private String longUrl;
    private String shortUrl;
}
