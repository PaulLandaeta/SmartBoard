package edu.upb.lp.core.deck;

import java.util.ArrayList;
import edu.upb.lp.core.deck.exceptions.CardPositionOutOfBoundsException;

public class Deck {
    private final ArrayList<Card> cards;
    private String closeTitle;

    public Deck(String closeTitle) {
        this.cards = new ArrayList<>();
        this.closeTitle = closeTitle;
    }

    public void addCard(String title, String description, String image) {
        cards.add(new CardScreen(title, description, image));
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> deckComplete = new ArrayList<>();
        deckComplete.addAll(cards);
        Card closeCard = new CardStart(this.closeTitle);
        deckComplete.add(closeCard);
        return deckComplete;
    }

    public void clear() {
        cards.clear();
    }
    public void remove(int position) {
        if (position < 0 || position >= cards.size()) {
            throw new CardPositionOutOfBoundsException("Card position " + position + " is out of bounds.");
        }
        cards.remove(position);
    }

    public void removeLastCard() {
        if (!cards.isEmpty()) {
            cards.remove(cards.size() - 1);
        }
    }

    public void setCloseTitle(String closeTitle) {
        this.closeTitle = closeTitle;
    }
}
