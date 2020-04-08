package vu.lt.persistence;

import vu.lt.entities.Label;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LabelsDAO {

    @Inject
    private EntityManager em;

    public List<Label> loadAll() {
        return em.createNamedQuery("Label.findAll", Label.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Label label){
        this.em.persist(label);
    }

    public Label findOne(Integer id) { return em.find(Label.class, id); }

    public Label update(Label label){
        return em.merge(label);
    }
}
