package shopping.adapter.inbound;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import shopping.ShoppingBasketLogic;
import shopping.port.inbound.ShoppingBasket;
import shopping.port.outbound.ShoppingBasketRepository;

@Factory
public class ShoppingBasketFactory {
    @Singleton
    public ShoppingBasket shoppingBasket(ShoppingBasketRepository repository) {
        return new ShoppingBasketLogic(repository);
    }
}
