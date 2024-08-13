package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.VentaMiembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaMiembroRepository extends JpaRepository<VentaMiembro, Long> {
}