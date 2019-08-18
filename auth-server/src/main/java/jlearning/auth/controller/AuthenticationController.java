package jlearning.auth.controller;

import jlearning.jwt.config.TokenExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private static final ResponseEntity TOKEN_REVOKED_SUCCESS = ok().build();
    private static final ResponseEntity<String> TOKEN_NOT_FOUND = notFound().build();

    private final TokenExtractor tokenExtractor;
    private final ConsumerTokenServices consumerTokenServices;

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @DeleteMapping("/revokeToken")
    public ResponseEntity revokeToken() {
        String token = tokenExtractor.getOAuthToken();
        if (consumerTokenServices.revokeToken(token)) {
            return TOKEN_REVOKED_SUCCESS;
        } else {
            return TOKEN_NOT_FOUND;
        }
    }
}
