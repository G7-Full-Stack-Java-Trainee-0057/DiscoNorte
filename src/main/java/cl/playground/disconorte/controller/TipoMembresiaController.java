package cl.playground.disconorte.controller;

import cl.playground.disconorte.entity.TipoMembresia;
import cl.playground.disconorte.service.ITipoMembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/membresia")
public class TipoMembresiaController {

    @Autowired
    private ITipoMembresiaService tipoMembresiaService;

    // Método para mostrar el formulario de creación de una nueva membresía
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("tipoMembresia", new TipoMembresia());
        return "membresia/crearMembresia"; // Nombre de la vista para crear una membresía
    }

    // Método para procesar la creación de una nueva membresía
    @PostMapping("/guardar")
    public String crearMembresia(@ModelAttribute("tipoMembresia") TipoMembresia tipoMembresia, Model model) {
        try {
            tipoMembresiaService.save(tipoMembresia);
            return "redirect:/membresia"; // Redirige a la lista de membresías después de guardar
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "membresia/crearMembresia"; // Redirige a la misma vista si hay un error
        }
    }

    // Método para listar todos los tipos de membresía
    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10; // Número de elementos por página
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<TipoMembresia> membresiaPage = tipoMembresiaService.findAll(pageable);

        model.addAttribute("membresiaPage", membresiaPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", membresiaPage.getTotalPages());
        return "membresia/listarMembresias"; // Nombre de la vista que lista todos los discos
    }

    // Método para obtener un tipo de membresía por su ID y mostrarlo en un formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<TipoMembresia> tipoMembresia = tipoMembresiaService.findById(id);
        if (tipoMembresia.isPresent()) {
            model.addAttribute("tipoMembresia", tipoMembresia.get());
            return "membresia/editarMembresia"; // Nombre de la vista para editar una membresía
        } else {
            return "redirect:/membresia"; // Redirige a la lista de membresías si no se encuentra el ID
        }
    }

    // Método para procesar la actualización de un tipo de membresía
    @PostMapping("/actualizar/{id}")
    public String actualizarMembresia(@PathVariable Long id, @ModelAttribute("tipoMembresia") TipoMembresia tipoMembresia) {
        tipoMembresia.setTipoMembresiaId(id);
        tipoMembresiaService.update(tipoMembresia);
        return "redirect:/membresia"; // Redirige a la lista de membresías después de actualizar
    }

    // Método para eliminar un tipo de membresía
    @GetMapping("/eliminar/{id}")
    public String eliminarMembresia(@PathVariable Long id) {
        Optional<TipoMembresia> tipoMembresia = tipoMembresiaService.findById(id);
        if (tipoMembresia.isPresent()) {
            tipoMembresiaService.delete(tipoMembresia.get());
        }
        return "redirect:/membresia"; // Redirige a la lista de membresías después de eliminar
    }
}
