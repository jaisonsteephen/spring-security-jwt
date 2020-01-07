package io.javabrains.springsecurityjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurityjwt.util.JwtUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	
        return new User("foo", "foo", getAuthority());
    }
    
    private Set getAuthority() {
    	List<String> roles=jwtUtil.extractRoles();
    	if(roles==null) {
    		return new HashSet();
    	}
        Set authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(roles.get(0)));
		return authorities;
	}
}