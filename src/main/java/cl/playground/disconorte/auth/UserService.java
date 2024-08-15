package cl.playground.disconorte.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(String username, String password) {
        // Verificar si el usuario ya existe
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Crear un nuevo usuario
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));

        // Asignar el rol "USER" por defecto
        Roles userRole = rolRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Rol 'USER' no encontrado."));
        Set<Roles> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);

        // Guardar el nuevo usuario en la base de datos
        return userRepository.save(newUser);
    }
}
