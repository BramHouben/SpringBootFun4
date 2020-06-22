package com.Fontys.s44.BramHouben.Fun4Backend.service;

import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.ProductRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.image.MyFileNotFoundException;
import com.Fontys.s44.BramHouben.Fun4Backend.image.PictureStorageException;
import com.Fontys.s44.BramHouben.Fun4Backend.image.PictureStorageProperties;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class pictureService implements IPictureService {


    private final Path pictureStorageLocation;

    @Autowired
    public pictureService(PictureStorageProperties pictureStorageProperties) {

        this.pictureStorageLocation = Paths.get(pictureStorageProperties.getUploadDirectory()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.pictureStorageLocation);
        } catch (Exception exception) {
            throw new PictureStorageException("error uploading file");
        }
    }

    @Override
    public String storePicture(MultipartFile file) {
        String pictureName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (pictureName.contains("..")) {
                throw new PictureStorageException("no valid charSequence");
            }
            Path pictureTargetLocation = this.pictureStorageLocation.resolve(pictureName);
            Files.copy(file.getInputStream(), pictureTargetLocation, StandardCopyOption.REPLACE_EXISTING);
            return pictureName;
        } catch (IOException exception) {
            throw new PictureStorageException("Could not store file:" + pictureName);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.pictureStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
