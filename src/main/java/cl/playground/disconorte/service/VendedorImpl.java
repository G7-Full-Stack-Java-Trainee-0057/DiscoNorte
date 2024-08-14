package cl.playground.disconorte.service;

import cl.playground.disconorte.entity.Vendedor;
import cl.playground.disconorte.repository.VendedorRepository;
import cl.playground.disconorte.utility.validation.ValidatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorImpl implements IVendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    @Transactional
    public Vendedor save(Vendedor vendedor) {
        ValidatorManager.validateEntity(vendedor);
        return vendedorRepository.save(vendedor);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Vendedor> findAll(Pageable pageable) {
        return vendedorRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vendedor> findAll() {
        return vendedorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vendedor> findById(Long id) {
        return vendedorRepository.findById(id);
    }

    @Override
    @Transactional
    public Vendedor update(Vendedor vendedor) {
        ValidatorManager.validateEntity(vendedor);
        if (vendedorRepository.existsById(vendedor.getVendedorId())) {
            return vendedorRepository.save(vendedor);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Vendedor vendedor) {
        vendedorRepository.delete(vendedor);
    }
}
