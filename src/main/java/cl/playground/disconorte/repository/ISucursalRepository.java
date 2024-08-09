package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Miembro;
import cl.playground.disconorte.entity.Sucursal;

import java.util.List;
import java.util.Optional;

public interface ISucursalRepository {
    Sucursal create(Sucursal sucursal);
    Optional<Sucursal> findById(Long id);
    List<Sucursal> findAll();
    Miembro update(Sucursal sucursal);
    void delete(Sucursal sucursal);
}
