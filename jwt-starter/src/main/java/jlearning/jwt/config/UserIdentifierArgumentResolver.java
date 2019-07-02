package telecom.config;

import jlearning.jwt.annotations.InjectUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class UserIdentifierArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenStore tokenStore;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        InjectUserId injectUserId = parameter.getParameterAnnotation(InjectUserId.class);
        return injectUserId != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        return null;
    }
}
