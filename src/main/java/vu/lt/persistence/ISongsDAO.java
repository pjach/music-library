package vu.lt.persistence;

import vu.lt.entities.Song;

public interface ISongsDAO {

    Song findOne(Integer id);

    Song update(Song song);
}
