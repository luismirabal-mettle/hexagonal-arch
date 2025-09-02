package shopping.adapter.outbound;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Singleton
public class DbSchemaGenerator implements ApplicationEventListener<StartupEvent> {

    private final DataSource dataSource;

    public DbSchemaGenerator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void onApplicationEvent(StartupEvent event) {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(
                        """
                                CREATE TABLE IF NOT EXISTS shopping_basket (
                                    name VARCHAR NOT NULL,
                                    quantity INTEGER NOT NULL,
                                    unit_price INTEGER NOT NULL,
                                    timestamp TIMESTAMP NOT NULL
                                )
                             """
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
