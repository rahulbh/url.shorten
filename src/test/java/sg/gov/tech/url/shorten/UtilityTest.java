package sg.gov.tech.url.shorten;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sg.gov.tech.url.shorten.utility.BaseConversionUtility;


@RunWith(SpringJUnit4ClassRunner.class)
public class UtilityTest {

    @Test
    public void testEncode(){
        Assert.assertEquals("ba",BaseConversionUtility.encodeTo62(62));
    }

    @Test
    public void testDecode(){
        Assert.assertEquals("3650",BaseConversionUtility.decodeTo10(new char[]{'6','2'}));
    }
}
