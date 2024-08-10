package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Sucursal;
import cl.playground.querygenerator.util.repositorys.GenericRepository;
import org.springframework.stereotype.Repository;
import java.lang.Integer;

@Repository
public interface SucursalRepository extends GenericRepository<Sucursal, Integer> {
}