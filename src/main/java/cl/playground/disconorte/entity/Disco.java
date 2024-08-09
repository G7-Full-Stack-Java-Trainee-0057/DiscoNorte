package cl.playground.disconorte.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discoId;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String discoNombreArtista;

    @NotBlank
    @Column(length = 200, nullable = false)
    private String discoTitulo;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String discoDuracion;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String discoGeneroMusical;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String discoCompania;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer discoStock;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer discoPrecio;
}
