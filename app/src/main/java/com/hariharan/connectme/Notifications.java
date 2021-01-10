package com.hariharan.connectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
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
    int c;
    TextView zero,move;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        GoogleSignInAccount googleSignInAccount= GoogleSignIn.getLastSignedInAccount(this);
        currentID=googleSignInAccount.getId();
        Log.i("currentID",currentID);
        zero=findViewById(R.id.zeroNotifs);
        move=findViewById(R.id.accept_notifs);
        zero.setVisibility(View.GONE);
        move.setVisibility(View.GONE);
        notif_RV=findViewById(R.id.notification_rv);
        userRef=FirebaseDatabase.getInstance().getReference().child("users");
        friends= FirebaseDatabase.getInstance().getReference().child("friend_requests");
        notif_RV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        friends.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(currentID).exists())
                {
                    c=1;
                    friends.child(currentID)
                            .orderByChild("request_type")
                            .equalTo("received")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot1) {
                                    for (DataSnapshot childSnapshot : dataSnapshot1.getChildren()) {
                                         receiver_key= childSnapshot.getKey();
                                         Log.i("Receiver key",receiver_key);

                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                    userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            notificationModelArrayList=new ArrayList<NotificationModel>();
                            for(DataSnapshot shot:dataSnapshot.getChildren())
                            {
                                if(shot.child("information").child("Info").child("IDno").getValue().equals(receiver_key))
                                {

                                    Log.i("notifications",shot.child("NotificationModel").getValue(NotificationModel.class).getName());
                                   notificationModelArrayList.add(shot.child("NotificationModel").getValue(NotificationModel.class));
                                   c=1;
                                }

                            }
                            adapter=new NotificationsRVAdapter(notificationModelArrayList,getApplicationContext());
                            notif_RV.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(Notifications.this,"Success",Toast.LENGTH_LONG).show();
                            if(c==0)
                            {
                                zero.setVisibility(View.VISIBLE);
                            }
                            else {
                                move.setVisibility(View.VISIBLE);
                            }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }
                else
                {
                    c=0;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
