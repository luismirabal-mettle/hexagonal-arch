package bank.adapter.inbound;

import bank.port.inbound.BankAccount;

public class BankAccountConsoleApp {
    private final BankAccount account;

    public BankAccountConsoleApp(BankAccount account) {
        this.account = account;
    }

    public void deposit(int amount) {
        account.deposit(amount);
        System.out.println("Deposited " + amount);
    }

    public void withdraw(int amount) {
        account.withdraw(amount);
        System.out.println("Withdrawn " + amount);
    }

    public void printStatement() {
        System.out.println("Time\tAmount\tBalance");
        account.getStatement()
                .lines()
                .forEach(line ->
                        System.out.printf("%s\t%d\t%d%n", line.time().getEpochSecond(), line.amount(), line.balance())
                );
    }
}
