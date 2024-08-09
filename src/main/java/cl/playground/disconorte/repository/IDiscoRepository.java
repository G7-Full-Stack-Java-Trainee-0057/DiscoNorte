package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Disco;

import java.util.List;
import java.util.Optional;

public interface IDiscoRepository {
    Disco create(Disco disco);
    Optional<Disco> findById(Long id);
    List<Disco> findAll();
    Disco update(Disco disco);
    void delete(Disco disco);
}
