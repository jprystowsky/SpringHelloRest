package io.mapping.apps.SpringHelloRest.controller;

import io.mapping.apps.SpringHelloRest.domain.Message;
import io.mapping.apps.SpringHelloRest.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MessageControllerImpl implements MessageController {
	@Autowired private MessageService mMessageService;

	@RequestMapping("/")
	public Message getDefaultMessage() {
		return mMessageService.getMessage();
	}

	@RequestMapping("/message/{message}")
	public Message getMessage(@PathVariable("message") String message, HttpServletRequest request) {
		return mMessageService.getMessage(message);
	}
}
