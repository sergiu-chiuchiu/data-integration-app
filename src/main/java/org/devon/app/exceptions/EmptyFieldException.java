package org.devon.app.exceptions;

public class EmptyFieldException extends GenericException {


    public EmptyFieldException(String message) {
        super(message);
    }

    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
