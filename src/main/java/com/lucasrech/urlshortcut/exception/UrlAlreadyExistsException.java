package com.lucasrech.urlshortcut.exception;

public class UrlAlreadyExistsException extends RuntimeException {
    public UrlAlreadyExistsException(String message) {
        super("Url already exists:" + message);
    }
}
