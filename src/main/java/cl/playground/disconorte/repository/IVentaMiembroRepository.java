package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.VentaMiembro;

import java.util.List;
import java.util.Optional;

public interface IVentaMiembroRepository {
    VentaMiembro create(VentaMiembro ventaMiembro);
    Optional<VentaMiembro> findById(Long id);
    List<VentaMiembro> findAll();
    VentaMiembro update(VentaMiembro ventaMiembro);
    void delete(VentaMiembro ventaMiembro);
}
