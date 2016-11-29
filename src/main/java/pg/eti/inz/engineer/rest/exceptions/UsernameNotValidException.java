package pg.eti.inz.engineer.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Wyjątek rzucany, gdy nazwa użytkownika nie jest prawidłowa
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameNotValidException extends RuntimeException {
    public UsernameNotValidException() {
        super("Given username is not valid.");
    }
}
