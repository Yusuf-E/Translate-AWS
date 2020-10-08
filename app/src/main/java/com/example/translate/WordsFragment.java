package com.example.translate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;

import java.util.ArrayList;

public class WordsFragment extends Fragment {
    private RecyclerView recylerView;
    private GroupAdapter adapter;
    private FirebaseUser user;
    private FirebaseAuth registerAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private String userUid;
    int queue=0;
    private Iterable<DataSnapshot> user1;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_words,container,false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recylerView = ((RecyclerView) view.findViewById(R.id.recyclerView));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recylerView.setLayoutManager(linearLayoutManager);
        //recylerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new GroupAdapter<GroupieViewHolder>();
        recylerView.setAdapter(adapter);

        database= FirebaseDatabase.getInstance();
        ref=database.getReference().child("users");
        Query query = ref.orderByChild("score");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                queue= (int) snapshot.getChildrenCount();
                for (DataSnapshot child : snapshot.getChildren()) {

                    System.out.println((child.child("score").getValue()));
                    System.out.println(child.child("username").getValue().toString());
                    if ((child.child("username").getValue().toString())!=null){
                        adapter.add(new Score(queue,child.child("username").getValue().toString(),child.child("score").getValue().toString()));
                        queue--;
                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
