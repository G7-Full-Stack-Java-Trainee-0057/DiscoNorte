package cl.playground.disconorte.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendedorId;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String vendedorNombre;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String vendedorApellidos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;
}
