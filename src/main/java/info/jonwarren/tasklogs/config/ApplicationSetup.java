package info.jonwarren.tasklogs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dev")
public class ApplicationSetup {

    private DatabaseSetup database;
    private HerokuSetup heroku;

    public DatabaseSetup getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseSetup database) {
        this.database = database;
    }

    public HerokuSetup getHeroku() {
        return heroku;
    }

    public void setHeroku(HerokuSetup heroku) {
        this.heroku = heroku;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //@formatter:off
        sb.append("database=[").append(database).append("], ")
        .append("heroku=[").append(heroku).append("]");
        //@formatter:on

        return sb.toString();
    }

}
