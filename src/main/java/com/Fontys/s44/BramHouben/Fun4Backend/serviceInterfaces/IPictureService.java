package com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

    String storePicture(MultipartFile file);

    Resource loadFileAsResource(String fileName);
}
