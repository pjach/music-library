package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Artist;
import vu.lt.persistence.ArtistsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Artists {

    @Inject
    private ArtistsDAO artistsDAO;

    @Getter @Setter
    private Artist artistToCreate = new Artist();

    @Getter
    private List<Artist> allArtists;

    @PostConstruct
    public void init(){
        loadAllArtists();
    }

    @Transactional
    public String createArtist(){
        this.artistsDAO.persist(artistToCreate);
        return "success";
    }

    private void loadAllArtists(){
        this.allArtists = artistsDAO.loadAll();
    }
}
