package vu.lt.decorators;

import vu.lt.entities.Song;
import vu.lt.persistence.ISongsDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class SongsDAODecorator implements ISongsDAO {

    @Inject @Delegate @Any
    ISongsDAO songsDAO;

    public Song update(Song song){
        if(song.getTempo() >= 120 || song.getTempo() <= 128){
            System.out.println("House music!");
        }
        return songsDAO.update(song);
    }



}
