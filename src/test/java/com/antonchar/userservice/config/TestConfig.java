package com.antonchar.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.antonchar.userservice.services.dto.CurrentUser;

import static com.antonchar.userservice.UserTestData.*;

@Configuration
public class TestConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            switch (email) {
                case "vasilypup@example.com": return new CurrentUser(USER_1);
                case "superbratan@example.com": return new CurrentUser(USER_2);
                case "johnnny@example.com": return new CurrentUser(USER_3);
                case "avo.pots@example.com": return new CurrentUser(USER_4);
                default: throw new UsernameNotFoundException("Fail to authenticate user");
            }
        };
    }
}
