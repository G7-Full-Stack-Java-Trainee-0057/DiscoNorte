package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.VentaMiembro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IVentaMiembro {
    VentaMiembro save(VentaMiembro ventaMiembro);
    Page<VentaMiembro> findAll(Pageable pageable);
    List<VentaMiembro> findAll();
    Optional<VentaMiembro> findById(Long id);
    VentaMiembro update(VentaMiembro ventaMiembro);
    void delete(VentaMiembro ventaMiembro);
}
