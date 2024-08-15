package cl.playground.disconorte.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @GetMapping("/auth/login")
    public String login() {
        return "auth/login"; // Vista para iniciar sesión
    }

    @PostMapping("/auth/login")
    public String authenticateUser(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   Model model,
                                   HttpServletResponse response) {
        try {
            // Autenticamos al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());

            // Añadir el token como una cookie HttpOnly
            Cookie cookie = new Cookie("JWT", jwt);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            model.addAttribute("username", userDetails.getUsername());

            return "redirect:/disco"; // Redirecciona después del login exitoso

        } catch (Exception e) {
            model.addAttribute("error", "Usuario o contraseña inválidos.");
            return "auth/login"; // Si falla, volvemos a la página de login con un mensaje de error
        }
    }

    @GetMapping("/auth/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Añadir un objeto vacío al modelo
        return "auth/register"; // Vista para el registro de usuario
    }

    @PostMapping("/auth/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            // Registrar nuevo usuario utilizando UserService
            userService.registerNewUser(user.getUsername(), user.getPassword());
            model.addAttribute("success", "Usuario registrado exitosamente!");
            return "redirect:/auth/login"; // Redirecciona al login después del registro exitoso

        } catch (Exception e) {
            model.addAttribute("error", "Error en el registro del usuario: " + e.getMessage());
            return "auth/register"; // Si falla, volvemos a la página de registro con un mensaje de error
        }
    }
}
