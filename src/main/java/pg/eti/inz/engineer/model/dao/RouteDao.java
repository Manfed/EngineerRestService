package pg.eti.inz.engineer.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pg.eti.inz.engineer.model.RestUser;
import pg.eti.inz.engineer.model.Route;
import pg.eti.inz.engineer.rest.exceptions.UserIsNotOwnerException;

import java.util.List;

/**
 * DAO (Data Access Object) - klasa służąca do działania na bazie danych dla tras
 */
@Repository
public class RouteDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Route getRoute (int id, Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Route route = session.load(Route.class, new Long(id));

        if (!route.getRestUser().getId().equals(userId)) {
            throw new UserIsNotOwnerException();
        }

        return route;
    }

    public List<Route> getAllRoutes(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        List<Route> routes = session.createQuery("FROM Route WHERE restUserId = :userId")
                .setParameter("userId", userId).list();
        return routes;
    }

    public void addRoute(Route route, Long userId) {
        Session session = sessionFactory.getCurrentSession();

        route.setRestUser(new RestUser(userId));

        session.persist(route);
    }

    public void deleteRoute(int id, Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Route route = session.load(Route.class, new Long(id));
        if (route != null) {
            session.delete(route);
        }
    }
}
