package com.antonchar.userservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.antonchar.userservice.services.dto.CurrentUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        if ("avo@pots.ee".equals(email)) {
            return new CurrentUser();
        } else {
            throw new UsernameNotFoundException(String.format("User with email=%s was not found", email));
        }
    }
}
