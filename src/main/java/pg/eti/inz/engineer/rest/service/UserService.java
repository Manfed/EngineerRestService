package pg.eti.inz.engineer.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    public RestUser findById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public RestUser findByUsername(String username) {
        return userDao.getUserByName(username);
    }

    @Transactional
    public RestUser saveUser(RestUser user) {
        return userDao.addUser(user);
    }

    @Transactional
    public void updatePassword(RestUser user) {
        userDao.updatePassword(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
}
