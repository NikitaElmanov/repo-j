package patterns.behavioral.iterator.core;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameCharacter<R> implements Collection<R> {

    private String name;
    private List<R> capabilities;

    @Override
    public Iterator<R> getIterator() {
        return new CapabilityIterator();
    }

    private class CapabilityIterator implements Iterator<R> {

        private int index;

        @Override
        public boolean hasNext() {
            return index < capabilities.size();
        }

        @Override
        public R next() {
            return capabilities.get(index++);
        }

    }

}
