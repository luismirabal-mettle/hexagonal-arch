package bank.adapter.outbound;

import bank.model.BalanceMovement;
import bank.port.outbound.BalanceMovementRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBalanceMovementRepository implements BalanceMovementRepository {
    private final List<BalanceMovement> movements = new ArrayList<>();

    @Override
    public void save(BalanceMovement movement) {
        movements.add(movement);
    }

    @Override
    public List<BalanceMovement> getMovements() {
        return List.copyOf(movements);
    }
}
