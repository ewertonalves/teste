package com.bucarcep.teste.model;

public class MessageResponse {

	private String message;

	public MessageResponse(String String) {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageResponse [message=" + message + "]";
	}

}
