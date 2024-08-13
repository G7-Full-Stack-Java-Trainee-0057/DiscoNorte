package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.TipoMembresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMembresiaRepository extends JpaRepository<TipoMembresia, Long> {
}