package jlearning.config;

import jlearning.model.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    /**
     * Adds ID of the logged in user to the claims of JWT
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken oauthToken = (DefaultOAuth2AccessToken) accessToken;
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("user_id", user.getId());
        additionalInfo.put("username", user.getUsername());
        oauthToken.setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
