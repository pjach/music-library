package vu.lt.persistence;

import vu.lt.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class SongsDAO {

    @PersistenceContext
    private EntityManager em;

    public void persist(Song song){
        this.em.persist(song);
    }
}
