package hu.autsoft.nytimes.mock.util;


import java.util.LinkedList;

public class MockCallQueue<T> extends LinkedList<T> {
    public void errorIfEmpty() {
        if (isEmpty()) {
            throw new NoMockResultSetException();
        }
    }
}
