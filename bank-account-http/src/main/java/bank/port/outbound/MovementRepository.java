package bank.port.outbound;

import bank.domain.model.Movement;

import java.util.List;

public interface MovementRepository {
    void save(Movement movement);

    List<Movement> findAll();
}
