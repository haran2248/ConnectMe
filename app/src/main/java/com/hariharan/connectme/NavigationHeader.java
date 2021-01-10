package com.hariharan.connectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NavigationHeader extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference dr;
    ImageView imgref;
    TextView nameNav,YearNav,BranchNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_header);
        nameNav=findViewById(R.id.name_Field);
        imgref=findViewById(R.id.display_pic);
        dr=FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nameNav.setText(snapshot.child("information").child("Info").getValue(Info.class).getName());
                YearNav.setText(snapshot.child("information").child("Info").getValue(Info.class).getYear());
                BranchNav.setText(snapshot.child("information").child("Info").getValue(Info.class).getBranch());
                if(snapshot.child("information").child("Info").getValue(Info.class).getImage()!=null&&snapshot.child("information").child("Info").getValue(Info.class).getImage()!=""){
                    Glide.with(getApplicationContext()).load(snapshot.child("information").child("Info").getValue(Info.class).getImage()).into(imgref);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}