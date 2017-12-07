package com.antonchar.userservice.service;

import com.antonchar.userservice.service.dto.CurrentUser;
import org.junit.Test;

import static com.antonchar.userservice.TestDataHelper.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PermissionServiceImplTest {

    private final static Long ID = 100L;

    private PermissionService service = new PermissionServiceImpl();

    @Test
    public void testCanReadUserDetails() throws Exception {
        assertTrue(service.canReadUserDetails(new CurrentUser(USER_SADM), ID));
        assertTrue(service.canReadUserDetails(new CurrentUser(USER_ADM), ID));
        assertTrue(service.canReadUserDetails(new CurrentUser(getNewUserUsr(ID)), ID));
    }

    @Test
    public void testCannotReadUserDetails() throws Exception {
        assertFalse(service.canReadUserDetails(null, ID));
        assertFalse(service.canReadUserDetails(new CurrentUser(USER_SADM), null));
        assertFalse(service.canReadUserDetails(new CurrentUser(USER_USR), ID));
    }

    @Test
    public void testCanWriteUserDetails() throws Exception {
        assertTrue(service.canWriteUserDetails(new CurrentUser(USER_SADM), ID));
        assertTrue(service.canWriteUserDetails(new CurrentUser(getNewUserAdm(ID)), ID));
        assertTrue(service.canWriteUserDetails(new CurrentUser(getNewUserUsr(ID)), ID));
    }

    @Test
    public void testCannotWriteUserDetails() throws Exception {
        assertFalse(service.canWriteUserDetails(null, ID));
        assertFalse(service.canWriteUserDetails(new CurrentUser(USER_SADM), null));
        assertFalse(service.canWriteUserDetails(new CurrentUser(USER_ADM), ID));
        assertFalse(service.canWriteUserDetails(new CurrentUser(USER_USR), ID));
    }
}