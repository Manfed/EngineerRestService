package pg.eti.inz.engineer.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pg.eti.inz.engineer.model.RestUser;
import pg.eti.inz.engineer.rest.service.UserService;

/**
 * Kontroler użytkowników
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public RestUser getUser(@PathVariable int id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RestUser createUser (@RequestBody RestUser newUser) {
        return userService.saveUser(newUser);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void updatePassword(@RequestBody RestUser updatedUser) {
        userService.updatePassword(updatedUser);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
