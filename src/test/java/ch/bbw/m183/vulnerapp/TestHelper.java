package ch.bbw.m183.vulnerapp;


import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TestHelper {

    private static final String ADMIN_CREDENTIALS = "admin@mail.com:super5ecret";
    private static final String USER_CREDENTIALS = "fuu@mail.com:bar12345";

    public static void addAdminCredentialsToHeader(HttpHeaders httpHeaders) {
        httpHeaders.add("Authorization", "Basic " + Base64.getEncoder()
                .encodeToString(ADMIN_CREDENTIALS.getBytes(StandardCharsets.UTF_8)));
    }

    public static void addUserCredentialsToHeader(HttpHeaders httpHeaders) {
        httpHeaders.add("Authorization", "Basic " + Base64.getEncoder()
                .encodeToString(USER_CREDENTIALS.getBytes(StandardCharsets.UTF_8)));
    }
}
