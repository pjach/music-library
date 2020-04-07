package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Artist;
import vu.lt.persistence.ArtistsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class SongsForArtist implements Serializable {

    @Inject
    private ArtistsDAO artistsDAO;

    @Getter @Setter
    private Artist artist;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));
        this.artist = artistsDAO.findOne(artistId);
    }
}
