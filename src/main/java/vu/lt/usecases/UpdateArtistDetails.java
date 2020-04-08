package vu.lt.usecases;


import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Artist;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.ArtistsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateArtistDetails implements Serializable {

    private Artist artist;

    @Inject
    ArtistsDAO artistsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateArtistDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer artistId = Integer.parseInt(requestParameters.get("artistId"));
        this.artist = artistsDAO.findOne(artistId);
    }

    @Transactional
    @LoggedInvocation
    public String updateArtistInfo() {
        try{
            artistsDAO.update(this.artist);
        } catch (OptimisticLockException e) {
            return "/artistDetails.xhtml?faces-redirect=true&artistId=" + this.artist.getId() + "&error=optimistic-lock-exception";
        }
        return "songs.xhtml?artistId=" + this.artist.getId() + "&faces-redirect=true";
    }
}
