package com.training.spotiClone.repositors.jdbc;

import com.training.spotiClone.dao.User;
import com.training.spotiClone.dao.UserType;
import com.training.spotiClone.repositors.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUserRepository implements IUserRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public User getUserById(long id) {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        return jdbcOperations.queryForObject( sql , this::mapRow, id);
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO USERS (id, user_name, password, type) VALUES ( ?, ?, ?, ? )";
        jdbcOperations.update(
               sql, user.getId(), user.getName(), user.getPassword(), user.getType().getId()
        );
    }

    @Override
    public List<User> getPremiumUsers() {
        String sql = "SELECT * FROM USERS WHERE TYPE = ?";
        return jdbcOperations.query( sql , this::mapRow, UserType.PREMIUM.getId());
    }

    @Override
    public boolean exists(String userName) {
        String sql = "SELECT * FROM USERS WHERE USER_NAME = ?";
        return !jdbcOperations.query(sql, this::mapRow, userName).isEmpty();
    }

    private User mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("user_name");
        String password = rs.getString("password");
        UserType type = UserType.valueOf(rs.getInt("type"));

        return new User(id, name, password, type);
    }
}
