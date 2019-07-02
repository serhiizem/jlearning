package jlearning.jwt.config;

import jlearning.jwt.annotations.InjectUserRef;
import jlearning.jwt.exceptions.AuthenticationTokenUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

import static jlearning.jwt.config.CustomTokenEnhancer.USER_ID_CUSTOM_JWT_PARAM;

@RequiredArgsConstructor
public class UserIdentifierArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenStore tokenStore;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        InjectUserRef injectUserRef = parameter.getParameterAnnotation(InjectUserRef.class);
        return injectUserRef != null;
    }

    @Override
    public Object resolveArgument(@Nullable MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @Nullable NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        String oAuthToken = getOAuthToken();
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(oAuthToken);
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();

        return additionalInformation.get(USER_ID_CUSTOM_JWT_PARAM);
    }

    private String getOAuthToken() {
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
