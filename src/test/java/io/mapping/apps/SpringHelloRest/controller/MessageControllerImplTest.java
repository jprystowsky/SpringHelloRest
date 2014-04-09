package io.mapping.apps.SpringHelloRest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@EnableAutoConfiguration
public class MessageControllerImplTest {
	@Configuration
	static class ContextConfiguration {
		@Autowired
		ApplicationContext mApplicationContext;

		@Bean
		MessageControllerImpl messageControllerImpl() {
			return new MessageControllerImpl();
		}

		/**
		 * The "interface" qualifier is necessary so that Spring DI doesn't get confused with dual implementations of
		 * MessageController
		 */
		@Bean
		@Qualifier("interface")
		MessageController messageController() {
			return mApplicationContext.getBean(MessageControllerImpl.class);
		}

		@Bean
		MessageService messageService() {
			MessageService mockMessageService = mock(MessageService.class);

			when(mockMessageService.getMessage()).thenReturn(new Message());

			String helloSpring = "Hello, Spring!";
			when(mockMessageService.getMessage(helloSpring)).thenReturn(new Message(helloSpring));

			return mockMessageService;
		}
	}

	@Autowired @Qualifier("interface") private MessageController mMessageController;

	private MockMvc mMockMvc;
	private ObjectMapper mMapper;

	@Before
	public void setUp() throws Exception {
		mMockMvc = MockMvcBuilders.standaloneSetup(mMessageController).build();
		mMapper = new ObjectMapper();

		assertNotNull("MockMvc shouldn't be null", mMockMvc);
	}

	@Test
	public void shouldReturnHelloWorldMessageAtRootPath() throws Exception {
		final MvcResult mvcResult = mMockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
				.andReturn();

		final Message message = mMapper.readValue(mvcResult.getResponse().getContentAsString(), Message.class);

		assertEquals("Message should be 'Hello, world!'", "Hello, world!", message.getMessage());
	}

	@Test
	public void shouldReturnHelloSpringWhenAtPath() throws Exception {
		final String helloSpring = "Hello, Spring!";

		final MvcResult mvcResult = mMockMvc.perform(get("/message/" + helloSpring))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
				.andReturn();

		final Message message = mMapper.readValue(mvcResult.getResponse().getContentAsString(), Message.class);

		assertEquals("Message should be '" + helloSpring +  "'", helloSpring, message.getMessage());
	}
}
