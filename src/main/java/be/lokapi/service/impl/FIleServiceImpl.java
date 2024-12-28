package be.lokapi.service.impl;


import be.lokapi.model.UserDTO;
import be.lokapi.service.IFileService;
import be.lokapi.service.IUserService;
import be.lokapi.utils.Constantes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FIleServiceImpl implements IFileService {

    private final IUserService userService;


    public FIleServiceImpl(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public String saveProfilePicture(MultipartFile file, Long userId) {
        try {
            // Créer le répertoire utilisateur s'il n'existe pas
            String userFolder = Constantes.UPLOAD_DIR_FRONT+"/User_" + userId;
            String fileName = "user_" + userId +".jpg";
            Path profilePicturesDir = Paths.get(userFolder);

            File directory = new File(userFolder);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Sauvegarder le fichier dans le répertoire utilisateur
            Path filePath = profilePicturesDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            UserDTO user = userService.getUserById(userId);
            String path = filePath.toString().substring("../frontend/assets/".length());
            System.out.println(path);
            user.setProfilePicture(path);
            userService.updateUser(user);
            return filePath.toString();


        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'image.", e);
        }
    }

    @Override
    public Resource loadProfilePicture(String filename) {
        try {
            Path filePath = Paths.get(Constantes.UPLOAD_DIR_FRONT).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                throw new RuntimeException("Fichier introuvable.");
            }
        }catch (Exception e){
            throw new RuntimeException("Erreur lors du chargement de l'image.", e);
        }
    }
}
