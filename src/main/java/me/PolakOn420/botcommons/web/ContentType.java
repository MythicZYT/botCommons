package me.PolakOn420.botcommons.web;

import okhttp3.MediaType;

public enum ContentType {
    JSON("application/json"),
    XML("application/xml"),
    URLENCODED("application/x-www-form-urlencoded"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    OCTET_STREAM("application/octet-stream"),
    ANY("*/*");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public MediaType toMediaType() {
        return MediaType.parse(type);
    }
}
