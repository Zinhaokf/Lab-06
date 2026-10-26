package ca.mcgill.solitaire.cards;

/**
 * A source of cards that can be drawn from.
 */
public interface CardSource {

    /**
     * Draws a card from the source.
     *
     * @return The card drawn.
     * @pre !isEmpty()
     */
    Card draw();

    /**
     * @return True if the source has no more cards.
     */
    boolean isEmpty();
}