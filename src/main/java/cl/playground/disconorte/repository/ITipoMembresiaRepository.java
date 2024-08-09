package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.TipoMembresia;

import java.util.List;
import java.util.Optional;

public interface ITipoMembresiaRepository {
    TipoMembresia create(TipoMembresia tipoMembresia);
    Optional<TipoMembresia> findById(Long id);
    List<TipoMembresia> findAll();
    TipoMembresia update(TipoMembresia tipoMembresia);
    void delete(TipoMembresia tipoMembresia);
}
