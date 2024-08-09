package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Disco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.Integer;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Integer> {
}