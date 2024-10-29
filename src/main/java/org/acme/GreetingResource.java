package org.acme;

import io.quarkus.logging.Log;
import io.quarkus.narayana.jta.QuarkusTransaction;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import javax.sql.DataSource;
import java.sql.SQLException;

@Path("/hello")
public class GreetingResource {

    @Inject
    DataSource dataSource;

    @Transactional
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws SQLException {
        openConnection();
        return "Hello from Quarkus REST";
    }

    public void openConnection() throws SQLException {
        try (var con = dataSource.getConnection()) {
            Log.info("Connected to database 1");
        }
  }
}
