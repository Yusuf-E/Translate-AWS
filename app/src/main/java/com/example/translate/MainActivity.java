package com.example.translate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class MainActivity extends AppCompatActivity {
    private String language;
    Button firstLanguageButton,secondLanguageButton;
    String firstLanguage,secondLanguage;
    int a;
    RadioButton radioButton;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavView();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment=new HomeFragment();
                        break;
                    case R.id.nav_learn:
                        selectedFragment =new LearnFragment();
                        break;
                    case R.id.nav_words:
                        selectedFragment =new WordsFragment();
                        break;
                    case R.id.nav_saved:
                        selectedFragment=new SaveFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment).commit();
                return true;
            }
        };

    private void initBottomNavView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
    }

    public void alert(int b){
        a=b;
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.languages,null);
        radioGroup = view.findViewById(R.id.languages);
        Button buttonApply = view.findViewById(R.id.button_apply);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        AlertDialog alertDialog = alert.create();
        buttonApply.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                firstLanguage=radioButton.getText().toString();
                if (a == 1){
                    firstLanguageButton=findViewById(R.id.button);
                    firstLanguageButton.setText(firstLanguage);
                }
                else {
                    secondLanguageButton=findViewById(R.id.button2);
                    secondLanguage = radioButton.getText().toString();
                    secondLanguageButton.setText(secondLanguage);
                }
            }
        });

        alertDialog.show();
    }


    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton=view.findViewById(radioId);
        System.out.println(radioButton.getText());
    }
}