package shopping;

import shopping.model.Item;
import shopping.port.inbound.ShoppingBasket;
import shopping.port.outbound.ShoppingBasketRepository;

import java.util.List;

public class ShoppingBasketLogic implements ShoppingBasket {
    private final ShoppingBasketRepository repository;

    public ShoppingBasketLogic(ShoppingBasketRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addItem(Item item) {
        repository.save(item);
    }

    @Override
    public int calculateTotalPrice() {
        List<Item> items = repository.findAll();
        return items.stream()
                .mapToInt(item -> item.quantity() * item.price())
                .sum();
    }
}
