package com.github.diogopeixoto.controller.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

class ExceptionResponseTest {

	@Test
	void constructor_ShouldSetAttributesValues() {
		Date date = new Date();
		String detail = "Detail";
		String message = "Message";

		ExceptionResponse response = new ExceptionResponse(date, message, detail);

		assertEquals(date, response.getDate());
		assertEquals(detail, response.getDetail());
		assertEquals(message, response.getMessage());
	}
}
