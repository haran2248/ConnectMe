package com.hariharan.connectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BasicInfoRV extends AppCompatActivity {

    RecyclerView basicInfo;
    InfoRvAdapter adapter;
    SearchView searchView;
    TextView nameTextView;
    DatabaseReference databaseReference;
    ArrayList<Info> infoArrayList;
    ArrayList<Info> newList;
    InfoRvAdapter newAdapter;
    DrawerLayout dl;
    ActionBarDrawerToggle adbt;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return adbt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info_r_v);
        basicInfo=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.search_bar);
        dl=findViewById(R.id.dl);
        adbt=new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        adbt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(adbt);
        adbt.syncState();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.nav);
        View view=navigationView.getHeaderView(0);
        TextView name_side=view.findViewById(R.id.name_Field);
        ImageView prof_pic=view.findViewById(R.id.display_pic);
        DatabaseReference dr=FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name_side.setText(snapshot.child("information").child("Info").getValue(Info.class).getName());
                if(snapshot.child("information").child("Info").getValue(Info.class).getImage()!=null&&snapshot.child("information").child("Info").getValue(Info.class).getImage()!=""){
                    Glide.with(getApplicationContext()).load(snapshot.child("information").child("Info").getValue(Info.class).getImage()).into(prof_pic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.edit_details)
                {
                    Intent intent=new Intent(BasicInfoRV.this,Details.class);
                    startActivity(intent);
                }
                if(id==R.id.profile_logout_activity)
                {
                    Intent intent=new Intent(BasicInfoRV.this,Profile.class);
                    startActivity(intent);
                }
                if(id==R.id.home_page)
                {
                    Toast.makeText(BasicInfoRV.this,"You are on the home page",Toast.LENGTH_LONG).show();
                }
              
                if(id==R.id.notifications_page)
                {
                    Intent intent=new Intent(BasicInfoRV.this,Notifications.class);
                    startActivity(intent);
                }
                if(id==R.id.memories)
                {
                    Intent intent=new Intent(BasicInfoRV.this,Memories.class);
                    startActivity(intent);
                }



                return true;

            }

        });

        basicInfo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //dataRef=FirebaseDatabase.getInstance().getReference().child("users").child("information");






    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!(searchView.equals("")))
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filter(newText);
                    return true;
                }
            });
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infoArrayList=new ArrayList<Info>();
                for(DataSnapshot shot:dataSnapshot.getChildren()) {
                    infoArrayList.add(shot.child("information").child("Info").getValue(Info.class));
                    try {
                        Log.i("PET NAME", shot.child("information").child("Info").getValue(Info.class).getName());
                        Log.i("PET branch", shot.getValue(Info.class).getBranch());
                        Log.i("PET Wing", shot.getValue(Info.class).getImage());
                        Log.i("PET Email", shot.getValue(Info.class).getYear());
                    } catch (Exception ex) {
                        Log.i("error in retrieval", ex.getMessage());
                    }
                }
                adapter=new InfoRvAdapter(infoArrayList,getApplicationContext());
                basicInfo.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(BasicInfoRV.this,"Success",Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BasicInfoRV.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void filter(String toStr) {

        newList=new ArrayList<Info>();
        for(Info item:infoArrayList)
        {
            if(item.getName().toLowerCase().contains(toStr.toLowerCase())||item.getBranch().toLowerCase().contains(toStr.toLowerCase())||item.getInterests().toLowerCase().contains(toStr.toLowerCase()))
            {
                newList.add(item);
            }

        }

        newAdapter=new InfoRvAdapter(newList,getApplicationContext());
        basicInfo.setAdapter(newAdapter);


    }
}
