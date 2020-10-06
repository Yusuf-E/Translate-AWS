package com.example.translate;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaDrm;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class LearnFragment extends Fragment {
    EditText answerText;
    TextView questionText;
    Button answerButton;
    private FirebaseAuth registerAuth;
    private String userUid;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    MediaPlayer player;
    int total = 1;
    private MediaPlayer player1;
    ConstraintLayout constraintLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_learn,container,false);

        questionText = ((TextView) v.findViewById(R.id.questionText));
        answerText= ((EditText) v.findViewById(R.id.answerText));
        answerButton= ((Button) v.findViewById(R.id.answerButton));
        constraintLayout = (((ConstraintLayout) v.findViewById(R.id.learnfragment_layout)));


        updateQuestion();

        return v;
    }

    private void updateQuestion() {
        constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        if (total>4){

        }
        else{
            registerAuth=FirebaseAuth.getInstance();
            userUid = registerAuth.getUid();
            database= FirebaseDatabase.getInstance();
            ref=database.getReference().child("Words").child(String.valueOf(total));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Question question = snapshot.getValue(Question.class);
                    questionText.setText("What is the meaning of" +" "+ question.getQuestion());

                    answerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (answerText.getText()!=null&&!(answerText.getText().equals(""))){
                                if (answerText.getText().toString().replaceAll(" ","").equalsIgnoreCase(question.getAnswer())){
                                    player.start();
                                    total++;
                                    Handler handler = new Handler();

                                            constraintLayout.setBackgroundColor(Color.parseColor("#32CD32"));
                                    Handler handler1 = new Handler();
                                    handler1.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            answerText.setText("");
                                            answerText.setHint("Cevabınızı Buraya Yazınız");
                                            updateQuestion();
                                        }
                                    },2000);

                                }
                                else{
                                    player1.start();
                                    total++;
                                    constraintLayout.setBackgroundColor(Color.parseColor("#FF0000"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            answerText.setText("");
                                            answerText.setHint("Cevabınızı Buraya Yazınız");
                                            updateQuestion();
                                        }
                                    },2000);

                                }
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        player=MediaPlayer.create(context,R.raw.correct);
        player1=MediaPlayer.create(context,R.raw.wrong);

    }
}
