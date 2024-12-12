package edu.upb.lp.core.deck;

import android.os.Parcel;
import android.os.Parcelable;

public class CardStart implements Card {
    private final String title;
    private final int imageResource;

    public CardStart(String title, int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(imageResource);
    }

    public static final Parcelable.Creator<CardStart> CREATOR = new Parcelable.Creator<CardStart>() {
        public CardStart createFromParcel(Parcel in) {
            return new CardStart(in);
        }

        public CardStart[] newArray(int size) {
            return new CardStart[size];
        }
    };

    private CardStart(Parcel in) {
        title = in.readString();
        imageResource = in.readInt();
    }
}