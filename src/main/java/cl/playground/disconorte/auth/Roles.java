package cl.playground.disconorte.auth;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

}
