package bank.domain.model;

import java.time.LocalDateTime;

public record StatementLine(LocalDateTime time, int amount, int balance) {
}
