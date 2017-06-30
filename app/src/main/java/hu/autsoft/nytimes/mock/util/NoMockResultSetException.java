package hu.autsoft.nytimes.mock.util;


public class NoMockResultSetException extends Error {
    public NoMockResultSetException() {
        super("No mock result set!");
    }
}
