package ua.com.tkachenko.onlinestore.service;

import ua.com.tkachenko.onlinestore.model.User;

public interface UserService {
    void save (User user);
    User findByUsername(String username);
}
