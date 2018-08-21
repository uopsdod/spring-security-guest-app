package com.frankmoley.security.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frankmoley.security.app.dao.User;
import com.frankmoley.security.app.dao.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	@Autowired
	public MyUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("cannot find username: " + username);
		}
		return new MyUserDetails(user);
	}

}
