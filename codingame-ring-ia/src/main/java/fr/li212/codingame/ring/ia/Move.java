package fr.li212.codingame.ring.ia;

public enum Move {
    LEFT('<'),
    RIGHT('>'),
    INCREASE_RUNE('+'),
    DECREASE_RUNE('-'),
    ADD_RUNE('.');

    Move(final char move) {
        this.move = move;
    }

    private final char move;

    public char getMove() {
        return move;
    }
}
