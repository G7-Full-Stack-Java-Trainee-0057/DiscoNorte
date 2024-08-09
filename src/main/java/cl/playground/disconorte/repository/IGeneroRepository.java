package cl.playground.disconorte.repository;

import cl.playground.disconorte.entity.Genero;

import java.util.List;
import java.util.Optional;

public interface IGeneroRepository {
    Genero create(Genero genero);
    Optional<Genero> findById(Long id);
    List<Genero> findAll();
    Genero update(Genero genero);
    void delete(Genero genero);
}
