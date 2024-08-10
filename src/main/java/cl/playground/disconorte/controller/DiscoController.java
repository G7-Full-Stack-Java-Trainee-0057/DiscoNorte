package cl.playground.disconorte.controller;

import cl.playground.disconorte.entity.Disco;
import cl.playground.disconorte.service.IDiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/disco")
public class DiscoController {

    @Autowired
    private IDiscoService discoService;

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("disco", new Disco());
        return "disco/crearDisco"; // Nombre de la vista para crear un disco
    }

    // Método para procesar la creación de un nuevo disco
    @PostMapping("/guardar")
    public String crearDisco(@ModelAttribute("disco") Disco disco, Model model) {
        try {
            discoService.save(disco);
            return "redirect:/disco"; // Redirige a la lista de discos después de guardar
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "disco/crearDisco"; // Redirige a la misma vista si hay un error
        }
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10; // Número de elementos por página
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Disco> discoPage = discoService.findAll(pageable);

        model.addAttribute("discoPage", discoPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", discoPage.getTotalPages());
        return "disco/listarDiscos"; // Nombre de la vista que lista todos los discos
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Disco> disco = discoService.findById(id);
        if (disco.isPresent()) {
            model.addAttribute("disco", disco.get());
            return "disco/editarDisco"; // Nombre de la vista para editar un disco
        } else {
            return "redirect:/disco"; // Redirige a la lista de discos si no se encuentra el ID
        }
    }

    // Método para procesar la actualización de un disco
    @PostMapping("/actualizar/{id}")
    public String actualizarDisco(@PathVariable Long id, @ModelAttribute("disco") Disco disco) {
        disco.setDiscoId(id);
        discoService.update(disco);
        return "redirect:/disco"; // Redirige a la lista de discos después de actualizar
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDisco(@PathVariable Long id) {
        Optional<Disco> disco = discoService.findById(id);
        if (disco.isPresent()) {
            discoService.delete(disco.get());
        }
        return "redirect:/disco"; // Redirige a la lista de discos después de eliminar
    }
}
