package br.com.btg.exception;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8352250846356199728L;

	public ValidationException(String message) {
		super(message);
	}

}
