package pg.eti.inz.engineer.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pg.eti.inz.engineer.model.Location;

/**
 * DAO (Data Access Object) - klasa służąca do działania na bazie danych dla lokalizacji
 */
@Repository
public class LocationDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addLocation(Location location) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(location);
    }

    public void updateLocation(Location location) {
        Session session = sessionFactory.getCurrentSession();
        session.update(location);
    }
}
