package service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    List<T> getAll(String search) throws SQLException;

    void save(T object);
}
