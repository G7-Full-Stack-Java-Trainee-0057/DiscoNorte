package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Genero;

import java.util.List;
import java.util.Optional;

public interface IGeneroService {
    Genero save(Genero genero);
    List<Genero> findAll();
    Optional<Genero> findById(Long id);
    Genero update(Genero genero);
    void delete(Genero genero);
}
