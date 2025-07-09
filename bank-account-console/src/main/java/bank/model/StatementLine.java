package bank.model;

import java.time.Instant;

public record StatementLine(Instant time, int amount, int balance) {
}
