package pg.eti.inz.engineer.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.eti.inz.engineer.model.RestUser;
import pg.eti.inz.engineer.model.dao.UserDao;

/**
 * Serwis odpowiedzialny za działania na użytkownikach
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public RestUser findById() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = userDao.getUserIdByName(user.getUsername());
        return userDao.getUserById(id.intValue());
    }

    @Transactional
    public RestUser findByUsername(String username) {
        return userDao.getUserByName(username);
    }

    @Transactional
    public void saveUser(RestUser user) {
        userDao.addUser(user);
    }

    @Transactional
    public void updatePassword(RestUser user) {
        userDao.updatePassword(user);
    }

    @Transactional
    public void deleteUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = userDao.getUserIdByName(user.getUsername());
        userDao.deleteUser(id.intValue());
    }
}
