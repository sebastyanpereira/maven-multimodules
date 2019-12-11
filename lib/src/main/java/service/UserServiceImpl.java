package service;

import lombok.RequiredArgsConstructor;
import model.User;
import repository.Repository;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements Service<User> {
    final Repository repository;

    @Override
    public List<User> getAll() throws SQLException {
        return repository.getAll();
    }

    @Override
    public void save(User object) {
        repository.save(object);
    }
}