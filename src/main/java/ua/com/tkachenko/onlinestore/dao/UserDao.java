package ua.com.tkachenko.onlinestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.tkachenko.onlinestore.model.User;

public interface UserDao extends JpaRepository<User, Long>{
    User findByUsername (String username);
}
