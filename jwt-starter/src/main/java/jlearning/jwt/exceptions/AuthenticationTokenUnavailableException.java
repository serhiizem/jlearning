package jlearning.jwt.exceptions;

public class AuthenticationTokenUnavailableException extends RuntimeException {
    public AuthenticationTokenUnavailableException() {
        super("Authentication token cannot be injected because it is unavailable in the given context");
    }
}