package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Miembro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMiembroService {
    Miembro save(Miembro miembro);
    Page<Miembro> findAll(Pageable pageable);
    List<Miembro> findAll();
    Optional<Miembro> findById(Long id);
    Miembro update(Miembro miembro);
    void delete(Miembro miembro);
}
