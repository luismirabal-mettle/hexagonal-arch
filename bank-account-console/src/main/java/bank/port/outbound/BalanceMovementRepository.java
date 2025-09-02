package bank.port.outbound;

import bank.model.BalanceMovement;

import java.util.List;

public interface BalanceMovementRepository {
    void save(BalanceMovement movement);
    List<BalanceMovement> getMovements();
}
