package vu.lt.persistence;

import vu.lt.entities.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class SongsDAO {

    @Inject
    private EntityManager em;

    public void persist(Song song){
        this.em.persist(song);
    }
}
