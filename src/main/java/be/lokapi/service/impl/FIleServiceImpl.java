package be.lokapi.service.impl;

import be.lokapi.entity.User;
import be.lokapi.model.UserDTO;
import be.lokapi.service.IFileService;
import be.lokapi.service.IUserService;
import be.lokapi.utils.Constantes;
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
        String uploadDir = Constantes.UPLOAD_DIR_FRONT+"/User_" + userId;
        try {
            // Créer le répertoire utilisateur s'il n'existe pas
            String userFolder = Constantes.UPLOAD_DIR_FRONT + "/User_" + userId;
            File directory = new File(userFolder);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Sauvegarder le fichier dans le répertoire utilisateur
            String fileName = userId + ".jpeg"; // Vous pouvez utiliser d'autres extensions selon le fichier
            //  Path filePath = Paths.get(userFolder, fileName);
            //  Files.write(filePath, file.getBytes());

            Path frontendPath = Paths.get(userFolder, fileName);
            Files.copy(frontendPath, frontendPath, StandardCopyOption.REPLACE_EXISTING);
            UserDTO user = userService.getUserById(userId);
            user.setProfilePicture(frontendPath.toString());
            userService.updateUser(user);
            return frontendPath.toString();


        } catch (IOException e) {
            e.printStackTrace();
            return "";

        }
    }
}
