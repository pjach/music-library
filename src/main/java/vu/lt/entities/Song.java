package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(name = "Song.findAll", query = "select a from Song as a")
})

@Entity
@Table(name = "SONG")
@Getter @Setter
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "TEMPO")
    private Integer tempo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ARTIST_ID")
    private Artist artist;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Song (){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) &&
                Objects.equals(name, song.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
