package io.mapping.apps.SpringHelloRest.domain;

import org.springframework.stereotype.Component;

@Component
public class Message {
	private String mMessage;

	public Message() {
		mMessage = "Hello, world!";
	}

	public Message(String message) {
		mMessage = message;
	}

	public String getMessage() {
		return mMessage;
	}

	public void setMessage(final String message) {
		mMessage = message;
	}
}
