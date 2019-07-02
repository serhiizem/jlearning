package jlearning.jwt.config;

import jlearning.jwt.model.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    static final String USER_ID_CUSTOM_JWT_PARAM = "user_id";

    /**
     * Adds ID of the logged in user to the claims of JWT
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken oauthToken = (DefaultOAuth2AccessToken) accessToken;
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put(USER_ID_CUSTOM_JWT_PARAM, user.getId().toString());
        additionalInfo.put("username", user.getUsername());
        oauthToken.setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
