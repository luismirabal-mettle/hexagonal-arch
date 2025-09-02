package bank;

import bank.adapter.inbound.BankAccountConsoleApp;
import bank.adapter.outbound.InMemoryBalanceMovementRepository;

public class Main {
    public static void main(String[] args) {
        BankAccountConsoleApp account = new BankAccountConsoleApp(new BankAccountLogic(new InMemoryBalanceMovementRepository()));

        account.deposit(100);
        account.withdraw(20);
        account.printStatement();
    }
}
