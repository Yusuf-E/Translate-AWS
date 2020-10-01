package com.example.translate;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    EditText editText ;
    TextView previousText,translatedText;
     Button firstLanguageButton,secondLanguageButton;
     Switch switchButton;
     ImageButton translateButton;
    String text,firstLanguage,secondLanguage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home,container,false);
        firstLanguageButton= ((Button) v.findViewById(R.id.button));
        secondLanguageButton= ((Button) v.findViewById(R.id.button2));
        switchButton= ((Switch) v.findViewById(R.id.switch1));
        translateButton= ((ImageButton) v.findViewById(R.id.translateButton));
        editText = ((EditText) v.findViewById(R.id.translateText));

        translatedText= ((TextView) v.findViewById(R.id.translatedText));
        previousText= ((TextView) v.findViewById(R.id.previousText));
        translateButton.setOnClickListener(new View.OnClickListener() {
            private String resultText;

            @Override
            public void onClick(View view) {
                text=editText.getText().toString();
                firstLanguage=firstLanguageButton.getText().toString().toUpperCase();
                secondLanguage=secondLanguageButton.getText().toString().toUpperCase();
                System.out.println(text);
              ((MainActivity) getActivity()).translateText(firstLanguage,secondLanguage,text);
               System.out.println(resultText);
                Timer timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask(){

                    @Override
                    public void run(){

                        if ( ((MainActivity) getActivity()).resultText!=null) {
                            previousText.setText(text);
                            translatedText.setText(((MainActivity) getActivity()).resultText);
                            editText.setText("");
                            editText.setHint("Buraya yazınız");
                            ((MainActivity) getActivity()).resultText=null;
                            timer.cancel();

                        }     }
                },0,100);
            }
        });

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

