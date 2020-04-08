package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Label.findAll", query = "select l from Label as l")
})

@Entity
@Table(name = "LABEL")
@Getter @Setter
public class Label implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "LABEL_ARTIST")
    private List<Artist> artists = new ArrayList<>();

    public Label(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(id, label.id) &&
                Objects.equals(name, label.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
