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
import sg.gov.tech.url.shorten.model.ResponseWrapper;
import sg.gov.tech.url.shorten.processor.UrlProcessor;

@SpringBootTest
@AutoConfigureMockMvc

class UrlControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private UrlProcessor urlProcessor;

	@Test
	public void doesDirectToIndexPage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Url Shortener for Gov Tech")));
	}

	@Test
	public void doesRedirectToErrorPage() throws Exception {
		ResponseWrapper responseWrapper = ResponseWrapper.builder().isValid(false).build();
		given(urlProcessor.retrieveUrl("PLEASEDONTEVEREXISTINDB")).willReturn(responseWrapper);

		this.mockMvc.perform(get("/PLEASEDONTEVEREXISTINDB"))
				.andDo(print())
				.andExpect(redirectedUrl("/error/redirect"))
				.andExpect(status().isNotFound());
	}

}
