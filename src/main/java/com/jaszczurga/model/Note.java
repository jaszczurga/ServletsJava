package com.jaszczurga.model;

import java.util.UUID;

public class Note {
    private String id;
    private String title;
    private String content;

    public Note() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Note(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
    }

    public Note(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
