package edu.upb.lp.core.deck;

import android.os.Parcel;
import android.os.Parcelable;

public class CardScreen implements Card {
    private final String title;
    private final String description;
    private final int imageResource;

    public CardScreen(String title, String description, int imageResource) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(imageResource);
    }

    public static final Parcelable.Creator<CardScreen> CREATOR = new Parcelable.Creator<CardScreen>() {
        public CardScreen createFromParcel(Parcel in) {
            return new CardScreen(in);
        }

        public CardScreen[] newArray(int size) {
            return new CardScreen[size];
        }
    };

    private CardScreen(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageResource = in.readInt();
    }
}