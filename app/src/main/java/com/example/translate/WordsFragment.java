package com.example.translate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class WordsFragment extends Fragment {
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_words,container,false);

        ArrayList<String> dizi = new ArrayList<>();
        dizi.add("");
        dizi.add("item1");
        dizi.add("item2");
        dizi.add("item3");
        dizi.add("item1");
        dizi.add("item2");
        dizi.add("item3");
        dizi.add("item1");
        dizi.add("item2");
        dizi.add("item3");
        dizi.add("item1");
        dizi.add("item2");
        dizi.add("item3");
        dizi.add("item1");
        dizi.add("item2");
        dizi.add("item3");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dizi);


        ListView listView = ((ListView) v.findViewById(R.id.listview));

        listView.setAdapter(adapter);
        return v;
    }
}
