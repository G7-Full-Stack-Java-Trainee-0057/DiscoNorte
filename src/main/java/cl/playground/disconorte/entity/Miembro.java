package cl.playground.disconorte.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Miembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long miembroId;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String miembroNombre;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String miembroApellidos;

    @NotBlank
    @Email
    @Column(length = 100, nullable = false)
    private String miembroEmail;

    @NotNull
    @Past
    @Column(nullable = false)
    private LocalDate miembroFechaNacimiento;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "sucursal_id")
    private Sucursal sucursal;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_membresia_id", nullable = false)
    private TipoMembresia tipoMembresia;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;
}
