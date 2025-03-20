package ro.eduardismund;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class Repository {
    private final DataSource dataSource;

    @SneakyThrows
    String getHelloMessage() {
        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.createStatement()) {
                try (var resultSet = statement.executeQuery("""
                        SELECT message
                        FROM hello
                        OFFSET FLOOR(RANDOM() * (SELECT count(*) FROM hello))
                        LIMIT 1""")) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
                    }
                }
            }
        }
        return null;
    }
}
