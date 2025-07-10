package shopping.port.outbound;

import shopping.model.Item;

import java.util.List;

public interface ShoppingBasketRepository {
    void save(Item item);

    List<Item> findAll();
}
