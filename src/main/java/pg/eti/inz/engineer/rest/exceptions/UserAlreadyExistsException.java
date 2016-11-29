package pg.eti.inz.engineer.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Wyjątek rzucany, gdy podany użytkownik już istnieje w bazie
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userId) {
        super("User '" + userId + "' already exists.");
    }
}
