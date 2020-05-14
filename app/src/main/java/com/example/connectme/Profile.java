package com.example.connectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

   private MaterialButton nextActivity,logout;
  private   TextView name,email,id,family_name;
   private ImageView profile;
   String Fbname,FbEmail,Fbid,Fn;
   FirebaseAuth auth;
   DatabaseReference databaseReference;
    GoogleSignInClient mGoogleSignInClient;
private GoogleApiClient googleApiClient;
private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
         gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        nextActivity=findViewById(R.id.next);
        logout=findViewById(R.id.logout);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        id=findViewById(R.id.id);
        profile=findViewById(R.id.profile_pic);
        family_name=findViewById(R.id.family_name);



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
nextActivity.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(Fbname);
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Email").setValue(FbEmail);
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("IDno").setValue(Fbid);
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("familyName").setValue(Fn);
        Intent next=new Intent(Profile.this, Details.class);
        startActivity(next);



    }
});


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            name.setText(acct.getDisplayName());
            email.setText(acct.getEmail());
            id.setText(acct.getId());
            Picasso.get().load(acct.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(profile);
            family_name.setText(acct.getFamilyName());
            FbEmail=acct.getEmail();
            Fbname=acct.getDisplayName();
            Fbid=acct.getId();
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
    }

}
