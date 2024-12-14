package be.lokapi.configuration;

import be.lokapi.utils.Constantes;
import be.lokapi.utils.EncryptionUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private static final String SECRET_KEY = "6SuK33+CJEUOnzqR94Q6qA==";  // Génére une clé avec generateKey() et stocke-la de manière sécurisée


    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(Constantes.LOCALHOST);

        mailSender.setPort(1025);


        mailSender.setUsername("");
        mailSender.setPassword("");

        //configureMailSender(mailSender);


        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","false");
        properties.put("mail.smtp.starttls.enable","false");

        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "1025");

        properties.put("mail.debug", "true"); // Optionnel, pour activer les logs détaillés

        return mailSender;

    }

    public void configureMailSender(JavaMailSenderImpl mailSender) {
        try {
            // Mot de passe chiffré
            String encryptedPassword = "IQNO7Cm1daTuGfP/HtGwCw==";  // Chiffre mot de passe avec encrypt()

            // Déchiffrer le mot de passe avant de l'utiliser
            String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword, SECRET_KEY);

            mailSender.setPassword(decryptedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
