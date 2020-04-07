package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Artist;
import vu.lt.entities.Song;
import vu.lt.persistence.ArtistsDAO;
import vu.lt.persistence.SongsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class SongsForArtist implements Serializable {

    @Inject
    private ArtistsDAO artistsDAO;

    @Inject
    private SongsDAO songsDAO;

    @Getter @Setter
    private Artist artist;

    @Getter @Setter
    private Song songToCreate = new Song();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));
        this.artist = artistsDAO.findOne(artistId);
    }

    @Transactional
    public String createSong() {
        songToCreate.setArtist(this.artist);
        songsDAO.persist(songToCreate);
        return "/songs.xhtml?faces-redirect=true&artistId=" + this.artist.getId();
    }
}
