package vu.lt.usecases;

import vu.lt.entities.Song;
import vu.lt.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Songs {

    @Inject
    private SongsDAO songsDAO;

    private Song songToCreate = new Song();

    private List<Song> allSongs;

    @PostConstruct
    public void init()
    {
        loadSongs();
    }

    public void loadSongs() {

        this.allSongs = songsDAO.loadAll();
    }

    public List<Song> getAllSongs(){

        return allSongs;
    }

    @Transactional
    public String createSong(){
        this.songsDAO.persist(songToCreate);
        return "success";
    }

    public Song getSongToCreate() {
        return songToCreate;
    }

    public void setSongToCreate(Song songToCreate) {
        this.songToCreate = songToCreate;
    }
}
