package pg.eti.inz.engineer.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Wyjątek rzucany, gdy użytkownik próbuje pobrać nie swoje trasy
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserIsNotOwnerException extends RuntimeException {
    public UserIsNotOwnerException() {
        super("Current user isn't an owner of this data");
    }
}
