package com.antonchar.userservice.config;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.antonchar.userservice.service.UserService;
import com.antonchar.userservice.service.dto.CurrentUser;

import static com.antonchar.userservice.TestDataHelper.USER_USR;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityConfigTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private SecurityConfig config = new SecurityConfig();

    @Test
    public void testUserDetailsServiceLoadExistingUser() {
        // Given
        UserDetailsService userDetailsService = config.userDetailsService();
        when(userService.findByEmail(USER_USR.getEmail())).thenReturn(Optional.of(USER_USR));

        // When
        CurrentUser user = (CurrentUser) userDetailsService.loadUserByUsername(USER_USR.getEmail());

        // Then
        assertThat(user, is(new CurrentUser(USER_USR)));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testUserDetailsServiceLoadMissingUser() {
        // Given
        UserDetailsService userDetailsService = config.userDetailsService();
        when(userService.findByEmail(any(String.class))).thenReturn(Optional.empty());

        // When
        userDetailsService.loadUserByUsername("dummy@mail");

        // Then Exception
    }

    @Test
    public void testPasswordEncoder() {
        // Given
        PasswordEncoder encoder = config.passwordEncoder();

        // Then
        assertThat(encoder, instanceOf(BCryptPasswordEncoder.class));
    }
}