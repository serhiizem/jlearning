package telecom.repository;

import telecom.domain.SysUser;
import telecom.repository.support.WiselyRepository;

import java.util.Optional;

public interface SysUserRepository extends WiselyRepository<SysUser,Long> {
    Optional<SysUser> findOneWithRolesByUsername(String username);
}
