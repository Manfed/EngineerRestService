package pg.eti.inz.engineer.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Wyjątek rzucany, gdy hasło użytkownika jest zbyt słabe
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordNotValidException extends RuntimeException {
    public PasswordNotValidException() {
        super("Given password is too weak.");
    }
}