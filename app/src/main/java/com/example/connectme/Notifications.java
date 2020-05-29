package com.example.connectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {
    DatabaseReference friends,userRef;
    String currentID;
    String receiver_key;
    RecyclerView notif_RV;
    ArrayList<NotificationModel> notificationModelArrayList;
    NotificationsRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        GoogleSignInAccount googleSignInAccount= GoogleSignIn.getLastSignedInAccount(this);
        currentID=googleSignInAccount.getId();
        notif_RV=findViewById(R.id.recyclerView);
        userRef=FirebaseDatabase.getInstance().getReference().child("users");
        friends= FirebaseDatabase.getInstance().getReference().child("friend_requests");
        friends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(currentID).exists())
                {
                    friends.child(currentID)
                            .orderByChild("request_type")
                            .equalTo("received")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                         receiver_key = childSnapshot.getKey();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                    userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot shot:dataSnapshot.getChildren())
                            {
                                if(shot.child("information").child("Info").child("IDno").getValue().toString().equalsIgnoreCase(receiver_key))
                                {
                                   notificationModelArrayList.add(shot.child("NotificationModel").getValue(NotificationModel.class)) ;
                                }
                            }
                            adapter=new NotificationsRVAdapter(notificationModelArrayList,getApplicationContext());
                            notif_RV.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(Notifications.this,"Success",Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
