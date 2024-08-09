package cl.playground.disconorte.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaMiembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaMiembroId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "miembro_id", nullable = false)
    private Miembro miembro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "disco_id", nullable = false)
    private Disco disco;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer ventaMiembroCantidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;
}
