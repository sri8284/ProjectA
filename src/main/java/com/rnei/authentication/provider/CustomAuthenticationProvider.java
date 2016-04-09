package com.rnei.authentication.provider;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rnei.model.User;
import com.rnei.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
    private UserService userService;
	
	@Autowired
	@Qualifier("encoder")
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 String username = authentication.getName();
         String password = (String) authentication.getCredentials();
         if (username == null) {
             throw new BadCredentialsException("UserId is Mandatory.");
         }
         if (password == null) {
             throw new BadCredentialsException("password is Mandatory.");
         }
         username = username.toUpperCase();
         User user = userService.loadUserByUsername(username);
	     
         if (user == null) {
             throw new BadCredentialsException("UserId not found.");
         }
         
         if(!user.getUsername().equalsIgnoreCase(username)){
             throw new BadCredentialsException("UserId not found.");
         }
         
         if (!passwordEncoder.matches(password, user.getPassword())) {
             throw new BadCredentialsException("Wrong password.");
         }
         Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
         
         return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
