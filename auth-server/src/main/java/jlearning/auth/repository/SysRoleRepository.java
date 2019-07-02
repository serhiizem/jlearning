package jlearning.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jlearning.auth.domain.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
}
