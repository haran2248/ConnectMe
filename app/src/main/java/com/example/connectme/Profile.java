package com.example.connectme;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

   private MaterialButton nextActivity,logout;
  private   TextView name,email,id,family_name;
   private ImageView profile;
   String Fbname="",FbEmail="",Fbid="",Fn="";
   FirebaseAuth auth;
   DatabaseReference databaseReference;
   DrawerLayout drawerLayout;
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
        profile=findViewById(R.id.profile_pic);
        drawerLayout=findViewById(R.id.dl);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(R.id.navigation);
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
                    Intent intent1=new Intent(Profile.this,BasicInfoRV.class);
                    startActivity(intent1);
                }
               if(id==R.id.notifications_page)
                {
                    Intent intent=new Intent(Profile.this,Notifications.class);
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
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("Email").setValue(acct.getEmail());
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("IDno").setValue(acct.getId());
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("familyName").setValue(acct.getFamilyName());
        Intent next=new Intent(Profile.this, Details.class);
        startActivity(next);



    }
});



        if (acct != null) {
            name.setText(acct.getDisplayName());
            email.setText(acct.getEmail());
            id.setText(acct.getId());
            Picasso.get().load(acct.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(profile);
            Fn=acct.getFamilyName();



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
        Intent intent=new Intent(Profile.this,MainActivity.class);
        startActivity(intent);
    }

}
