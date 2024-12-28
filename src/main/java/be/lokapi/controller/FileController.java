package be.lokapi.controller;

import be.lokapi.api.FileApi;

import be.lokapi.service.IFileService;
import be.lokapi.utils.Constantes;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



@RestController
@RequestMapping("/api/file")
public class FileController implements FileApi {


    private final IFileService fileService;

    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }



    @Override
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filename) {
        try{

            Path filePath = Paths.get(Constantes.FILE_PATH_SERVER).resolve(filename).normalize();//meilleur pratique securité
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() && resource.isReadable()){
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            throw new RuntimeException("Erreur lors du téléchargement du fichier : " + e.getMessage());
        }

    }



    @Override
    @GetMapping("/preview")
    public ResponseEntity<Resource> previewFile(@RequestParam("filename") String filename) {
        try {
            Path file = Paths.get(Constantes.FILE_PATH_SERVER).resolve(filename.trim()).normalize();

            FileSystemResource resource = new FileSystemResource(file);

            if (resource.exists() && resource.isReadable()) {
                String contentType = "application/octet-stream";
                if (filename.endsWith(".pdf")) {
                    contentType = "application/pdf";
                }

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            }
            return ResponseEntity.notFound().build();


        }catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @Override
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("owner") String owner,
                                             @RequestParam("tenant") String tenant,
                                             @RequestParam("property") String property,
                                             @RequestParam("file") MultipartFile file)
    {

        // Vérifie si le fichier n'est pas vide
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun fichier sélectionné.");
        }
        try {
            // Vérifie si le fichier est un PDF
            if (!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Seuls les fichiers PDF sont acceptés.");
            }

            Path uploadPath = Paths.get(Constantes.UPLOAD_DIR+"OWNER_"+owner+"/PROPERTY_"+property+"/TENANT_"+tenant+"/contracts/"+System.currentTimeMillis());
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Enregistre le fichier sur le disque
            Path filePath = uploadPath.resolve(file.getOriginalFilename().trim());
            Files.write(filePath, file.getBytes());

            return ResponseEntity.status(HttpStatus.OK).body(filePath.toString());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement du fichier.");
        }
    }

    @Override
    @PostMapping("/uploadProfilePicture/{userId}")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fichier vide !");
        }

        String frontendPath = fileService.saveProfilePicture(file, userId);
        if(frontendPath != null)
            return ResponseEntity.ok(frontendPath);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de l'upload de l'image.");
    }

    @Override
    @GetMapping("/loadProfilePicture/{fileName}")
    public ResponseEntity<Resource> loadProfilePicture(@PathVariable String filename) {
        try {
            Resource file = fileService.loadProfilePicture(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
