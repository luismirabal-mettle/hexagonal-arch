package shopping.adapter.outbound;

import jakarta.inject.Singleton;
import shopping.model.Item;
import shopping.port.outbound.ShoppingBasketRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ShoppingBasketDbRepository implements ShoppingBasketRepository {
    private final DataSource dataSource;

    public ShoppingBasketDbRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Item item) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO shopping_basket (name, quantity, unit_price, timestamp) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, item.name());
            stmt.setInt(2, item.quantity());
            stmt.setInt(3, item.price());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save movement", e);
        }

    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name, quantity, unit_price FROM shopping_basket ORDER BY timestamp ASC");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                items.add(
                        new Item(
                                rs.getString("name"),
                                rs.getInt("quantity"),
                                rs.getInt("unit_price")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch movements", e);
        }
        return items;

    }
}
