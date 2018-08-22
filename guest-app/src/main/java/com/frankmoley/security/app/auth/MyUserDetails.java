package com.frankmoley.security.app.auth;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.frankmoley.security.app.dao.AuthGroup;
import com.frankmoley.security.app.dao.User;

public class MyUserDetails implements UserDetails{
	
	/** this is customed user bean in your system **/
	private User user;
	/** this is customed role bean in your system **/
	private List<AuthGroup> authGroups;
	
	public MyUserDetails(User user, List<AuthGroup> authGroups) {
		super();
		this.user = user;
		this.authGroups = authGroups;
	}

	/**
	 * specify role owned by this user
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (null == this.authGroups) {
			return Collections.emptySet();
		}
		
		/** let's convert customed beans into spring-security-format bean **/
		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
		this.authGroups.forEach(group->{
			grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
		});
		
		return grantedAuthorities;
	}

	/**
	 * convert User entity to UserDetails format
	 */
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	/**
	 * convert User entity to UserDetails format 
	 */
	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
