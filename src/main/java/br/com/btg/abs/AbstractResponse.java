package br.com.btg.abs;

import org.springframework.http.HttpStatus;

public class AbstractResponse {

	private HttpStatus httpCode;
	private String httpMessage;

	public HttpStatus getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(HttpStatus found) {
		this.httpCode = found;
	}

	public String getHttpMessage() {
		return httpMessage;
	}

	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}

}
