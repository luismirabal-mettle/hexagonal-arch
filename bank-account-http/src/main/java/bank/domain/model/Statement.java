package bank.domain.model;

import java.util.List;

public record Statement(List<StatementLine> lines) {
}
