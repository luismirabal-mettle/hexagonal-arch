package bank.port.inbound;

import bank.model.Statement;

public interface BankAccount {
    void deposit(int amount);
    void withdraw(int amount);
    Statement getStatement();
}
