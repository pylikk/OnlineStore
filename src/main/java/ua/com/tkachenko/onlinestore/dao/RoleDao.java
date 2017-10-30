package ua.com.tkachenko.onlinestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.tkachenko.onlinestore.model.Role;

public interface RoleDao extends JpaRepository<Role,Long> {
}
