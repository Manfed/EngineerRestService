package pg.eti.inz.engineer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
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

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Location> locations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restUserId", nullable = false)
    @JsonIgnore
    private RestUser restUser;

    public Route() {
    }

    public Route(Long id) {
        this(id, null, null);
    }

    public Route(List<Location> locations, RestUser restUser) {
        this(null, locations, restUser);
    }

    public Route(Long id, List<Location> locations, RestUser restUser) {
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setRestUser(RestUser restUser) {
        this.restUser = restUser;
    }
}
