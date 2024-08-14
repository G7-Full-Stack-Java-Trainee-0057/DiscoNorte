package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Sucursal;
import cl.playground.disconorte.entity.Vendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IVendedorService {
    Vendedor save(Vendedor vendedor);
    Page<Vendedor> findAll(Pageable pageable);
    List<Vendedor> findAll();
    Optional<Vendedor> findById(Long id);
    Vendedor update(Vendedor vendedor);
    void delete(Vendedor vendedor);
}
