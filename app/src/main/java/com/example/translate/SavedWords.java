package com.example.translate;

public class SavedWords {
    String translatedText;
    String previousText;

    public SavedWords() {
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getPreviousText() {
        return previousText;
    }

    public void setPreviousText(String previousText) {
        this.previousText = previousText;
    }

    public SavedWords(String translatedText, String previousText) {
        this.translatedText = translatedText;
        this.previousText = previousText;
    }
}
