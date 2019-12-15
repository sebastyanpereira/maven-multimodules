package repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> getAll(String search) throws SQLException;

    void save(T object);
}
