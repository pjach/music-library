package vu.lt.persistence;


import vu.lt.entities.Artist;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ArtistsDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Artist> loadAll() {
        return em.createNamedQuery("Artist.findAll", Artist.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Artist artist){
        this.em.persist(artist);
    }

    public Artist findOne(Integer id) {
        return em.find(Artist.class, id);
    }
}
