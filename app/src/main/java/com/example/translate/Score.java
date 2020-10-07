package com.example.translate;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

public class Score extends Item{
    private  String queue;
    private  String name,score;
    private TextView queueView,nameView,scoreView;
    public  Score(){

    }
    public Score(int queue,String name,String score) {
        this.queue=String.valueOf(queue);
        this.name=name;
        this.score = score;



    }
    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        queueView=viewHolder.itemView.findViewById(R.id.queue);
        nameView=viewHolder.itemView.findViewById(R.id.name);
        scoreView=viewHolder.itemView.findViewById(R.id.score);
        queueView.setText(queue);
        nameView.setText(this.name);
        scoreView.setText(this.score);
        queueView.setText(this.queue);

    }




    @Override
    public int getLayout() {
        return R.layout.score_layout;
    }
}
