package jlearning.auth.security;

import jlearning.auth.repository.SysUserRepository;
import jlearning.jwt.model.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jlearning.auth.domain.SysRole;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

@Slf4j
@Service("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowcaseUsername = username.toLowerCase();

        return sysUserRepository.findOneWithRolesByUsername(lowcaseUsername)
                .map(u -> {
                    Set<String> roleValues = ofNullable(u.getRoles())
                            .map(Collection::stream)
                            .orElse(Stream.empty())
                            .map(SysRole::getValue)
                            .collect(toSet());
                    return new SecurityUser(u.getId(), u.getUsername(), u.getPassword(), roleValues);
                })
                .orElseThrow(() -> new UsernameNotFoundException(
                        format("No user was found by the given username: %s", lowcaseUsername)));
    }
}
