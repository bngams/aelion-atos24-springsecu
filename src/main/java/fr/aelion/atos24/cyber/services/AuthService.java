package fr.aelion.atos24.cyber.services;

import fr.aelion.atos24.cyber.models.entities.User;
import fr.aelion.atos24.cyber.repositories.UserRepository;
import fr.aelion.atos24.cyber.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class AuthService {

    private final HashUtils hashUtils;
    private final UserRepository userRepo;

    @Autowired
    public AuthService(HashUtils hashUtils, UserRepository userRepo) {
        this.hashUtils = hashUtils;
        this.userRepo = userRepo;
    }

    public User signup(User u) {
        u.setPwd(this.hashUtils.generate(u.getPwd()));
        // save in db
        return this.userRepo.save(u); // /!\ dto (in controller, according to context...)
    }

    public User signin(User u) {
        String hash = this.hashUtils.generate(u.getPwd());
        return this.userRepo.findByEmailAndPwd(u.getEmail(), hash);
    }
}
