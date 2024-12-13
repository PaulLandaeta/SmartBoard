package edu.upb.lp.core.deck;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CardScreen implements Card {
    private final String title;
    private final String description;
    private final String image;

    public CardScreen(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(image);
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
        image = in.readString();
    }
}
