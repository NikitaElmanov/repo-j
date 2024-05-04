package patterns.behavioral.iterator.core;

@FunctionalInterface
public interface Collection<T> {

    Iterator<T> getIterator();

}
