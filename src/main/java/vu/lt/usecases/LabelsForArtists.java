package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Artist;
import vu.lt.entities.Label;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.ArtistsDAO;
import vu.lt.persistence.LabelsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class LabelsForArtists implements Serializable {

    @Inject
    private ArtistsDAO artistsDAO;

    @Inject
    private LabelsDAO labelsDAO;

    @Getter @Setter
    private Artist artist;

    @Getter @Setter
    private Label labelToCreate = new Label();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));
        this.artist = artistsDAO.findOne(artistId);
    }

    @Transactional
    @LoggedInvocation
    public String createLabel() {
        labelToCreate.getArtists().add(artist);
        labelsDAO.persist(labelToCreate);
        return "songs?faces-redirect=true&artistId=" + this.artist.getId();
    }
}
