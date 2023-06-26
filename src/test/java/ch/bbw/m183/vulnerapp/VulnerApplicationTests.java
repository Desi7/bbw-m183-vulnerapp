package ch.bbw.m183.vulnerapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VulnerApplicationTests {

    TestRestTemplate restTemplate;
    URL base;
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        restTemplate = new TestRestTemplate("user", "password");
        base = new URL("http://localhost:" + port);
    }

    @Test
    void contextLoads() {
    }


    /**
     * -------------------------------------------
     * Tests von den Tutorials
     */

    @Test
    public void whenLoggedUserRequestsHomePage_ThenSuccess()
            throws IllegalStateException, IOException {
        ResponseEntity<String> response =
                restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Baeldung"));
    }

    @Test
    public void whenUserWithWrongCredentials_thenUnauthorizedPage()
            throws Exception {

        restTemplate = new TestRestTemplate("user", "wrongpassword");
        ResponseEntity<String> response =
                restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody().contains("Unauthorized"));
    }

    /**
     * -------------------------------------------
     * Tests von den Tutorials
     *

    @Test
    @WithMockUser(username = "john", roles = {"VIEWER"})
    public void givenRoleViewer_whenCallGetUsername_thenReturnUsername() {
        String userName = userRoleService.getUsername();

        assertEquals("john", userName);
    }

    @Test
    @WithMockUser(username = "JOHN", authorities = {"SYS_ADMIN"})
    public void givenAuthoritySysAdmin_whenCallGetUsernameLC_thenReturnUsername() {
        String username = userRoleService.getUsernameInLowerCase();

        assertEquals("john", username);
    }

    @Test(expected = AccessDeniedException.class)
    @WithAnonymousUser
    public void givenAnomynousUser_whenCallGetUsername_thenAccessDenied() {
        userRoleService.getUsername();
    }

    @Test
    @WithUserDetails(
            value = "john",
            userDetailsServiceBeanName = "userDetailService")
    public void whenJohn_callLoadUserDetail_thenOK() {

        CustomUser user = userService.loadUserDetail("jane");

        assertEquals("jane", user.getNickName());
    }

    @Test
    @WithMockJohnViewer
    public void givenMockedJohnViewer_whenCallGetUsername_thenReturnUsername() {
        String userName = userRoleService.getUsername();

        assertEquals("john", userName);
    }
*/
}
