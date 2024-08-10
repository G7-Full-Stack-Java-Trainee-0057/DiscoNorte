package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Disco;
import cl.playground.disconorte.repository.DiscoRepository;
import cl.playground.disconorte.utility.validation.ValidatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DIscoImpl implements IDiscoService {

    @Autowired
    private DiscoRepository discoRepository;

    @Override
    @Transactional
    public Disco save(Disco disco) {
        ValidatorManager.validateEntity(disco);
        return discoRepository.save(disco);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Disco> findAll(Pageable pageable) {
        return discoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Disco> findById(Long id) {
        return discoRepository.findById(id);
    }

    @Override
    @Transactional
    public Disco update(Disco disco) {
        ValidatorManager.validateEntity(disco);
        if (discoRepository.existsById(disco.getDiscoId())) {
            return discoRepository.save(disco);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Disco disco) {
        discoRepository.delete(disco);
    }
}
