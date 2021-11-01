package fr.li212.codingame.ring.ia;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coordinate {
    public static final int WORLD_LENGTH = 30;
    final int position;

    public Coordinate(final int position) {
        this.position = position;
    }

    public int distance(final Coordinate other) {
        return Math.abs(signedDistance(other));
    }

    private int signedDistance(final Coordinate other) {
        final int potentialDistance = other.getPosition() - position;
        if (potentialDistance > WORLD_LENGTH / 2) {
            return potentialDistance - WORLD_LENGTH;
        } else if (potentialDistance < -WORLD_LENGTH / 2) {
            return WORLD_LENGTH + potentialDistance;
        } else {
            return potentialDistance;
        }
    }

    public List<Move> path(final Coordinate other) {
        final int signedDistance = signedDistance(other);
        return IntStream.range(0, Math.abs(signedDistance)).mapToObj(value -> signedDistance > 0 ? Move.RIGHT : Move.LEFT).collect(Collectors.toList());
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coordinate that = (Coordinate) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Coordinate: " + position;
    }
}
