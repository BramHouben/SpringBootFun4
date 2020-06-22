package com.Fontys.s44.BramHouben.Fun4Backend.image;

public class PictureStorageException extends RuntimeException {

    public PictureStorageException(String message) {
        super(message);
    }

    public PictureStorageException(String message, Throwable error) {
        super(message, error);
    }

}
