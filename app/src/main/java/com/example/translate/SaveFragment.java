package com.example.translate;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SaveFragment extends Fragment {


    private GroupAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    RecyclerView recyclerView;
    private String userid;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_saved,container,false);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         recyclerView = ((RecyclerView) view.findViewById(R.id.recyclerView2));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new GroupAdapter<GroupieViewHolder>();
        recyclerView.setAdapter(adapter);
        database= FirebaseDatabase.getInstance();
        userid=FirebaseAuth.getInstance().getUid();
        ref=database.getReference().child("UserWords").child(userid);
        System.out.println(ref);
        Query query = ref.orderByValue();
        System.out.println(query);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {

                    if (child.child("translatedText").getValue()!=null){
                        System.out.println(  child.child("previousText").getValue());
                        System.out.println(child.child("translatedText").getValue());
                        adapter.add(new WordsStorage(child.child("previousText").getValue().toString(),child.child("translatedText").getValue().toString()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
