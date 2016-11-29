package pg.eti.inz.engineer.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Klasa zawierajaca informacje o przebytej trasie
 */
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Location> locations;

    @ManyToOne
    @JoinColumn(name = "restUserId", nullable = false)
    private RestUser restUser;

    public Route() {
    }

    public Route(List<Location> locations, RestUser restUser) {
        this.locations = locations;
        this.restUser = restUser;
    }

    public Long getId() {
        return id;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public RestUser getRestUser() {
        return restUser;
    }
}
