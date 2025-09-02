package bank.adapter.outbound;

import bank.domain.model.Movement;
import bank.port.outbound.MovementRepository;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MovementDbRepository implements MovementRepository {
    private final DataSource dataSource;

    public MovementDbRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Movement movement) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO movements (amount, timestamp) VALUES (?, ?)")) {

            stmt.setInt(1, movement.amount());
            stmt.setTimestamp(2, Timestamp.valueOf(movement.time()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save movement", e);
        }
    }

    @Override
    public List<Movement> findAll() {
        List<Movement> movements = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT amount, timestamp FROM movements ORDER BY timestamp ASC");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movements.add(
                        new Movement(
                                rs.getTimestamp("timestamp").toLocalDateTime(),
                                rs.getInt("amount")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch movements", e);
        }
        return movements;
    }
}
