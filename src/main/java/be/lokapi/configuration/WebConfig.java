package be.lokapi.configuration;

import be.lokapi.utils.Constantes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    // cette methode permet les message croisé entre le front et le back come par exemple le telechargement de pdf
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(Constantes.HTTP_ORIGIN_FRONT_CHROME,Constantes.HTTP_ORIGIN_FRONT_MACOS) // autorise tout les ports de localhost
                .allowedMethods(Constantes.GET, Constantes.POST, Constantes.PUT, Constantes.DELETE, Constantes.OPTIONS) //méthodes autoriséés
                .allowedHeaders("*") // Autoriser tout les headers
                .allowCredentials(true); // Autorise les cookies/sessions partagé
    }
}





