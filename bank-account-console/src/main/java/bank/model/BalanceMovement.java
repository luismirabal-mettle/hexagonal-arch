package bank.model;

import java.time.Instant;

public record BalanceMovement(Instant time, int amount) {
}
