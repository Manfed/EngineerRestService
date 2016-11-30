package pg.eti.inz.engineer.rest.exceptions;

/**
 * Wyjątek rzucany, gdy użytkownik próbuje pobrać nie swoje trasy
 */
public class UserIsNotOwnerException extends RuntimeException {
    public UserIsNotOwnerException() {
        super("Current user isn't an owner of this data");
    }
}
