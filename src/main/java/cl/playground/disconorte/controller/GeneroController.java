package cl.playground.disconorte.controller;

import cl.playground.disconorte.entity.Genero;
import cl.playground.disconorte.service.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private IGeneroService generoService;

    // Método para crear un nuevo género (formulario)
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("genero", new Genero());
        return "genero/crearGenero"; // Nombre de la vista para crear un género
    }

    // Método para procesar la creación de un nuevo género
    @PostMapping("/guardar")
    public String crearGenero(@ModelAttribute("genero") Genero genero, Model model) {
        try {
            generoService.save(genero);
            return "redirect:/genero"; // Redirige a la lista de géneros después de guardar
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "crearGenero"; // Redirige a la misma vista si hay un error
        }
    }

    // Método para listar todos los géneros
    @GetMapping
    public String listar(Model model) {
        List<Genero> generos = generoService.findAll();
        model.addAttribute("generos", generos);
        return "genero/listarGeneros"; // Nombre de la vista que lista todos los géneros
    }

    // Método para obtener un género por su ID y mostrarlo en un formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Genero> genero = generoService.findById(id);
        if (genero.isPresent()) {
            model.addAttribute("genero", genero.get());
            return "genero/editarGenero"; // Nombre de la vista para editar un género
        } else {
            return "redirect:/genero"; // Redirige a la lista de géneros si no se encuentra el ID
        }
    }

    // Método para procesar la actualización de un género
    @PostMapping("/actualizar/{id}")
    public String actualizarGenero(@PathVariable Long id, @ModelAttribute("genero") Genero genero) {
        genero.setGeneroId(id);
        generoService.update(genero);
        return "redirect:/genero"; // Redirige a la lista de géneros después de actualizar
    }

    // Método para eliminar un género
    @GetMapping("/eliminar/{id}")
    public String eliminarGenero(@PathVariable Long id) {
        Optional<Genero> genero = generoService.findById(id);
        if (genero.isPresent()) {
            generoService.delete(genero.get());
        }
        return "redirect:/genero"; // Redirige a la lista de géneros después de eliminar
    }
}
