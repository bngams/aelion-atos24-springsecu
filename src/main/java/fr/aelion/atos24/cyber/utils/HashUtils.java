package fr.aelion.atos24.cyber.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component // ou @Bean
public class HashUtils {

    @Value("${spring.application.security.hash.algo}")
    private String algo;

    @Value("${spring.application.security.hash.salt}")
    private String salt;

    public String generate(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            md.update(salt.getBytes());
            return new String(md.digest(value.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
