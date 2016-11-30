package pg.eti.inz.engineer.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
    public RestUser getUser() {
        return userService.findById();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser (@RequestBody RestUser newUser) {
        userService.saveUser(newUser);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void updatePassword(@RequestBody RestUser updatedUser) {
        userService.updatePassword(updatedUser);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteUser() {
        userService.deleteUser();
    }
}
