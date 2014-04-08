package io.mapping.apps.SpringHelloRest.service;

import io.mapping.apps.SpringHelloRest.domain.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
	public Message getMessage() {
		return new Message();
	}

	public Message getMessage(String message) {
		return new Message(message);
	}
}
