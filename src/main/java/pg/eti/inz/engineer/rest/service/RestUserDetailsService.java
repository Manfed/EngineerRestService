package pg.eti.inz.engineer.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.eti.inz.engineer.model.RestUser;
import pg.eti.inz.engineer.model.dao.UserDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Serwis odpowiedzialny za utworzenie użytkownika Spring potrzebnego do weryfikacji.
 */
@Service("userDetailsService")
public class RestUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        RestUser user = userDao.getFullUserByName(username);
        List<GrantedAuthority> authorities = buildUserAuthority();

        return buildUserForAuthentication(user, authorities);

    }

    private User buildUserForAuthentication(RestUser user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority() {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        setAuths.add(new SimpleGrantedAuthority("USER"));

        List<GrantedAuthority> result = new ArrayList<>(setAuths);

        return result;
    }
}
