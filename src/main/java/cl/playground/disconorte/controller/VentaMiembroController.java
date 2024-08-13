package cl.playground.disconorte.controller;

import cl.playground.disconorte.entity.VentaMiembro;
import cl.playground.disconorte.entity.Miembro;
import cl.playground.disconorte.entity.Disco;
import cl.playground.disconorte.entity.Sucursal;
import cl.playground.disconorte.service.IVentaMiembro;
import cl.playground.disconorte.service.IMiembroService;
import cl.playground.disconorte.service.IDiscoService;
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
@RequestMapping("/venta")
public class VentaMiembroController {

    @Autowired
    private IVentaMiembro ventaMiembroService;

    @Autowired
    private IMiembroService miembroService;

    @Autowired
    private IDiscoService discoService;

    @Autowired
    private ISucursalService sucursalService;

    // Método para mostrar el formulario de creación de una nueva venta
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("ventaMiembro", new VentaMiembro());
        model.addAttribute("miembros", miembroService.findAll()); // Cargar miembros
        model.addAttribute("discos", discoService.findAll()); // Cargar discos
        model.addAttribute("sucursales", sucursalService.findAll()); // Cargar sucursales
        return "venta/crearVenta";
    }

    // Método para procesar la creación de una nueva venta
    @PostMapping("/guardar")
    public String crearVenta(@ModelAttribute("ventaMiembro") VentaMiembro ventaMiembro, Model model) {
        try {
            // Convertir los IDs en objetos completos antes de guardar
            Miembro miembro = miembroService.findById(ventaMiembro.getMiembro().getMiembroId())
                    .orElseThrow(() -> new IllegalArgumentException("Miembro inválido"));
            Disco disco = discoService.findById(ventaMiembro.getDisco().getDiscoId())
                    .orElseThrow(() -> new IllegalArgumentException("Disco inválido"));
            Sucursal sucursal = sucursalService.findById(ventaMiembro.getSucursal().getSucursalId())
                    .orElseThrow(() -> new IllegalArgumentException("Sucursal inválida"));

            ventaMiembro.setMiembro(miembro);
            ventaMiembro.setDisco(disco);
            ventaMiembro.setSucursal(sucursal);

            ventaMiembroService.save(ventaMiembro);
            return "redirect:/venta";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("miembros", miembroService.findAll());
            model.addAttribute("discos", discoService.findAll());
            model.addAttribute("sucursales", sucursalService.findAll());
            return "venta/crearVenta";
        }
    }

    // Método para listar todas las ventas con paginación
    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10; // Número de elementos por página
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<VentaMiembro> ventaPage = ventaMiembroService.findAll(pageable);

        model.addAttribute("ventaPage", ventaPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ventaPage.getTotalPages());
        return "venta/listarVentas"; // Nombre de la vista que lista todas las ventas
    }

    // Método para obtener una venta por su ID y mostrarla en un formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<VentaMiembro> ventaMiembro = ventaMiembroService.findById(id);
        if (ventaMiembro.isPresent()) {
            model.addAttribute("ventaMiembro", ventaMiembro.get());
            model.addAttribute("miembros", miembroService.findAll()); // Cargar miembros
            model.addAttribute("discos", discoService.findAll()); // Cargar discos
            model.addAttribute("sucursales", sucursalService.findAll()); // Cargar sucursales
            return "venta/editarVenta"; // Nombre de la vista para editar una venta
        } else {
            return "redirect:/venta";
        }
    }

    // Método para procesar la actualización de una venta
    @PostMapping("/actualizar/{id}")
    public String actualizarVenta(@PathVariable Long id, @ModelAttribute("ventaMiembro") VentaMiembro ventaMiembro, Model model) {
        try {
            // Convertir los IDs en objetos completos antes de actualizar
            Miembro miembro = miembroService.findById(ventaMiembro.getMiembro().getMiembroId())
                    .orElseThrow(() -> new IllegalArgumentException("Miembro inválido"));
            Disco disco = discoService.findById(ventaMiembro.getDisco().getDiscoId())
                    .orElseThrow(() -> new IllegalArgumentException("Disco inválido"));
            Sucursal sucursal = sucursalService.findById(ventaMiembro.getSucursal().getSucursalId())
                    .orElseThrow(() -> new IllegalArgumentException("Sucursal inválida"));

            ventaMiembro.setMiembro(miembro);
            ventaMiembro.setDisco(disco);
            ventaMiembro.setSucursal(sucursal);
            ventaMiembro.setVentaMiembroId(id);

            ventaMiembroService.update(ventaMiembro);
            return "redirect:/venta";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("miembros", miembroService.findAll());
            model.addAttribute("discos", discoService.findAll());
            model.addAttribute("sucursales", sucursalService.findAll());
            return "venta/editarVenta";
        }
    }

    // Método para eliminar una venta
    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        Optional<VentaMiembro> ventaMiembro = ventaMiembroService.findById(id);
        if (ventaMiembro.isPresent()) {
            ventaMiembroService.delete(ventaMiembro.get());
        }
        return "redirect:/venta";
    }
}
