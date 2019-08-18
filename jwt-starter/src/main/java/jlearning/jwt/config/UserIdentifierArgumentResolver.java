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

import static java.util.Optional.ofNullable;
import static jlearning.jwt.config.CustomTokenEnhancer.USER_ID_CUSTOM_JWT_PARAM;

@RequiredArgsConstructor
public class UserIdentifierArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenStore tokenStore;
    private final TokenExtractor tokenExtractor;

    @Override
    public boolean supportsParameter(@Nullable MethodParameter parameter) {
        InjectUserRef injectUserRef = ofNullable(parameter)
                .map(mp -> mp.getParameterAnnotation(InjectUserRef.class))
                .orElse(null);

        return injectUserRef != null;
    }

    @Override
    public Object resolveArgument(@Nullable MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @Nullable NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        String oAuthToken = tokenExtractor.getOAuthToken();
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(oAuthToken);
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();

        return additionalInformation.get(USER_ID_CUSTOM_JWT_PARAM);
    }
}
