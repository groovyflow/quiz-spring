package org.tassoni.quizspring.security;

public class UserNotAuthenticatedException extends RuntimeException{
	private static final long serialVersionUID = 2383270738937880128L;

	public UserNotAuthenticatedException() {}
	public UserNotAuthenticatedException(String message) {
		super(message);
	}

}
