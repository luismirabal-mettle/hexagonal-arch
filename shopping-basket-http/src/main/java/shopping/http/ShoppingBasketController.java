package shopping.http;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Singleton;
import shopping.db.DbRepository;

import java.util.List;

@Controller("/api")
@Singleton
public class ShoppingBasketController {
    private final DbRepository repository;

    public ShoppingBasketController(DbRepository repository) {
        this.repository = repository;
    }

    @Post("/hello-world")
    public HttpResponse<String> saveMessage(@Body MessageRequest request) {
        var message = "Hello World: " + request.content();
        repository.save(message);
        return HttpResponse.ok(message);
    }

    @Get("/hello-world")
    public HttpResponse<List<String>> getMessages() {
        return HttpResponse.ok(repository.get());
    }

    @Serdeable
    public record MessageRequest(String content) {}
}
