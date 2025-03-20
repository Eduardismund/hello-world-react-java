package ro.eduardismund;

import lombok.RequiredArgsConstructor;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.util.Properties;

@RequiredArgsConstructor
public class DataSourceFactory {
    private final Properties properties;
    DataSource createDataSource(){
        final String url = properties.getProperty("datasource.url");
        final String username = properties.getProperty("datasource.username");
        final String password = properties.getProperty("datasource.password");
        final var dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
