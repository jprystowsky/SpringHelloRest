package io.mapping.apps.SpringHelloRest.service;

import io.mapping.apps.SpringHelloRest.domain.Message;

public interface MessageService {
	/**
	 * Gets the default Message.
	 * @return A Message instantiated with "Hello, world!"
	 */
	public Message getMessage();

	/**
	 * Gets a message initialized with a String message.
	 * @param message the String with which to instantiate the Message.
	 * @return a Message instantiated with {@code message}.
	 */
	public Message getMessage(String message);
}
