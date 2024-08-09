package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Vendedor;

import java.util.List;
import java.util.Optional;

public interface IVendedorRepository {
    Vendedor create(Vendedor vendedor);
    Optional<Vendedor> findById(Long id);
    List<Vendedor> findAll();
    Vendedor update(Vendedor vendedor);
    void delete(Vendedor vendedor);
}
