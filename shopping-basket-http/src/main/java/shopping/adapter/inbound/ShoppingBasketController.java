package shopping.adapter.inbound;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Singleton;
import shopping.model.Item;
import shopping.port.inbound.ShoppingBasket;

@Controller("/shopping-basket")
@Singleton
public class ShoppingBasketController {
    private final ShoppingBasket shoppingBasket;

    public ShoppingBasketController(ShoppingBasket shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    @Post("/items")
    public HttpResponse<String> addItem(@Body AddItemRequest request) {
        shoppingBasket.addItem(new Item(request.name(), request.quantity(), request.price()));
        return HttpResponse.ok();
    }

    @Get("/total")
    public HttpResponse<TotalPriceResponse> getTotalPrice() {
        return HttpResponse.ok(new TotalPriceResponse(shoppingBasket.calculateTotalPrice()));
    }

    @Serdeable
    public record AddItemRequest(String name, int quantity, int price) {}

    @Serdeable
    public record TotalPriceResponse(int total) {}
}
