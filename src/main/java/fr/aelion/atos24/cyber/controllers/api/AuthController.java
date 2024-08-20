package fr.aelion.atos24.cyber.controllers.api;

import fr.aelion.atos24.cyber.models.Credentials;
import fr.aelion.atos24.cyber.models.entities.User;
import fr.aelion.atos24.cyber.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // protected or default (package)  => to allow better testing
    // @Autowired
    // AuthService authservice;

    // private but accesible for tsting with constructor
    private AuthService authservice;

    public AuthController(AuthService authservice) {
        this.authservice = authservice;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody @Valid Credentials c) {
        // sign up logic (hash + salt + db storage)
        return this.authservice.signup(c.toUser()).toPublicUser();
    }

    // signin
    @PostMapping("/signin")
    public User signin(@RequestBody User u) {
        // sign in logic (hash + salt + db select) ...
        return this.authservice.signin(u).toPublicUser();
    }
}
