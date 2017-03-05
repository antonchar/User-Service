package com.antonchar.userservice.config;

import com.antonchar.userservice.services.PermissionService;
import com.antonchar.userservice.services.PermissionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.antonchar.userservice.services.dto.CurrentUser;

import static com.antonchar.userservice.TestDataHelper.*;

@Configuration
public class TestConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            switch (email) {
                case "admin@example.com": return new CurrentUser(USER_ADM);
                case "superadmin@example.com": return new CurrentUser(USER_SADM);
                case "user-blocked@example.com": return new CurrentUser(USER_USR_BL);
                case "user@example.com": return new CurrentUser(USER_USR);
                default: throw new UsernameNotFoundException("Fail to authenticate user");
            }
        };
    }

    @Bean
    public PermissionService permissionService() {
        return new PermissionServiceImpl();
    }
}
