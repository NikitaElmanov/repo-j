package patterns.behavioral.iterator;

import java.util.List;
import patterns.behavioral.iterator.core.GameCharacter;

public class Main {

    public static void main(String[] args) {

        GameCharacter<String> character = new GameCharacter<>(
                "Player 1",
                List.of("Jump", "Walk", "Kick", "Run", "Take", "Protect")
        );

        System.out.println(character.getName());
        final var iterator = character.getIterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
