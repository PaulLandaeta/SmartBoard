package edu.upb.lp.core.model;

public class ScreenData {
    private final String title;
    private final String description;
    private final int imageResourceId;
    private final int backgroundColor;
    private final boolean isLastScreen;

    private  final boolean showButton;

    public ScreenData(String title, String description, int imageResourceId, int backgroundColor, boolean isLastScreen, boolean showButton) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.backgroundColor = backgroundColor;
        this.isLastScreen = isLastScreen;
        this.showButton = showButton;

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

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isLastScreen() {
        return isLastScreen;
    }

    public boolean isShowButton() {
        return showButton;
    }
}
