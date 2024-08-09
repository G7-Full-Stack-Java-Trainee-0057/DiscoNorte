package cl.playground.disconorte.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoMembresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoMembresiaId;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String tipoMembresiaDescripcion;
}