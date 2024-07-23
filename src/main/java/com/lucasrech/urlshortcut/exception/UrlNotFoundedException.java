package com.lucasrech.urlshortcut.exception;

public class UrlNotFoundedException extends RuntimeException {
    public UrlNotFoundedException() {
        super("Url does not exists");
    }
}
