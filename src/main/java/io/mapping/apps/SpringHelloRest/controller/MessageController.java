package io.mapping.apps.SpringHelloRest.controller;

import io.mapping.apps.SpringHelloRest.domain.Message;

import javax.servlet.http.HttpServletRequest;

public interface MessageController {
	/**
	 * Returns a Message containing "Hello, world!"
	 * @return a default instance of {@link io.mapping.apps.SpringHelloRest.domain.Message}.
	 */
	public Message getDefaultMessage();

	/**
	 * Returns a message containing the given input message.
	 * @param message the contents of the created Message.
	 * @param request the incoming servlet request.
	 * @return a {@link io.mapping.apps.SpringHelloRest.domain.Message} containing the input message.
	 */
	public Message getMessage(String message, HttpServletRequest request);
}
