package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.VentaMiembro;
import cl.playground.disconorte.repository.VentaMiembroRepository;
import cl.playground.disconorte.utility.validation.ValidatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VentaMiembroImpl implements IVentaMiembro {

    @Autowired
    private VentaMiembroRepository ventaMiembroRepository;

    @Override
    @Transactional
    public VentaMiembro save(VentaMiembro ventaMiembro) {
        ValidatorManager.validateEntity(ventaMiembro);
        return ventaMiembroRepository.save(ventaMiembro);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VentaMiembro> findAll(Pageable pageable) {
        return ventaMiembroRepository.findAll(pageable);
    }

    @Override
    public List<VentaMiembro> findAll() {
        return ventaMiembroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VentaMiembro> findById(Long id) {
        return ventaMiembroRepository.findById(id);
    }

    @Override
    @Transactional
    public VentaMiembro update(VentaMiembro ventaMiembro) {
        ValidatorManager.validateEntity(ventaMiembro);
        if (ventaMiembroRepository.existsById(ventaMiembro.getVentaMiembroId())) {
            return ventaMiembroRepository.save(ventaMiembro);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(VentaMiembro ventaMiembro) {
        ventaMiembroRepository.delete(ventaMiembro);
    }
}
