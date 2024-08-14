package cl.playground.disconorte.controller;

import cl.playground.disconorte.entity.Vendedor;
import cl.playground.disconorte.entity.Sucursal;
import cl.playground.disconorte.service.IVendedorService;
import cl.playground.disconorte.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private IVendedorService vendedorService;

    @Autowired
    private ISucursalService sucursalService;

    // Método para mostrar el formulario de creación de un nuevo vendedor
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("vendedor", new Vendedor());
        model.addAttribute("sucursales", sucursalService.findAll()); // Cargar sucursales
        return "vendedor/crearVendedor"; // Nombre de la vista para crear un vendedor
    }

    // Método para procesar la creación de un nuevo vendedor
    @PostMapping("/guardar")
    public String crearVendedor(@ModelAttribute("vendedor") Vendedor vendedor, Model model) {
        try {
            // Convertir el ID de la sucursal en un objeto completo antes de guardar
            Sucursal sucursal = sucursalService.findById(vendedor.getSucursal().getSucursalId())
                    .orElseThrow(() -> new IllegalArgumentException("Sucursal inválida"));

            vendedor.setSucursal(sucursal);

            vendedorService.save(vendedor);
            return "redirect:/vendedor";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("sucursales", sucursalService.findAll());
            return "vendedor/crearVendedor";
        }
    }

    // Método para listar todos los vendedores con paginación
    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10; // Número de elementos por página
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vendedor> vendedorPage = vendedorService.findAll(pageable);

        model.addAttribute("vendedorPage", vendedorPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vendedorPage.getTotalPages());
        return "vendedor/listarVendedores"; // Nombre de la vista que lista todos los vendedores
    }

    // Método para obtener un vendedor por su ID y mostrarlo en un formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Vendedor> vendedor = vendedorService.findById(id);
        if (vendedor.isPresent()) {
            model.addAttribute("vendedor", vendedor.get());
            model.addAttribute("sucursales", sucursalService.findAll()); // Cargar sucursales
            return "vendedor/editarVendedor"; // Nombre de la vista para editar un vendedor
        } else {
            return "redirect:/vendedor";
        }
    }

    // Método para procesar la actualización de un vendedor
    @PostMapping("/actualizar/{id}")
    public String actualizarVendedor(@PathVariable Long id, @ModelAttribute("vendedor") Vendedor vendedor, Model model) {
        try {
            // Convertir el ID de la sucursal en un objeto completo antes de actualizar
            Sucursal sucursal = sucursalService.findById(vendedor.getSucursal().getSucursalId())
                    .orElseThrow(() -> new IllegalArgumentException("Sucursal inválida"));

            vendedor.setSucursal(sucursal);
            vendedor.setVendedorId(id);

            vendedorService.update(vendedor);
            return "redirect:/vendedor";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("sucursales", sucursalService.findAll());
            return "vendedor/editarVendedor";
        }
    }

    // Método para eliminar un vendedor
    @GetMapping("/eliminar/{id}")
    public String eliminarVendedor(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.findById(id);
        if (vendedor.isPresent()) {
            vendedorService.delete(vendedor.get());
        }
        return "redirect:/vendedor";
    }
}
