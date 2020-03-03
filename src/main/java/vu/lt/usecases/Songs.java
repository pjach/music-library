package vu.lt.usecases;

import vu.lt.entities.Song;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model
public class Songs {

    private List<Song> allSongs;

    @PostConstruct
    public void init(){
        loadSongs();
    }

    public void loadSongs() {
        // TODO this is a mock implementation - later we will connect it to real data store
        List<Song> songs = new ArrayList<Song>();
        songs.add(new Song(125, "Gecko"));
        songs.add(new Song(126, "Koala"));
        this.allSongs = songs;
    }

    public List<Song> getAllSongs(){
        return allSongs;
    }
}
