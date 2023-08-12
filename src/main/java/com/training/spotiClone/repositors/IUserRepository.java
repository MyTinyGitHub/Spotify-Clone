package com.training.spotiClone.repositors;

import com.training.spotiClone.dao.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    public User getUserById(long id);
    public void add(User user);
    public List<User> getPremiumUsers();

    public boolean exists(String userName);
    public String toString();
}
