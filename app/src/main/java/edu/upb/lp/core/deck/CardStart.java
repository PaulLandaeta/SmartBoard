package edu.upb.lp.core.deck;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CardStart implements Card {

    private final String buttonText;

    public CardStart(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(buttonText);
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
        buttonText = in.readString();
    }
}
