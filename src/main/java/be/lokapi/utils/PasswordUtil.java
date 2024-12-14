package be.lokapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordUtil {

    // Méthode pour hacher un mot de passe
    public static String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    // Méthode pour vérifier le mot de passe haché
    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }
}
