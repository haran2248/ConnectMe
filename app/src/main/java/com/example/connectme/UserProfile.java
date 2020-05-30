package com.example.connectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserProfile extends AppCompatActivity {
    String email_in_final_page="",Idno="";
   private TextView email_in_xml,name_in_xml,year_in_xml,interests_in_xml,branch_in_xml,gender_in_xml,fav_movies_in_xml,devsoc_in_xml,wsc_xml,qc_xml,Bits_K_xml,whatsapp_xml,whatsappNO_xml;
   FirebaseAuth mAuth;
   ImageView display_in_final_page;
   DatabaseReference databaseReference;
   DatabaseReference dataRef,friendRef;
   Button sendRequest,decline;
   String current="";
   String currentUser="",saveDate,currentAuth;
   // final GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        attachID();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
        dataRef=FirebaseDatabase.getInstance().getReference().child("friend_requests");
        friendRef=FirebaseDatabase.getInstance().getReference().child("friends");
        email_in_final_page=getIntent().getExtras().getString("unique");
        email_in_xml.setText(email_in_final_page);
        Idno=getIntent().getExtras().getString("uniqueID");
        currentAuth=FirebaseAuth.getInstance().getCurrentUser().getEmail();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot:dataSnapshot.getChildren())
                {
                    if(shot.child("information").child("Info").getValue(Info.class).getEmail().equals(currentAuth))
                    {
                        currentUser=shot.child("information").child("Info").getValue(Info.class).getIDno().toString();
                    }
                    if((shot.child("information").child("Info").getValue(Info.class).getEmail()).equals(email_in_final_page))
                    {
                        Glide.with(getApplicationContext()).load(shot.child("information").child("Info").getValue(Info.class).getImage()).into(display_in_final_page);
                        interests_in_xml.setText(shot.getValue(User.class).getInterests());


                        Log.i("Interests",shot.getValue(User.class).getInterests());
                        name_in_xml.setText(shot.child("information").child("Info").getValue(Info.class).getName());
                        Log.i("name",shot.child("information").child("Info").getValue(Info.class).getName());
                        year_in_xml.setText(shot.child("information").child("Info").getValue(Info.class).getYear());
                        branch_in_xml.setText(shot.child("information").child("Info").getValue(Info.class).getBranch());
                        fav_movies_in_xml.setText(shot.getValue(User.class).getMovies());
                        gender_in_xml.setText(shot.getValue(User.class).getGender());
                        if(shot.getValue(User.class).getBitsK().equals("1"))
                        {
                            Bits_K_xml.setText("BitsKrieg");
                        }
                        else
                        {
                            Bits_K_xml.setVisibility(View.GONE);
                        }
                        if(shot.getValue(User.class).getQC().equals("1"))
                        {
                            qc_xml.setText("QUARK CONTROLS");
                        }
                        else
                        {
                            qc_xml.setVisibility(View.GONE);
                        }
                        if(shot.getValue(User.class).getDEVSOC().equals("1"))
                        {
                            devsoc_in_xml.setText("DEVSOC");
                        }
                        else
                        {
                            devsoc_in_xml.setVisibility(View.GONE);
                        }
                        if(shot.getValue(User.class).getWSC().equals("1"))
                        {
                            wsc_xml.setText("WallStreet");
                        }
                        else
                        {
                            wsc_xml.setVisibility(View.GONE);
                        }


                    }

                  Log.i("Email",shot.child("information").child("Info").getValue(Info.class).getEmail());
                    Log.i("PET NAME", shot.child("information").child("Info").getValue(Info.class).getName());
                }
                maintainance();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current.equals("not friends"))
                {
                    sendFriendRequest();
                    Log.i("Request","request sent");
                }
                else if(current.equals("request_sent")) {
                    CancelRequest();
                }
                else if(current.equals("request_received"))
                {
                    AcceptRequest();
                }

            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declineRequest();
            }
        });





    }

    private void declineRequest() {
        dataRef.child(currentUser).child(Idno).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataRef.child(Idno).child(currentUser).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        current="not friends";
                                        decline.setVisibility(View.GONE);
                                        sendRequest.setText("Request Phone no");
                                    }
                                });
                    }
                });
    }



    private void AcceptRequest() {
        Calendar curDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("dd-mm-yyyy");
        saveDate=currentDate.format(curDate.getTime());

    friendRef.child(currentUser).child(Idno).child("date").setValue(saveDate)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    friendRef.child(Idno).child(currentUser).child("date").setValue(saveDate)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    dataRef.child(currentUser).child(Idno).removeValue()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    dataRef.child(Idno).child(currentUser).removeValue()
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    current="friends";
                                                                    sendRequest.setText("Friends");
                                                                    decline.setVisibility(View.GONE);
                                                                    decline.setEnabled(false);

                                                                }
                                                            });
                                                }
                                            });

                                }
                            });
                }
            });

    }

    private void CancelRequest() {

        dataRef.child(currentUser).child(Idno).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataRef.child(Idno).child(currentUser).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        current="not friends";
                                        sendRequest.setText("Request phone No");
                                        decline.setVisibility(View.GONE);
                                        decline.setEnabled(false);

                                    }
                                });
                    }
                });


    }

    private void maintainance() {
        if(currentUser.equals(Idno))
        {
            sendRequest.setVisibility(View.GONE);
            decline.setVisibility(View.GONE);
        }
        dataRef.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(Idno))
                {
                    String request_type=dataSnapshot.child(Idno).child("request_type").getValue().toString();
                    if(request_type.equals("sent"))
                    {
                        current="request_sent";
                        sendRequest.setText("Cancel Request");
                        decline.setVisibility(View.INVISIBLE);
                    }
                    if(request_type.equals("received"))
                    {
                        current="request_received";
                        sendRequest.setText("Accept Request");
                        decline.setVisibility(View.VISIBLE);
                        decline.setText("Decline request");


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        friendRef.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(Idno))
                {
                    String date=dataSnapshot.child(Idno).child("date").getValue().toString();
                    if(!(date.equals("")))
                    {
                        sendRequest.setText("Friends");
                        current = "request_accepted";
                        decline.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                friendRef.child(currentUser).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(Idno)) {
                            String date2 = dataSnapshot.child(Idno).child("date").getValue().toString();
                            if (!(date2.equals(""))) {
                                for (DataSnapshot shot : dataSnapshot1.getChildren()) {
                                    if ((shot.child("information").child("Info").getValue(Info.class).getIDno()).equals(Idno)) {
                                        whatsapp_xml.setVisibility(View.VISIBLE);
                                        whatsappNO_xml.setVisibility(View.VISIBLE);
                                        whatsappNO_xml.setText(shot.getValue(User.class).getWhatsapp());
                                    }

                                }
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    private void sendFriendRequest() {
        Log.i("first req","entered the function");



        dataRef.child(currentUser).child(Idno).child("request_type").setValue("sent")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataRef.child(Idno).child(currentUser).child("request_type").setValue("received")
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        current="request_sent";
                                        sendRequest.setText("cancel request");
                                    }
                                });

                    }
                });
        Log.i("first req","sent");

    }


    private void attachID() {
        email_in_xml=findViewById(R.id.Email_in_final_page);
        name_in_xml=findViewById(R.id.profile_name_in_user_profile);
        year_in_xml=findViewById(R.id.profile_year_in_user_profile);
        interests_in_xml=findViewById(R.id.Interests_text_in_user_profile);
        branch_in_xml=findViewById(R.id.Branch_in_user_profile);
        gender_in_xml=findViewById(R.id.gender_in_user_profile);
        sendRequest=findViewById(R.id.send);
        fav_movies_in_xml=findViewById(R.id.Fav_movies_in_user_profile);
        devsoc_in_xml=findViewById(R.id.devsoc_club_in_user_profile);
        wsc_xml=findViewById(R.id.WSC_club_in_user_profile);
        qc_xml=findViewById(R.id.Quark_club_in_user_profile);
        display_in_final_page=findViewById(R.id.display_pic_in_user_profile);
        Bits_K_xml=findViewById(R.id.BITSK_club_in_user_profile);
        decline=findViewById(R.id.back);
        current="not friends";
        whatsapp_xml=findViewById(R.id.Whatsapp_text_in_user_profile);
        whatsappNO_xml=findViewById(R.id.WhatsappNO_in_final_page);
        whatsappNO_xml.setVisibility(View.GONE);
        whatsapp_xml.setVisibility(View.GONE);
        decline.setVisibility(View.GONE);
    }
}
