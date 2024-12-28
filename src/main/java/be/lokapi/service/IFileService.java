package be.lokapi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String saveProfilePicture(MultipartFile file, Long userId);
    Resource loadProfilePicture(String filename);
}
