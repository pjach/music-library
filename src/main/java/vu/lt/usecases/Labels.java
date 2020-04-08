package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Label;
import vu.lt.persistence.LabelsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Labels {

    @Inject
    private LabelsDAO labelsDAO;

    @Getter @Setter
    private Label labelToCreate = new Label();

    @Getter
    private List<Label> allLabels;

    @PostConstruct
    public void init(){
        loadAllArtists();
    }

    @Transactional
    public String createLabel(){
        this.labelsDAO.persist(labelToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllArtists(){
        this.allLabels = labelsDAO.loadAll();
    }
}
