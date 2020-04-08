package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Artist;
import vu.lt.entities.Label;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.LabelsDAO;

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
public class UpdateLabelDetails implements Serializable {

    private Label label;

    private String artistStageName;

    @Inject
    private LabelsDAO labelsDAO;

    @Inject
    private Artists artists;

    @PostConstruct
    private void init() {
        System.out.println("UpdateLabelDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer labelId = Integer.parseInt(requestParameters.get("labelId"));
        this.label = labelsDAO.findOne(labelId);
    }

    @Transactional
    @LoggedInvocation
    public String updateLabelArtists() {
        try{
            for(Artist artist : artists.getAllArtists()){
                if (artist.getStageName().equals(artistStageName) &&
                !label.getArtists().contains(artist)){
                    label.getArtists().add(artist);
                    labelsDAO.update(this.label);
                    return "labelDetails.xhtml?labelId=" + this.label.getId() + "&faces-redirect=true";
                }
            }
        } catch (OptimisticLockException e) {
            return "/labelDetails.xhtml?faces-redirect=true&labelId=" + this.label.getId() + "&error=optimistic-lock-exception";
        }
        return "/labelDetails.xhtml?faces-redirect=true&labelId=" + this.label.getId() + "&error=artist-not-found";
    }
}
