package fr.li212.codingame.ring.ia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FindSequenceTest {

    @DisplayName("AZ")
    @Test
    void az() {
        final FindSequence findSequence = new FindSequence();
        System.out.println(findSequence.find("AZ"));
    }

    @DisplayName("MINAS")
    @Test
    void minas() {
        final FindSequence findSequence = new FindSequence();
        System.out.println(findSequence.find("MINAS"));
    }

}
