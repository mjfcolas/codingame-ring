package fr.li212.codingame.ring.ia;

import java.util.*;
import java.util.stream.IntStream;

public class FindSequence {

    Coordinate playerPosition = new Coordinate(0);
    final Map<Coordinate, RuneValue> charactersByPosition;
    final StringBuilder result = new StringBuilder();

    public FindSequence() {
        this.charactersByPosition = new HashMap<>();
        IntStream.range(0, Coordinate.WORLD_LENGTH).forEach(value -> charactersByPosition.put(new Coordinate(value), RuneValue.SPACE));
    }

    public String find(final String phrase) {

        final char[] phraseArray = phrase.toCharArray();

        for (final char c : phraseArray) {
            final RuneValue runeToSet = RuneValue.fromChar(c);

            final Map.Entry<Coordinate, RuneValue> bestChoice = this.charactersByPosition.entrySet().stream()
                    .min(Comparator.comparingInt(entry -> this.scoreToGoThenChangeRuneThenActivateThenRollback(entry.getKey(), runeToSet)))
                    .orElseThrow(() -> new IllegalStateException("Illegal state: no rune found to be set"));
            this.activateRune(bestChoice.getKey(), runeToSet);
        }
        return result.toString();
    }

    private void activateRune(final Coordinate coordinateToSet, final RuneValue runeToSet) {
        coordinateToSet.path(playerPosition).forEach(move -> result.append(move.getMove()));
        playerPosition = coordinateToSet;
        runeToSet.movesToSetFrom(charactersByPosition.get(coordinateToSet)).forEach(move -> result.append(move.getMove()));
        charactersByPosition.put(coordinateToSet, runeToSet);
        result.append(Move.ADD_RUNE.getMove());
    }

    private int scoreToGoThenChangeRuneThenActivateThenRollback(final Coordinate toBeModified, final RuneValue targetValue) {
        final int distance = toBeModified.distance(playerPosition);
        final int scoreToActivateOrRollback = targetValue.movesToSetFrom(charactersByPosition.get(toBeModified)).size();
        return distance + 2 * scoreToActivateOrRollback + 1;
    }
}
