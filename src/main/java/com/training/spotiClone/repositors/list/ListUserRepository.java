package com.training.spotiClone.repositors.list;

import com.training.spotiClone.dao.User;
import com.training.spotiClone.dao.UserType;
import com.training.spotiClone.repositors.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ListUserRepository implements IUserRepository {
    List<User> users = new ArrayList<>();

    public User getUserById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseGet(() -> User.createGuestUser());
    }

    public void add(User user) {
        this.users.add(user);
    }

    @Override
    public List<User> getPremiumUsers() {
        return users.stream()
                .filter(user -> UserType.PREMIUM.equals(user.getType()))
                .toList();
    }

    @Override
    public boolean exists(String userName) {
        return users.stream().anyMatch( user -> user.getName().equals(userName));
    }
}
