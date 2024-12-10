package edu.upb.lp.core.deck;

public class CardScreen implements Card {
    private final String title;
    private final String description;
    private final int imageResource;

    public CardScreen(String title, String description, int imageResource) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

}