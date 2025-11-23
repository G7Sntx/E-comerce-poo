package exceptions;

public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException(String msg) {
        super(msg);
    }
}