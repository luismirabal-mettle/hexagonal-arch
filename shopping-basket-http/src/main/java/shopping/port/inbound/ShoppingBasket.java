package shopping.port.inbound;

import shopping.model.Item;

public interface ShoppingBasket {
    void addItem(Item item);

    int calculateTotalPrice();
}
