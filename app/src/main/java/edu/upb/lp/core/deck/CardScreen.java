package edu.upb.lp.core.deck;

public class CardScreen implements Card {
    private final String title;
    private final String description;
    private final int imageResourceId;

    public CardScreen(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
