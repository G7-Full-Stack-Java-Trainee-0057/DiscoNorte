package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Miembro;

import java.util.List;
import java.util.Optional;

public interface IMiembroRepository {
    Miembro create(Miembro miembro);
    Optional<Miembro> findById(Long id);
    List<Miembro> findAll();
    Miembro update(Miembro miembro);
    void delete(Miembro miembro);
}
