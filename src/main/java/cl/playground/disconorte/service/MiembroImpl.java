package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Miembro;
import cl.playground.disconorte.repository.MiembroRepository;
import cl.playground.disconorte.utility.validation.ValidatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class MiembroImpl implements IMiembroService {

    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    @Transactional
    public Miembro save(Miembro miembro) {
        // Validar la entidad antes de guardarla
        ValidatorManager.validateEntity(miembro);
        return miembroRepository.save(miembro);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Miembro> findAll(Pageable pageable) {
        return miembroRepository.findAll(pageable);
    }

    @Override
    public List<Miembro> findAll() {
        return miembroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Miembro> findById(Long id) {
        return miembroRepository.findById(id);
    }

    @Override
    @Transactional
    public Miembro update(Miembro miembro) {
        // Validar la entidad antes de actualizarla
        ValidatorManager.validateEntity(miembro);
        // Verificar que el miembro existe antes de actualizar
        if (miembroRepository.existsById(miembro.getMiembroId())) {
            return miembroRepository.save(miembro);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Miembro miembro) {
        miembroRepository.delete(miembro);
    }
}
