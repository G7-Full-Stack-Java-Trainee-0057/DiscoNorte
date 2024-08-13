package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Disco;
import cl.playground.disconorte.entity.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISucursalService {
    Sucursal save(Sucursal sucursal);
    Page<Sucursal> findAll(Pageable pageable);
    Optional<Sucursal> findById(Long id);
    Sucursal update(Sucursal sucursal);
    void delete(Sucursal sucursal);
}
