package fr.li212.codingame.ring.ia;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum RuneValue {
    SPACE(0),
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    I(9),
    J(10),
    K(11),
    L(12),
    M(13),
    N(14),
    O(15),
    P(16),
    Q(17),
    R(18),
    S(19),
    T(20),
    U(21),
    V(22),
    W(23),
    X(24),
    Y(25),
    Z(26);

    private static final short ASCII_SHIFT = 64;
    private static final short NUMBER_OF_RUNES = 27;

    RuneValue(final int index) {
        this.index = index;
    }

    private final int index;

    public static RuneValue fromChar(final char toConvert){
        if(toConvert == ' '){
            return SPACE;
        }
        return RuneValue.fromIndex(toConvert - ASCII_SHIFT);
    }

    public static RuneValue fromIndex(final int index){
        return Arrays.stream(RuneValue.values())
                .filter(runeValue -> runeValue.index == index).findAny()
                .orElseThrow(() -> new IllegalStateException(String.format("No rune found for index %s", index)));
    }

    public Collection<Move> movesToSetFrom(final RuneValue initialRune){
        int distanceBetweenRunes = this.index - initialRune.index;
        if(distanceBetweenRunes > NUMBER_OF_RUNES/2){
            distanceBetweenRunes = distanceBetweenRunes - NUMBER_OF_RUNES;
        }
        if(distanceBetweenRunes > 0){
            return IntStream.range(0, distanceBetweenRunes).mapToObj(value -> Move.INCREASE_RUNE).collect(Collectors.toList());
        }else{
            return IntStream.range(0, -distanceBetweenRunes).mapToObj(value -> Move.DECREASE_RUNE).collect(Collectors.toList());
        }
    }

    public int getIndex() {
        return index;
    }
}
