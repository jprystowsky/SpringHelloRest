package io.mapping.apps.SpringHelloRest.service;

import io.mapping.apps.SpringHelloRest.domain.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class MessageServiceImplTest {
	@Configuration
	static class ContextConfiguration {
		@Bean
		MessageService messageService() {
			return new MessageServiceImpl();
		}
	}

	@Autowired private MessageService mMessageService;

	@Before
	public void setUp() throws Exception {
		assertNotNull("Service shouldn't be null", mMessageService);
	}

	@Test
	public void shouldCreateHelloWorldMessageByDefault() throws Exception {
		String defaultMessage = "Hello, world!";

		Message message = mMessageService.getMessage();

		assertNotNull(message);
		assertEquals(String.format("Default message should equal '%s'", defaultMessage), defaultMessage, message.getMessage());
	}

	@Test
	public void shouldCreateMessageMatchingInputMessageString() throws Exception {
		String inputMessage = "Input message";

		Message message = mMessageService.getMessage(inputMessage);

		assertNotNull(message);
		assertEquals("Message's message should equal input message", inputMessage, message.getMessage());
	}
}
