package com.training.spotiClone.repositors.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

public class JdbcRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    public JdbcOperations getJdbcOperations() {
        return jdbcOperations;
    }
}
