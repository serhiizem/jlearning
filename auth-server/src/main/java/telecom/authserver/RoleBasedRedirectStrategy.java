package telecom.authserver;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toSet;
import static telecom.authserver.UserRole.CSR;
import static telecom.authserver.UserRole.SERVICE_MANAGER;
import static telecom.authserver.UserRole.fromRoleName;

@RequiredArgsConstructor
public class RoleBasedRedirectStrategy implements RedirectStrategy {

    private final Function<GrantedAuthority, UserRole> authorityToUserRole =
            grantedAuthority -> fromRoleName(grantedAuthority.getAuthority());

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url)
            throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String redirectionUrl = this.getRedirectionUrl(authentication);
        response.sendRedirect("http://localhost:4200/#" + redirectionUrl);
    }

    private String getRedirectionUrl(Authentication authentication) {

        Set<UserRole> userRoles = authentication.getAuthorities()
                .stream().map(authorityToUserRole)
                .collect(toSet());

        if (userRoles.contains(SERVICE_MANAGER)) {
            return "/tariffs";
        } else if (userRoles.contains(CSR)) {
            return "/troubleshooting";
        } else return "/dashboard";
    }
}
