package pg.eti.inz.engineer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Klasa reprezentujaca lokalizacje uzytkownika na trasie.
 */
@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    private Double latitude;

    private Double longitude;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    private Double speed;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    public Location() {
    }

    public Location(Double latitude, Double longitude, Date timestamp, Double speed, Route route) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.speed = speed;
        this.route = route;
    }

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Double getSpeed() {
        return speed;
    }

    public Route getRoute() {
        return route;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
