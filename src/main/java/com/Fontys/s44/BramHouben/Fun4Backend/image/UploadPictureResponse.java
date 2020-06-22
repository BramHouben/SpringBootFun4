package com.Fontys.s44.BramHouben.Fun4Backend.image;

public class UploadPictureResponse {

    private String pictureName;
    private String pictureUri;
    private String fileType;
    private Long size;

    public UploadPictureResponse(String pictureName, String pictureUri, String fileType, Long size) {
        this.pictureName = pictureName;
        this.pictureUri = pictureUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
