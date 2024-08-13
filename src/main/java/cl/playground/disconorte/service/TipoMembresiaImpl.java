package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.TipoMembresia;
import cl.playground.disconorte.repository.TipoMembresiaRepository;
import cl.playground.disconorte.utility.validation.ValidatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMembresiaImpl implements ITipoMembresiaService {

    @Autowired
    private TipoMembresiaRepository tipoMembresiaRepository;

    @Override
    @Transactional
    public TipoMembresia save(TipoMembresia tipoMembresia) {
        ValidatorManager.validateEntity(tipoMembresia);
        return tipoMembresiaRepository.save(tipoMembresia);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoMembresia> findAll(Pageable pageable) {
        return tipoMembresiaRepository.findAll(pageable);
    }

    @Override
    public List<TipoMembresia> findAll() {
        return tipoMembresiaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoMembresia> findById(Long id) {
        return tipoMembresiaRepository.findById(id);
    }

    @Override
    @Transactional
    public TipoMembresia update(TipoMembresia tipoMembresia) {
        ValidatorManager.validateEntity(tipoMembresia);
        if (tipoMembresiaRepository.existsById(tipoMembresia.getTipoMembresiaId())) {
            return tipoMembresiaRepository.save(tipoMembresia);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(TipoMembresia tipoMembresia) {
        tipoMembresiaRepository.delete(tipoMembresia);
    }
}