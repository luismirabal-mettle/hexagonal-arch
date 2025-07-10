package bank.db;

import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DbRepository {
    private final DataSource dataSource;

    public DbRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(String message) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO messages (content, timestamp) VALUES (?, ?)")) {

            stmt.setString(1, message);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save movement", e);
        }
    }

    public List<String> get() {
        List<String> messages = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT content FROM messages ORDER BY timestamp ASC");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                messages.add(rs.getString("content"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch movements", e);
        }
        return messages;
    }
}
