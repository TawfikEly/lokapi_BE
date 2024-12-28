package be.lokapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String saveProfilePicture(MultipartFile file, Long userId);
}
