package edu.upb.lp.core.deck;

public class CardStart implements Card {

    private final String buttonText;
    private final Runnable onStart;


    public CardStart(String buttonText, Runnable onStart) {
        this.buttonText = buttonText;
        this.onStart = onStart;
    }


    public String getButtonText() {
        return buttonText;
    }

    public Runnable getOnStart() {
        return onStart;
    }

}
