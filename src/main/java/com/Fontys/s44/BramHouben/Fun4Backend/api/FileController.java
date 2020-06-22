package com.Fontys.s44.BramHouben.Fun4Backend.api;

import com.Fontys.s44.BramHouben.Fun4Backend.image.UploadPictureResponse;
import com.Fontys.s44.BramHouben.Fun4Backend.service.pictureService;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping("api/v1/picture")
@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);


    private final pictureService pictureService;

    private final IProductService productService;

    public FileController(pictureService pictureService, IProductService productService) {
        this.pictureService = pictureService;
        this.productService = productService;
    }


    @PreAuthorize("hasAuthority('product:write')")
    @PostMapping()
    public UploadPictureResponse uploadPicture(@RequestParam("file") MultipartFile file,@RequestParam("name") String name) {
        String pictureName = pictureService.storePicture(file);
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(pictureName).toUriString();
        productService.savePhoto(pictureName,name);
        return new UploadPictureResponse(pictureName, uri, file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = pictureService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
