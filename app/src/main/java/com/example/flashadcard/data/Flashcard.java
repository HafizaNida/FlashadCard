package com.example.flashadcard.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Flashcard implements Parcelable {
    private String question;
    private String answer;

    // Constructor
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    // Parcelable implementation
    protected Flashcard(Parcel in) {
        question = in.readString();
        answer = in.readString();
    }

    public static final Creator<Flashcard> CREATOR = new Creator<Flashcard>() {
        @Override
        public Flashcard createFromParcel(Parcel in) {
            return new Flashcard(in);
        }

        @Override
        public Flashcard[] newArray(int size) {
            return new Flashcard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
    }
}
