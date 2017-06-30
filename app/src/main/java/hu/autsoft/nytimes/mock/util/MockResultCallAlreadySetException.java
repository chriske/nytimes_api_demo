package hu.autsoft.nytimes.mock.util;


public class MockResultCallAlreadySetException extends Error {
    public MockResultCallAlreadySetException() {
        super("Mock result call already set!");
    }
}
