package com.bucarcep.teste.exception;

public class CepNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CepNotFoundException(String message) {
		super(message);
	}
}