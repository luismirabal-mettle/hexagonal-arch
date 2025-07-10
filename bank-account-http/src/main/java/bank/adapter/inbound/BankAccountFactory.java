package bank.adapter.inbound;

import bank.domain.BankAccountLogic;
import bank.port.inbound.BankAccount;
import bank.port.outbound.MovementRepository;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class BankAccountFactory {
    @Singleton
    BankAccount bankAccount(MovementRepository repository) {
        return new BankAccountLogic(repository);
    }
}
