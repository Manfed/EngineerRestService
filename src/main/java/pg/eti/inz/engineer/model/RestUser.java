package pg.eti.inz.engineer.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Klasa reprezentujaca uzytkownika
 */
@Entity
@Table(name = "restUsers")
public class RestUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "restUser")
    @Cascade(CascadeType.ALL)
    @Transient
    private List<Route> routes;

    public RestUser() {
    }

    public RestUser(Long id, String username) {
        this.id = id;
        this.username = username;
        this.password = "";
    }

    public RestUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
