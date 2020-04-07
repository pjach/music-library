package vu.lt.persistence;

import vu.lt.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class SongsDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Song> loadAll() {
        return em.createNamedQuery("Song.findAll", Song.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Song song){
        this.em.persist(song);
    }
}
