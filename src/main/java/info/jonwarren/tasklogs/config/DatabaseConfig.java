package info.jonwarren.tasklogs.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean
    public DataSource dataSource() throws URISyntaxException {
        String envDbUrl = env.getProperty("DATABASE_URL", "");

        URI dbUri = new URI(envDbUrl);
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];

        String scheme = "jdbc:mysql://";
        if (envDbUrl.startsWith("postgres://")) {
            scheme = "jdbc:postgresql://";
        }

        String dbUrl = scheme + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

}