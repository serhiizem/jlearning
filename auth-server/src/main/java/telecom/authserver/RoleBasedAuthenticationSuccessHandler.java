package telecom.authserver;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleBasedAuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {

    public RoleBasedAuthenticationSuccessHandler() {
        super();
        setRedirectStrategy(new RoleBasedRedirectStrategy());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
    }
}