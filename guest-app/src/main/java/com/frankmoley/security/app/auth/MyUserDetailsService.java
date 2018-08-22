package com.frankmoley.security.app.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frankmoley.security.app.dao.AuthGroup;
import com.frankmoley.security.app.dao.AuthGroupRepository;
import com.frankmoley.security.app.dao.User;
import com.frankmoley.security.app.dao.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	private AuthGroupRepository authGroupRepository;
	
	@Autowired
	public MyUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
		super();
		this.userRepository = userRepository;
		this.authGroupRepository = authGroupRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("cannot find username: " + username);
		}
		
		List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
		
		return new MyUserDetails(user, authGroups);
	}

}
