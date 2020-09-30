package com.example.translate;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

     Button firstLanguageButton,secondLanguageButton;
     Switch switchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home,container,false);
        firstLanguageButton= ((Button) v.findViewById(R.id.button));
        secondLanguageButton= ((Button) v.findViewById(R.id.button2));
        switchButton= ((Switch) v.findViewById(R.id.switch1));
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temporaryText = (String) secondLanguageButton.getText();
                secondLanguageButton.setText(firstLanguageButton.getText());
                firstLanguageButton.setText(temporaryText);
            }
        });
        firstLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).alert(1);

            }
        });
        secondLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).alert(0);
            }
        });
        return  v;
    }

    }

