package fr.li212.codingame.ring.application.codingame;

import fr.li212.codingame.ring.ia.FindSequence;

import java.util.Scanner;

class Player {

    private static final FindSequence FIND_SEQUENCE = new FindSequence();

    public static void main(final String args[]) {
        Scanner in = new Scanner(System.in);
        String magicPhrase = in.nextLine();
        System.out.println(FIND_SEQUENCE.find(magicPhrase));
    }
}
