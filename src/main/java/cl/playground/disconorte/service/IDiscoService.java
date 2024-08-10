package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Disco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDiscoService {
    Disco save(Disco disco);
    Page<Disco> findAll(Pageable pageable);
    Optional<Disco> findById(Long id);
    Disco update(Disco disco);
    void delete(Disco disco);
}
