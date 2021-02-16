package com.example.classfirebase.model;

public class User {

    private String noteId;
    private String noteName;
    private String noteDescription;
    private String totalImage;

    public User() {
    }

    public User(String noteId, String noteName, String noteDescription, String totalImage) {
        this.noteId = noteId;
        this.noteName = noteName;
        this.noteDescription = noteDescription;
        this.totalImage = totalImage;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getTotalImage() {
        return totalImage;
    }

    public void setTotalImage(String totalImage) {
        this.totalImage = totalImage;
    }
}
