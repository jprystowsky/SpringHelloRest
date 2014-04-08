package io.mapping.apps.SpringHelloRest.service;

import io.mapping.apps.SpringHelloRest.domain.Message;

public interface MessageService {
	public Message getMessage();
	public Message getMessage(String message);
}
