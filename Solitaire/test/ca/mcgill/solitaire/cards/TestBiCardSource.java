package ca.mcgill.solitaire.cards;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * Exercise 11: test suite for the public methods of BiCardSource
 * (draw and isEmpty), achieving 100% branch coverage with stubs.
 */
public class TestBiCardSource {

    /** Stub: holds at most one card, hard-coded by the test. */
    private static class CardSourceStub implements CardSource {

        private Optional<Card> aCard = Optional.empty();

        CardSourceStub() {}

        CardSourceStub(Card pCard) {
            aCard = Optional.of(pCard);
        }

        @Override
        public Card draw() {
            assert !isEmpty();
            Card card = aCard.get();
            aCard = Optional.empty();
            return card;
        }

        @Override
        public boolean isEmpty() {
            return !aCard.isPresent();
        }
    }

    private static final Card ACE = Card.get(Rank.ACE, Suit.CLUBS);
    private static final Card TWO = Card.get(Rank.TWO, Suit.CLUBS);

    private CardSource aEmpty = new CardSourceStub();
    private CardSource aContainsAce = new CardSourceStub(ACE);
    private CardSource aContainsTwo = new CardSourceStub(TWO);

    // ---- isEmpty(): covers both operands of && ----

    @Test
    void testIsEmpty_BothEmpty() {
        assertTrue(new BiCardSource(aEmpty, aEmpty).isEmpty());
    }

    @Test
    void testIsEmpty_FirstEmptyOnly() {
        assertFalse(new BiCardSource(aEmpty, aContainsAce).isEmpty());
    }

    @Test
    void testIsEmpty_SecondEmptyOnly() {
        assertFalse(new BiCardSource(aContainsAce, aEmpty).isEmpty());
    }

    @Test
    void testIsEmpty_NeitherEmpty() {
        assertFalse(new BiCardSource(aContainsAce, aContainsTwo).isEmpty());
    }

    // ---- draw(): covers both branches of the if ----

    @Test
    void testDraw_FirstEmpty_DrawsFromSecond() {
        assertSame(ACE, new BiCardSource(aEmpty, aContainsAce).draw());
    }

    @Test
    void testDraw_SecondEmpty_DrawsFromFirst() {
        assertSame(ACE, new BiCardSource(aContainsAce, aEmpty).draw());
    }

    @Test
    void testDraw_NeitherEmpty_DrawsFromFirst() {
        assertSame(ACE, new BiCardSource(aContainsAce, aContainsTwo).draw());
    }
}