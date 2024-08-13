package cl.playground.disconorte.controller;

import cl.playground.disconorte.entity.Genero;
import cl.playground.disconorte.entity.Miembro;
import cl.playground.disconorte.entity.Sucursal;
import cl.playground.disconorte.entity.TipoMembresia;
import cl.playground.disconorte.service.IGeneroService;
import cl.playground.disconorte.service.IMiembroService;
import cl.playground.disconorte.service.ISucursalService;
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
@RequestMapping("/miembro")
public class MiembroController {

    @Autowired
    private IMiembroService miembroService;
    @Autowired
    private ITipoMembresiaService tipoMembresiaService;
    @Autowired
    private IGeneroService generoService;
    @Autowired
    private ISucursalService sucursalService;

    // Método para mostrar el formulario de creación de un nuevo miembro
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("miembro", new Miembro());
        model.addAttribute("tiposMembresia", tipoMembresiaService.findAll()); // Servicio que obtiene los tipos de membresía
        model.addAttribute("generos", generoService.findAll()); // Servicio que obtiene los géneros
        model.addAttribute("sucursales", sucursalService.findAll()); // Cargar sucursales
        return "miembro/crearMiembro";
    }

    @PostMapping("/guardar")
    public String crearMiembro(@ModelAttribute("miembro") Miembro miembro, Model model) {
        try {
            // Convertir los IDs en objetos completos antes de guardar
            TipoMembresia tipoMembresia = tipoMembresiaService.findById(miembro.getTipoMembresia().getTipoMembresiaId())
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de Membresía inválido"));
            Genero genero = generoService.findById(miembro.getGenero().getGeneroId())
                    .orElseThrow(() -> new IllegalArgumentException("Género inválido"));
            Sucursal sucursal = sucursalService.findById(miembro.getSucursal().getSucursalId())
                    .orElseThrow(() -> new IllegalArgumentException("Sucursal inválida"));

            miembro.setTipoMembresia(tipoMembresia);
            miembro.setGenero(genero);
            miembro.setSucursal(sucursal);

            miembroService.save(miembro);
            return "redirect:/miembro";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("tiposMembresia", tipoMembresiaService.findAll());
            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("sucursales", sucursalService.findAll());
            return "miembro/crearMiembro";
        }
    }


    // Método para listar todos los miembros con paginación
    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10; // Número de elementos por página
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Miembro> miembroPage = miembroService.findAll(pageable);

        model.addAttribute("miembroPage", miembroPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", miembroPage.getTotalPages());
        return "miembro/listarMiembros"; // Nombre de la vista que lista todos los miembros
    }

    // Método para obtener un miembro por su ID y mostrarlo en un formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Miembro> miembro = miembroService.findById(id);
        if (miembro.isPresent()) {
            model.addAttribute("miembro", miembro.get());
            model.addAttribute("tiposMembresia", tipoMembresiaService.findAll());
            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("sucursales", sucursalService.findAll()); // Cargar sucursales
            return "miembro/editarMiembro";
        } else {
            return "redirect:/miembro";
        }
    }

    // Método para procesar la actualización de un miembro
    @PostMapping("/actualizar/{id}")
    public String actualizarMiembro(@PathVariable Long id, @ModelAttribute("miembro") Miembro miembro, Model model) {
        try {
            // Convertir los IDs en objetos completos antes de actualizar
            TipoMembresia tipoMembresia = tipoMembresiaService.findById(miembro.getTipoMembresia().getTipoMembresiaId())
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de Membresía inválido"));
            Genero genero = generoService.findById(miembro.getGenero().getGeneroId())
                    .orElseThrow(() -> new IllegalArgumentException("Género inválido"));
            Sucursal sucursal = sucursalService.findById(miembro.getSucursal().getSucursalId())
                    .orElseThrow(() -> new IllegalArgumentException("Sucursal inválida"));

            miembro.setTipoMembresia(tipoMembresia);
            miembro.setGenero(genero);
            miembro.setSucursal(sucursal);
            miembro.setMiembroId(id);

            miembroService.update(miembro);
            return "redirect:/miembro";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("tiposMembresia", tipoMembresiaService.findAll());
            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("sucursales", sucursalService.findAll());
            return "miembro/editarMiembro";
        }
    }

    // Método para eliminar un miembro
    @GetMapping("/eliminar/{id}")
    public String eliminarMiembro(@PathVariable Long id) {
        Optional<Miembro> miembro = miembroService.findById(id);
        if (miembro.isPresent()) {
            miembroService.delete(miembro.get());
        }
        return "redirect:/miembro";
    }
}
