package io.mapping.apps.SpringHelloRest.filter;

import io.mapping.apps.SpringHelloRest.controller.MessageController;
import io.mapping.apps.SpringHelloRest.controller.MessageControllerImpl;
import io.mapping.apps.SpringHelloRest.domain.Message;
import io.mapping.apps.SpringHelloRest.service.MessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class CorsFilterTest {
	@Configuration
	static class ContextConfiguration {
		@Autowired
		ApplicationContext mApplicationContext;

		@Bean
		MessageControllerImpl messageControllerImpl() {
			return new MessageControllerImpl();
		}

		@Bean
		@Qualifier("interface")
		MessageController messageController() {
			return mApplicationContext.getBean(MessageControllerImpl.class);
		}

		@Bean
		MessageService messageService() {
			final MessageService messageService = mock(MessageService.class);

			when(messageService.getMessage()).thenReturn(new Message());

			return messageService;
		}

		@Bean
		CorsFilter corsFilter() {
			return new CorsFilter();
		}
	}

	@Autowired @Qualifier("interface") private MessageController mMessageController;
	@Autowired CorsFilter mCorsFilter;

	private MockMvc mMockMvc;

	@Before
	public void setUp() throws Exception {
		mMockMvc = MockMvcBuilders
				.standaloneSetup(mMessageController)
				.addFilters(mCorsFilter)
				.build();

		assertNotNull("MockMvc shouldn't be null", mMockMvc);
	}

	@Test
	public void shouldAddCorsHeaders() throws Exception {
		mMockMvc.perform(get("/"))
				.andExpect(header().string("Access-Control-Allow-Origin", "*"))
				.andExpect(header().string("Access-Control-Allow-Methods", "GET"))
				.andExpect(header().string("Access-Control-Max-Age", "3600"))
				.andExpect(header().string("Access-Control-Allow-Headers", "x-requested-with"));
	}
}
