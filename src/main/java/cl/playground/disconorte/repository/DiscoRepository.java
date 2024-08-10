package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Disco;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {
}