package bank.domain.model;

import java.time.LocalDateTime;

public record Movement(LocalDateTime time, int amount) {
}
