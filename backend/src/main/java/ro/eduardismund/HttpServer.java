package ro.eduardismund;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

@RequiredArgsConstructor
public class HttpServer {
    private final HelloServlet helloServlet;

    @SneakyThrows
    public void start(){
        final var tomcat = new Tomcat();

        String webAppDir = new File("static").getAbsolutePath();
        tomcat.addWebapp("", webAppDir);


        tomcat.setPort(8080);
        tomcat.getConnector();

        final var context = tomcat.addContext("/api", System.getProperty("java.io.tmpdir"));

        tomcat.addServlet("/api", "HelloServlet", helloServlet);

        context.addServletMappingDecoded("/hello", "HelloServlet");

        tomcat.start();
        tomcat.getServer().await();
    }

}
