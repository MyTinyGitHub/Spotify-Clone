package com.training.spotiClone.repositors;

import com.training.spotiClone.dao.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    User getUserById(long id);
    void add(User user);

    List<User> getPremiumUsers();

    String toString();
}
