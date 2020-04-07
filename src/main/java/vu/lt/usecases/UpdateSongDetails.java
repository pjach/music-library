package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Song;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.SongsDAO;

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
@Getter @Setter
public class UpdateSongDetails implements Serializable {

    private Song song;

    @Inject
    private SongsDAO songsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateSongDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer songId = Integer.parseInt(requestParameters.get("songId"));
        this.song = songsDAO.findOne(songId);
    }

    @Transactional
    @LoggedInvocation
    public String updateSongTempo() {
        try{
            songsDAO.update(this.song);
        } catch (OptimisticLockException e) {
            return "/songDetails.xhtml?faces-redirect=true&songId=" + this.song.getId() + "&error=optimistic-lock-exception";
        }
        return "songs.xhtml?artistId=" + this.song.getArtist().getId() + "&faces-redirect=true";
    }

}
