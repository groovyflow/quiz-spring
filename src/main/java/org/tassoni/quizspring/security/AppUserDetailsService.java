package org.tassoni.quizspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tassoni.quizspring.model.User;
import org.tassoni.quizspring.repository.BasicRepository;

//See https://github.com/rwinch/gs-spring-security-3.2/blob/master/src/main/java/sample/security/UserRepositoryUserDetailsService.java
@Service("userDetailsService")
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private BasicRepository basicRepository;
	
	@Override
	/*@Transactional(readOnly = true)*/
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = basicRepository.findUserByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException(username + " not found");
		
		return new AppUserDetails(user);
	}

}
