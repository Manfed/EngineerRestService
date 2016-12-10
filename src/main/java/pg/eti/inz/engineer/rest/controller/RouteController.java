package pg.eti.inz.engineer.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pg.eti.inz.engineer.model.Route;
import pg.eti.inz.engineer.rest.service.RouteService;

import java.util.List;

/**
 * Kontroler tras
 */
@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "/routes", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Route> getRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return routes;
    }

    @RequestMapping(value = "/routes/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Route getRoute(@PathVariable int id) {
        return routeService.getRoute(id);
    }

    @RequestMapping(value = "/routes", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addRoute(@RequestBody Route route) {
        return routeService.addRoute(route);
    }

    @RequestMapping(value = "/routes/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteRoute(@PathVariable int id) {
        routeService.deleteRoute(id);
    }
}
