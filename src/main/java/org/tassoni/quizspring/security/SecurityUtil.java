package org.tassoni.quizspring.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.tassoni.quizspring.model.User;

public class SecurityUtil {
	
	private SecurityUtil() {}
	
	public static User getUser() {
		AppUserDetails userDetails = (AppUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		User user = userDetails.getUser();
		if(user == null || user.isNew()) {
			throw new UserNotAuthenticatedException("No user found");
		}
		return user;
	}

}
