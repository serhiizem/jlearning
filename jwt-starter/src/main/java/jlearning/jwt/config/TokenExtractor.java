package jlearning.jwt.config;

import jlearning.jwt.exceptions.AuthenticationTokenUnavailableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class TokenExtractor {

    public String getOAuthToken() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();

        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) principal;
            Object details = authentication.getDetails();

            if (details instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) details;

                return oauthDetails.getTokenValue();
            }
        }
        throw new AuthenticationTokenUnavailableException();
    }
}
