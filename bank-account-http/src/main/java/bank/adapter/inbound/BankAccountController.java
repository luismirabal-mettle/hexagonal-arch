package bank.adapter.inbound;

import bank.port.inbound.BankAccount;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Singleton;

import java.time.LocalDateTime;
import java.util.List;

@Controller("/bank")
@Singleton
public class BankAccountController {
    private final BankAccount bankAccount;

    public BankAccountController(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Post("/deposit")
    public HttpResponse<?> deposit(@Body DepositRequest request) {
        bankAccount.deposit(request.amount());
        return HttpResponse.ok();
    }

    @Post("/withdraw")
    public HttpResponse<?> withdraw(@Body WithdrawRequest request) {
        bankAccount.withdraw(request.amount());
        return HttpResponse.ok();
    }

    @Get("/statement")
    public HttpResponse<StatementResponse> getMessages() {
        var lines = bankAccount.getStatement().lines()
                .stream()
                .map(line -> new StatementLine(
                        line.time(),
                        line.amount(),
                        line.balance()
                ))
                .toList();
        return HttpResponse.ok(new StatementResponse(lines));
    }

    @Serdeable
    public record DepositRequest(int amount) {}

    @Serdeable
    public record WithdrawRequest(int amount) {}

    @Serdeable
    public record StatementResponse(List<StatementLine> lines) {}

    @Serdeable
    public record StatementLine(LocalDateTime time, int amount, int balance) {}

}
