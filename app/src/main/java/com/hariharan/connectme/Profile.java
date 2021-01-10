package com.hariharan.connectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

   private MaterialButton nextActivity,logout;
  private   TextView name,email,id,family_name;
   private ImageView profile;
   String Fbname="",FbEmail="",Fbid="",Fn="";
   FirebaseAuth auth;
   DatabaseReference databaseReference;
   DrawerLayout drawerLayout;
   int c=0;
   ActionBarDrawerToggle actionBarDrawerToggle;
    GoogleSignInClient mGoogleSignInClient;
private GoogleApiClient googleApiClient;
private GoogleSignInOptions gso;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
         gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
        nextActivity=findViewById(R.id.next);
        logout=findViewById(R.id.logout);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        id=findViewById(R.id.id);
        id.setVisibility(View.GONE);
        profile=findViewById(R.id.profile_pic);
        drawerLayout=findViewById(R.id.dl);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.navigation);
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
                    Intent intent=new Intent(Profile.this,Details.class);
                    startActivity(intent);
                }
                if(id==R.id.profile_logout_activity)
                {
                    Toast.makeText(Profile.this,"You are on the logout page",Toast.LENGTH_LONG).show();
                }
                if(id==R.id.home_page)
                {
                    dr.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child("information").child("Info").getValue(Info.class).getYear() == null){
                                Toast.makeText(Profile.this,"You must fill details first,Click next",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Intent intent1=new Intent(Profile.this,BasicInfoRV.class);
                                startActivity(intent1);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
               if(id==R.id.notifications_page)
                {
                    Intent intent=new Intent(Profile.this,Notifications.class);
                    startActivity(intent);
                }
               if(id==R.id.memories){
                   Intent intent=new Intent(Profile.this,Memories.class);
                   startActivity(intent);
               }




                return true;

            }
        });




logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // ...
            case R.id.logout:
                signOut();

                break;
            // ...
        }

    }
});
        final GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
nextActivity.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (acct != null) {
            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("name").setValue(acct.getDisplayName());
            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("NotificationModel").child("name").setValue(acct.getDisplayName());
        }
        if (acct != null) {
            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("Email").setValue(acct.getEmail());
        }
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("IDno").setValue(acct.getId());
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("familyName").setValue(acct.getFamilyName());


        if(c==0) {
            c++;
            Intent intent = new Intent(Profile.this, Details.class);
            startActivity(intent);

        }
        else
        {
            Intent i=new Intent(Profile.this,BasicInfoRV.class);
            startActivity(i);
        }



    }
});



        if (acct != null) {
            name.setText(acct.getDisplayName());
            email.setText(acct.getEmail());
            id.setText(acct.getId());
            Picasso.get().load(acct.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(profile);
            Fn=acct.getFamilyName();
            String domain = acct.getEmail().split("@")[1];
            if(!(domain.equals("goa.bits-pilani.ac.in"))){
                signOut();
                Toast.makeText(Profile.this,"Please use bitsmail",Toast.LENGTH_LONG).show();
            }



        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Profile.this,"signed out",Toast.LENGTH_SHORT);
                        finish();

                    }
                });
        Intent next=new Intent(Profile.this, MainActivity.class);
        startActivity(next);

    }

}
