package fr.aelion.atos24.cyber.services;

import fr.aelion.atos24.cyber.models.entities.User;
import fr.aelion.atos24.cyber.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {

    private final HashUtils hashUtils;

    @Autowired
    public AuthService(HashUtils hashUtils) {
        this.hashUtils = hashUtils;
    }

    public User signup(User u) {
        u.setPwd(this.hashUtils.generate(u.getPwd()));
        // save in db
        return u; // /!\ dto (in controller...)
    }

    public User signin(User u) {
        return u;
    }
}
