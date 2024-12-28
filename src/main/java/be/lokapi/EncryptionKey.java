package be.lokapi;

import be.lokapi.utils.EncryptionUtil;

public class EncryptionKey {

    public static void main(String[] args) {
        try {
            // Mot de passe en clair que tu veux chiffrer
            String plainPassword = "Made4Tyo";

            // Générer une clé secrète une seule fois, que tu peux réutiliser pour chiffrer et déchiffrer
            String secretKey = EncryptionUtil.generateKey();
            System.out.println("Clé secrète : " + secretKey);

            // Chiffrer le mot de passe
            String encryptedPassword = EncryptionUtil.encrypt(plainPassword, secretKey);
            System.out.println("Mot de passe chiffré : " + encryptedPassword);

            // Tester le déchiffrement pour vérifier
            String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword, secretKey);
            System.out.println("Mot de passe déchiffré : " + decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
