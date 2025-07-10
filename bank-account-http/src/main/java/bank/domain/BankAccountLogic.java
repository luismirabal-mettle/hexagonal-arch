package bank.domain;

import bank.domain.model.Movement;
import bank.domain.model.Statement;
import bank.domain.model.StatementLine;
import bank.port.inbound.BankAccount;
import bank.port.outbound.MovementRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankAccountLogic implements BankAccount {
    private final MovementRepository repository;

    public BankAccountLogic(MovementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deposit(int amount) {
        repository.save(new Movement(LocalDateTime.now(), amount));
    }

    @Override
    public void withdraw(int amount) {
        repository.save(new Movement(LocalDateTime.now(), -amount));
    }

    @Override
    public Statement getStatement() {
        List<Movement> movements = repository.findAll();
        List<StatementLine> lines = new ArrayList<>();
        var balance = 0;
        for (var movement : movements) {
            balance += movement.amount();
            lines.add(new StatementLine(LocalDateTime.now(), movement.amount(), balance));
        }
        return new Statement(lines);
    }
}
