package com.example.connectme;


import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Details extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner branch;
    Image image;
    String imgPath;
    boolean imgflag = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    StorageReference storageReference;

    private CheckBox WSC, devsoc, BitsK, QC;
    private RadioButton y1,y2,y3,y4,y5;
    private RadioButton m, f;
    private RadioGroup gend_group, year_group;
    String gender = "";
    String year = "";
    Button sub, upload;
    private TextInputEditText interests, fav_movies, whatsapp_no;
    private TextInputLayout inter_layout, fav_movies_layout, whatsapp_no_layout;
    private DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("users");
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        branch = findViewById(R.id.branch_spinner);
        y1=findViewById(R.id.year1);
        y2=findViewById(R.id.year2);
        y3=findViewById(R.id.year3);
        y4=findViewById(R.id.year4);
        y5=findViewById(R.id.year5);
        sub = findViewById(R.id.submit);
        WSC = findViewById(R.id.WSC_checkbox);
        devsoc = findViewById(R.id.devsoc_checkbox);
        upload = findViewById(R.id.upload);
        year_group = findViewById(R.id.year);
        BitsK = findViewById(R.id.BitsK_checkbox);
        gend_group = findViewById(R.id.gender);
        QC = findViewById(R.id.QC_checkbox);
        m = findViewById(R.id.male);
        f = findViewById(R.id.female);
        interests = findViewById(R.id.interest_EditText);
        inter_layout = findViewById(R.id.interest_layout);
        fav_movies = findViewById(R.id.movies_EditText);
        fav_movies_layout = findViewById(R.id.movies_layout);
        whatsapp_no = findViewById(R.id.whatsapp_no_editext);
        whatsapp_no_layout = findViewById(R.id.whatsapp_no_layout);
        sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.branches, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter);
        branch.setOnItemSelectedListener(this);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.create(Details.this)
                        .single()
                        .folderMode(true)
                        .start();

            }
        });


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").setValue(interests.getText().toString());
                userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("movies").setValue(fav_movies.getText().toString());
                userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("whatsapp").setValue(whatsapp_no.getText().toString());


                if (WSC.isChecked()) {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("WSC").setValue("1");
                }
                else
                {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("WSC").setValue("0");
                }
                if (devsoc.isChecked()) {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DEVSOC").setValue("1");
                }
                else
                {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("DEVSOC").setValue("0");
                }
                if (BitsK.isChecked()) {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BitsK").setValue("1");
                }
                else{
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("BitsK").setValue("0");
                }
                if (QC.isChecked()) {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("QC").setValue("1");
                }
                else
                {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("QC").setValue("0");
                }


                if(y1.isChecked())
                {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("year").setValue("year1");
                }
                if(y2.isChecked())
                {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("year").setValue("year2");
                }
                if(y3.isChecked())
                {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("year").setValue("year3");

                }
                if(y4.isChecked())
                {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("year").setValue("year4");
                }if(y5.isChecked())
                {
                    userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("year").setValue("year5");
                }
                gend_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                        switch (i) {
                            case R.id.male:
                                gender = "Male";
                                userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("gender").child(gender);
                                break;
                            case R.id.female:
                                gender = "Female";
                                userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("gender").child(gender);
                                break;
                        }
                    }
                });
              if(m.isChecked())
              {
                 userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("gender").setValue("male");
              }
              if(f.isChecked())
              {
                  userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("gender").setValue("female");
              }

                userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("Branch").setValue(choice);
             if(!imgflag) {
                 Toast.makeText(Details.this,"No photo attached",Toast.LENGTH_SHORT).show();
             }
             else{
                  storageReference = FirebaseStorage.getInstance().getReference();

                  final StorageReference imgRef = storageReference.child("images");
                  final Uri file = Uri.fromFile(new File(imgPath));
                  final StorageReference myRef = imgRef.child(image.getName());

                  myRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                      @Override
                      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                          Toast.makeText(Details.this, "Image added", Toast.LENGTH_SHORT).show();
                          myRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                              @Override
                              public void onSuccess(Uri uri) {

                                  userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("information").child("Info").child("image").setValue(uri.toString());

                              }
                          });
                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                          Toast.makeText(Details.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });
              }

                Intent intent=new Intent(Details.this,BasicInfoRV.class);
                startActivity(intent);

            }

        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choice = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            image = ImagePicker.getFirstImageOrNull(data);
            imgPath = image.getPath();
            upload.setText(imgPath);
            imgflag = true;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}




