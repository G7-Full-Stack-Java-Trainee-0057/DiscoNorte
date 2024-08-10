package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Vendedor;
import cl.playground.querygenerator.util.repositorys.GenericRepository;
import org.springframework.stereotype.Repository;
import java.lang.Integer;

@Repository
public interface VendedorRepository extends GenericRepository<Vendedor, Integer> {
}