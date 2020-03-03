package vu.lt.entities;


import java.io.Serializable;

public class Song implements Serializable {
    private String name;
    private int tempo;

    public Song (){
    }

    public Song(int tempo, String name){
        this.name = name;
        this.tempo = tempo;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
