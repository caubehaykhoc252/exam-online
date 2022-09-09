package com.ntrungthanh1.subjectTest.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}
}
