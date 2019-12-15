package repository;

import model.User;
import ru.rtmis.util.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl implements Repository<User> {
    private final DataSource ds;

    public UserRepositoryImpl(DataSource ds) {
        this.ds = ds;
        String sql = "CREATE TABLE IF NOT EXISTS user (id TEXT PRIMARY KEY, name TEXT NOT NULL, surname TEXT NOT NULL);";
        JdbcTemplate.executeUpdate(ds, sql);
    }

    @Override
    public List<User> getAll(String search) throws SQLException {
        String sql = "SELECT id, name, surname FROM user WHERE name LIKE ?";
        return JdbcTemplate.executeQuery(ds, sql, stmt -> {
                    stmt.setString(1, "%" + search + "%");
                    return stmt;
                }, rs ->
                new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("surname")
                )
        );
    }

    @Override
    public void save(User object) {
        String sql = "INSERT INTO user (id, name, surname) VALUES (?, ?, ?)";
        JdbcTemplate.executeUpdate(ds, sql, stmt -> {
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, object.getName());
            stmt.setString(3, object.getSurname());
            return stmt;
        });
    }
}
