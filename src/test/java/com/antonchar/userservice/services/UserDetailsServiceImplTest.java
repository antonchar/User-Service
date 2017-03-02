package com.antonchar.userservice.services;

import com.antonchar.userservice.services.dto.CurrentUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.antonchar.userservice.TestDataHelper.USER_USR;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadExistingUser() throws Exception {
        when(userService.findByEmail(anyString())).thenReturn(Optional.of(USER_USR));
        assertThat(userDetailsService.loadUserByUsername("dummy@mail"), is(new CurrentUser(USER_USR)));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadMissingUser() throws Exception {
        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThat(userDetailsService.loadUserByUsername("dummy@mail"), is(new CurrentUser(USER_USR)));
    }
}