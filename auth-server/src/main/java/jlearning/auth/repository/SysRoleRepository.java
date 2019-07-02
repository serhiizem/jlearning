package jlearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jlearning.domain.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
}
