package ch.bbw.m183.vulnerapp;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.HttpHeaders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VulnerApplicationTests implements WithAssertions {

    @Test
    void contextLoads() {
    }

    @Autowired
    WebTestClient webTestClient;

    @Test
    void blogsArePublic() {
        webTestClient.get().uri("/api/blog")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void onlyAdminsOnAdminPage() {
        webTestClient.get().uri("/api/admin123")
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    public void getAllUsersWithoutAdminRights() {
        webTestClient.get().uri("/api/admin123/users")
                .headers(TestHelper::addUserCredentialsToHeader)
                .exchange()
                .expectStatus()
                .isForbidden();
    }

    @Test
    public void getAllUsersWithAdminRights() {
        webTestClient.get().uri("/api/admin123/users")
                .headers(TestHelper::addAdminCredentialsToHeader)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void loginOk() {
        String username = "fuu";
        String password = "bar";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
        webTestClient.get().uri("/api/user/whoami")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .exchange()
                .expectStatus()
                .isOk();
    }
}
