package cl.playground.disconorte.controller;

import cl.playground.disconorte.entity.Disco;
import cl.playground.disconorte.entity.Sucursal;
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
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    // Método para crear una nueva sucursal (formulario)
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("sucursal", new Sucursal());
        return "sucursal/crearSucursal";
    }

    // Método para procesar la creación de una nueva sucursal
    @PostMapping("/guardar")
    public String crearSucursal(@ModelAttribute("sucursal") Sucursal sucursal, Model model) {
        try {
            sucursalService.save(sucursal);
            return "redirect:/sucursal";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sucursal/crearSucursal";
        }
    }

    // Método para listar todas las sucursales
    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10; // Número de elementos por página
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Sucursal> sucursalPage = sucursalService.findAll(pageable);

        model.addAttribute("sucursalPage", sucursalPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sucursalPage.getTotalPages());
        return "sucursal/listarSucursales"; // Nombre de la vista que lista todos los sucursales
    }

    // Método para obtener una sucursal por su ID y mostrarla en un formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Sucursal> sucursal = sucursalService.findById(id);
        if (sucursal.isPresent()) {
            model.addAttribute("sucursal", sucursal.get());
            return "sucursal/editarSucursal";
        } else {
            return "redirect:/sucursal";
        }
    }

    // Método para procesar la actualización de una sucursal
    @PostMapping("/actualizar/{id}")
    public String actualizarSucursal(@PathVariable Long id, @ModelAttribute("sucursal") Sucursal sucursal) {
        sucursal.setSucursalId(id);
        sucursalService.update(sucursal);
        return "redirect:/sucursal";
    }

    // Método para eliminar una sucursal
    @GetMapping("/eliminar/{id}")
    public String eliminarSucursal(@PathVariable Long id) {
        Optional<Sucursal> sucursal = sucursalService.findById(id);
        if (sucursal.isPresent()) {
            sucursalService.delete(sucursal.get());
        }
        return "redirect:/sucursal";
    }
}