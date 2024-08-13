package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Sucursal;
import cl.playground.disconorte.repository.SucursalRepository;
import cl.playground.disconorte.utility.validation.ValidatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalImpl implements ISucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    @Transactional
    public Sucursal save(Sucursal sucursal) {
        ValidatorManager.validateEntity(sucursal);
        return sucursalRepository.save(sucursal);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sucursal> findAll(Pageable pageable) {
        return sucursalRepository.findAll(pageable);
    }

    @Override
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id);
    }

    @Override
    @Transactional
    public Sucursal update(Sucursal sucursal) {
        ValidatorManager.validateEntity(sucursal);
        if (sucursalRepository.existsById(sucursal.getSucursalId())) {
            return sucursalRepository.save(sucursal);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Sucursal sucursal) {
        sucursalRepository.delete(sucursal);
    }
}