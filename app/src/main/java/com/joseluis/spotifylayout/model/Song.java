package com.joseluis.spotifylayout.model;

/**
 * Clase tipo modelo que simula un objeto Canción
 */
public class Song {

    private int idSong;

    private String name;

    private String author;

    public Song(){
        super();
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
