package vu.lt.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Artist.findAll", query = "select a from Artist as a")
})
@Getter @Setter
@Table(name = "ARTIST")
public class Artist {

    public Artist(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 30)
    @Column(name = "STAGE_NAME")
    private String stageName;

    @Column(name = "AGE")
    private Integer age;

    @Size(max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany(mappedBy = "artist")
    private List<Song> songs = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(stageName, artist.stageName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stageName);
    }
}
