package com.antonchar.userservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.antonchar.userservice.entity.User;
import com.antonchar.userservice.repository.UserRepository;
import com.antonchar.userservice.service.PermissionService;
import com.antonchar.userservice.service.PermissionServiceImpl;
import com.antonchar.userservice.service.UserService;
import com.antonchar.userservice.service.UserServiceImpl;

import static com.antonchar.userservice.TestDataHelper.USER_ADM;
import static com.antonchar.userservice.TestDataHelper.USER_SADM;
import static com.antonchar.userservice.TestDataHelper.USER_USR;
import static com.antonchar.userservice.TestDataHelper.USER_USR_BL;

@Configuration
public class IntegrationTestConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl() {

            @Autowired(required = false)
            private UserRepository userRepository;

            @Override
            public Optional<User> findByEmail(String email) {
                switch (email) {
                    case "admin@example.com":
                        return Optional.of(USER_ADM);
                    case "superadmin@example.com":
                        return Optional.of(USER_SADM);
                    case "user-blocked@example.com":
                        return Optional.of(USER_USR_BL);
                    case "user@example.com":
                        return Optional.of(USER_USR);
                    default:
                        return Optional.empty();
                }
            }
        };
    }

    @Bean
    public PermissionService permissionService() {
        return new PermissionServiceImpl();
    }
}
