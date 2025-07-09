package bank;

import bank.model.BalanceMovement;
import bank.model.Statement;
import bank.model.StatementLine;
import bank.port.inbound.BankAccount;
import bank.port.outbound.BalanceMovementRepository;

import java.time.Instant;
import java.util.ArrayList;

public class BankAccountLogic implements BankAccount {
    private final BalanceMovementRepository repository;

    public BankAccountLogic(BalanceMovementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deposit(int amount) {
        repository.save(new BalanceMovement(Instant.now(), amount));
    }

    @Override
    public void withdraw(int amount) {
        repository.save(new BalanceMovement(Instant.now(), -amount));
    }

    @Override
    public Statement getStatement() {
        var movements = repository.getMovements();
        //work out the running balance from movements and produce a list of lines
        var lines = new ArrayList<StatementLine>();
        int balance = 0;
        for (BalanceMovement movement : movements) {
            balance += movement.amount();
            lines.add(new StatementLine(movement.time(), movement.amount(), balance));
        }
        return new Statement(lines);
    }
}
