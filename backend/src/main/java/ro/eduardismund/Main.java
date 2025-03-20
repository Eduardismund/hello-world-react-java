package ro.eduardismund;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        final var properties = new Properties();
        try(var file = new FileInputStream("config/application.properties")){
            properties.load(file);
        }

        final var dataSource = new DataSourceFactory(properties).createDataSource();
        final var repository = new Repository(dataSource);
        final var servlet = new HelloServlet(repository);
        new HttpServer(servlet).start();
    }
}