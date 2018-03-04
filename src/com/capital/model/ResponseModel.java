package com.capital.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
@Scope(value = "prototype")
@JsonInclude(Include.NON_NULL)
public class ResponseModel<T>  {

	private T responseData;

	private String responseMessage;
	
	private String responseStatus;

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public T getResponseData() {
		return responseData;
	}

	public void setResponseObject(T responseObject) {
		this.responseData = responseObject;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "Res Object : " + responseData + " Res Message : " + responseMessage;
	}

}
