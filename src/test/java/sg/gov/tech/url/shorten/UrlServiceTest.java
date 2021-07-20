package sg.gov.tech.url.shorten;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import sg.gov.tech.url.shorten.service.UrlService;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Test
    public void testValidUri(){
        UrlService UrlService = new UrlService();
        Assert.assertTrue(UrlService.validate("https://www.hblahblggahblahblahblahbbbbbblahblahhblahblah.com/blahblahblahblahblahblahhblahblahblahblahblahbbbbbblahblahhblahblah/blahblahblahblahblahblahblahblah/blahblahblahblahblahblah/blahblahbahblahbahblahblahblahblahblahblahblahblahblahblahblahblahblah"));
    }

    @Test
    public void testInvalidUri(){
        UrlService UrlService = new UrlService();
        Assert.assertFalse(UrlService.validate("www.hblahblggahblahblahblahbbbbbblahblahhblahblah.com/blahblahblahblahblahblahhblahblahblahblahblahbbbbbblahblahhblahblah/blahblahblahblahblahblahblahblah/blahblahblahblahblahblah/blahblahbahblahbahblahblahblahblahblahblahblahblahblahblahblahblahblah"));
    }
}
