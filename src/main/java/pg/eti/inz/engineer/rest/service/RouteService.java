package pg.eti.inz.engineer.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.eti.inz.engineer.model.Route;
import pg.eti.inz.engineer.model.dao.LocationDao;
import pg.eti.inz.engineer.model.dao.RouteDao;
import pg.eti.inz.engineer.model.dao.UserDao;

import java.util.List;

/**
 * Serwis odpowiedzialny za działania na trasach
 */
@Service("routeService")
public class RouteService {

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LocationDao locationDao;

    @Transactional
    public List<Route> getAllRoutes() {
        return routeDao.getAllRoutes(getCurrentUserId());
    }

    @Transactional
    public Route getRoute(int id) {
        return routeDao.getRoute(id, getCurrentUserId());
    }

    @Transactional
    public void addRoute(Route route) {
        routeDao.addRoute(route, getCurrentUserId());
        route.getLocations().forEach(location -> {
            location.setRoute(new Route(route.getId()));
            locationDao.updateLocation(location);
        });
    }

    @Transactional
    public void deleteRoute(int id) {
        routeDao.deleteRoute(id, getCurrentUserId());
    }

    @Transactional
    private Long getCurrentUserId() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDao.getUserIdByName(user.getUsername());
    }
}
