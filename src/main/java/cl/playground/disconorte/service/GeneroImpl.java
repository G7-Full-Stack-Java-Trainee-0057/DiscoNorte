package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Genero;
import cl.playground.disconorte.repository.GeneroRepository;
import cl.playground.disconorte.utility.validation.ValidatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroImpl implements IGeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional
    public Genero save(Genero genero) {
        ValidatorManager.validateEntity(genero);
        return generoRepository.save(genero);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genero> findById(Long id) {
        return generoRepository.findById(id);
    }

    @Override
    @Transactional
    public Genero update(Genero genero) {
        ValidatorManager.validateEntity(genero);
        // Assuming the Genero entity has a valid ID and exists in the DB
        if (generoRepository.existsById(genero.getGeneroId())) {
            return generoRepository.save(genero);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Genero genero) {
        generoRepository.delete(genero);
    }
}
