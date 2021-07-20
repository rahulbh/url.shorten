package sg.gov.tech.url.shorten;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sg.gov.tech.url.shorten.exception.InconsistentRequestException;
import sg.gov.tech.url.shorten.model.ResponseWrapper;
import sg.gov.tech.url.shorten.processor.UrlProcessor;

@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void doesDirectToIndexPage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Url Shortener for Gov Tech")));
	}


}
