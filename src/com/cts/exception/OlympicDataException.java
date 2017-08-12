package com.cts.exception;

public class OlympicDataException extends Exception{
	private static final long serialVersionUID = 1L;

	public OlympicDataException(final String errorMessage) {
		super(errorMessage);
	}
}
