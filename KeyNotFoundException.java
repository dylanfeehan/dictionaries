package dictionaries;


/**
 * An exception that is thrown when a lookup operation cannot be completed
 * because the specified identifier is not found.
 */
public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException() { super(); }
    public KeyNotFoundException(String e) { super(e); }
}


