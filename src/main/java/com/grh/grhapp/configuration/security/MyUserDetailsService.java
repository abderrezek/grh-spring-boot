package com.grh.grhapp.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grh.grhapp.entities.User;
import com.grh.grhapp.exceptions.UserNotFoundException;
import com.grh.grhapp.services.IUserService;

@Service
@Transactional(readOnly = true)
public class MyUserDetailsService implements UserDetailsService {

	private final IUserService userService;
	
	@Autowired
	public MyUserDetailsService(IUserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByEmail(username)
				.orElseThrow(() -> new UserNotFoundException(username));
		
		return new ApplicationUserDetails(user);
	}

}
