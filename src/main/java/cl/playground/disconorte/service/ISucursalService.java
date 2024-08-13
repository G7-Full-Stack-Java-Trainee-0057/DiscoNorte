package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ISucursalService {
    Sucursal save(Sucursal sucursal);
    Page<Sucursal> findAll(Pageable pageable);
    List<Sucursal> findAll();
    Optional<Sucursal> findById(Long id);
    Sucursal update(Sucursal sucursal);
    void delete(Sucursal sucursal);
}
