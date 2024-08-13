package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.TipoMembresia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITipoMembresiaService {
    TipoMembresia save(TipoMembresia tipoMembresia);
    Page<TipoMembresia> findAll(Pageable pageable);
    List<TipoMembresia> findAll();
    Optional<TipoMembresia> findById(Long id);
    TipoMembresia update(TipoMembresia tipoMembresia);
    void delete(TipoMembresia tipoMembresia);
}
