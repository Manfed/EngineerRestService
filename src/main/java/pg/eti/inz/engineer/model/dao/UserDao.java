package pg.eti.inz.engineer.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.internal.AbstractProducedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import pg.eti.inz.engineer.model.RestUser;
import pg.eti.inz.engineer.rest.exceptions.PasswordNotValidException;
import pg.eti.inz.engineer.rest.exceptions.UserNotFoundException;
import pg.eti.inz.engineer.rest.exceptions.UsernameNotValidException;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DAO (Data Access Object) - klasa służąca do działania na bazie danych dla użytkowników
 */
@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public RestUser getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaQuery<RestUser> criteriaQuery = session.getCriteriaBuilder().createQuery(RestUser.class);
        Root<RestUser> userRoot = criteriaQuery.from(RestUser.class);

        criteriaQuery.multiselect(userRoot.get("id"), userRoot.get("username")).where(
                session.getCriteriaBuilder().equal(userRoot.get("id"), id));

        RestUser user = session.getEntityManagerFactory().createEntityManager().createQuery(criteriaQuery).getSingleResult();

        return user;
    }

    public RestUser getFullUserByName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM RestUser WHERE username = :username")
                .setParameter("username", username);
        RestUser user = null;

        boolean userExists = ((AbstractProducedQuery) query).uniqueResultOptional().isPresent();

        if (userExists) {
            user = (RestUser)  query.getSingleResult();
        }
        return user;
    }

    public RestUser getUserByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaQuery<RestUser> criteriaQuery = session.getCriteriaBuilder().createQuery(RestUser.class);
        Root<RestUser> userRoot = criteriaQuery.from(RestUser.class);

        criteriaQuery.multiselect(userRoot.get("id"), userRoot.get("username")).where(
                session.getCriteriaBuilder().equal(userRoot.get("username"), name));

        RestUser user = session.getEntityManagerFactory().createEntityManager().createQuery(criteriaQuery).getSingleResult();

        return user;
    }

    public void addUser(RestUser user) {
        Session session = sessionFactory.getCurrentSession();
        validateUser(user.getUsername(), user.getPassword());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        session.persist(user);
    }

    public void updatePassword(RestUser user) {
        Session session = sessionFactory.getCurrentSession();
        RestUser updatedUser = null;

        if (user.getId() != null) {
            updatedUser = getUserById(user.getId().intValue());
        }

        if (user.getUsername() != null) {
            validateUsername(user.getUsername());
            if (updatedUser == null) {
                updatedUser = getUserByName(user.getUsername());
            }
        }

        if (updatedUser != null) {
            validatePassword(user.getPassword());

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            updatedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            session.update(updatedUser);
        } else {
            throw new UserNotFoundException(user.getUsername());
        }
    }

    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        RestUser user = session.load(RestUser.class, new Long(id));

        if (user != null) {
            session.delete(user);
        }
    }

    public Long getUserIdByName(String name) {
        RestUser user = getUserByName(name);
        return user.getId();
    }

    private void validateUser(String username, String password) {
        validateUsername(username);
        validatePassword(password);
    }

    private void validateUsername(String username) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,30}$");
        Matcher matcher = pattern.matcher(username);

        if (!matcher.matches()) {
            throw new UsernameNotValidException();
        }
    }

    private void validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,30}$");
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new PasswordNotValidException();
        }
    }
}
