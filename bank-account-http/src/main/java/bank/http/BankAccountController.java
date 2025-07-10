package bank.http;

import bank.db.DbRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Singleton;

import java.util.List;

@Controller("/api")
@Singleton
public class BankAccountController {
    private final DbRepository repository;

    public BankAccountController(DbRepository repository) {
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
