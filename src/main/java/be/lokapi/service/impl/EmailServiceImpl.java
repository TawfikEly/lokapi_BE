package be.lokapi.service.impl;

import be.lokapi.entity.User;
import be.lokapi.service.IEmailService;
import be.lokapi.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(User user, String activationToken) {

        String subject = "Activez votre compte lokapi";
        String activationUrl = Constantes.ACTVATION_URL + activationToken;

        String message = "Bonjour " + user.getUsername() + ",\n\n" +
                "Nous sommes ravis de vous compter parmi nos utilisateurs ! Pour finaliser votre inscription et activer votre compte, veuillez cliquer sur le lien suivant :\n\n" +
                activationUrl + "\n\n" +
                "Si vous n'avez pas créé de compte, veuillez ignorer cet email.\n\n\n" +
                "Cordialement,\n" +
                "L'équipe de support de lokapi\n" +
                "support@lokapi.com\n" +
                "www.lokapi.com";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }
}
