package telecom.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import telecom.domain.SysRole;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {

    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public SecurityUser(String username, String password, Set<SysRole> roles) {
        this.username = username;
        this.password = password;
        this.authorities = roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getValue()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
