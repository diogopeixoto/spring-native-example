package com.github.diogopeixoto.controller.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date date;
	private String detail;
	private String message;

	public ExceptionResponse(Date date, String message, String detail) {
		super();
		this.date = date;
		this.message = message;
		this.detail = detail;
	}

	public Date getDate() {
		return date;
	}

	public String getDetail() {
		return detail;
	}

	public String getMessage() {
		return message;
	}
}
