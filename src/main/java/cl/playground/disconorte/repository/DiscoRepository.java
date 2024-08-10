package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Disco;
import cl.playground.querygenerator.util.repositorys.GenericRepository;
import org.springframework.stereotype.Repository;
import java.lang.Integer;

@Repository
public interface DiscoRepository extends GenericRepository<Disco, Integer> {
}