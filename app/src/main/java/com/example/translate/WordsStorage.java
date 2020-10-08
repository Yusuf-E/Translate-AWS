package com.example.translate;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

public class WordsStorage extends Item {
        private String translatedText;
        private String previousText;
        TextView previousWord,translatedWord;

    public WordsStorage(String translatedText, String previousText) {
        this.translatedText = translatedText;
        this.previousText = previousText;
    }
    public WordsStorage(){

    }
    public WordsStorage(long id) {
        super(id);
    }

    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
         previousWord = viewHolder.itemView.findViewById(R.id.savedWord);
         translatedWord=viewHolder.itemView.findViewById(R.id.translatedWord);
         previousWord.setText(translatedText);
         translatedWord.setText(previousText);
    }

    @Override
    public int getLayout() {
       return R.layout.recycler_savedwords;
    }
}
