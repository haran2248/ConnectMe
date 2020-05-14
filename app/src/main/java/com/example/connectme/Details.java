package com.example.connectme;


import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Details extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
private Spinner branch;

private CheckBox WSC,devsoc,BitsK,QC;

    private RadioButton m,f;
    private RadioGroup gend_group,year_group;
    String gender="";
    String year="";
    Button sub;
    private TextInputEditText interests,fav_movies,whatsapp_no;
    private TextInputLayout inter_layout,fav_movies_layout,whatsapp_no_layout;
    private DatabaseReference userReference= FirebaseDatabase.getInstance().getReference();
    private String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        branch=findViewById(R.id.branch_spinner);
        sub=findViewById(R.id.submit);
        WSC=findViewById(R.id.WSC_checkbox);
        devsoc=findViewById(R.id.WSC_checkbox);
        year_group=findViewById(R.id.year);
        BitsK=findViewById(R.id.BitsK_checkbox);
        gend_group=findViewById(R.id.gender);
        QC=findViewById(R.id.QC_checkbox);
        m=findViewById(R.id.male);
        f=findViewById(R.id.female);
        interests=findViewById(R.id.interest_EditText);
        inter_layout=findViewById(R.id.interest_layout);
        fav_movies=findViewById(R.id.movies_EditText);
        fav_movies_layout=findViewById(R.id.movies_layout);
        whatsapp_no=findViewById(R.id.whatsapp_no_editext);
        whatsapp_no_layout=findViewById(R.id.whatsapp_no_layout);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.branches,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter);
        branch.setOnItemSelectedListener(this);

        userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").setValue(interests.getText().toString());
        userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("movies").setValue(fav_movies.getText().toString());
        userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("whatsapp").setValue(whatsapp_no.getText().toString());
        if(WSC.isChecked())
        {
            userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("1").setValue("WSC");
        }
        if(devsoc.isChecked())
        {
            userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("2").setValue("DEVSOC");
        }
        if(BitsK.isChecked())
        {
            userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("3").setValue("BitsK");
        }
        if(QC.isChecked())
        {
            userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("4").setValue("QC");
        }
        gend_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.male:
                        gender = "Male";
                        break;
                    case R.id.female:
                        gender = "Female";
                        break;
                }
            }
        });
        year_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.year1:
                        year="1st year";
                        break;
                    case R.id.year2:
                        year="2nd year";
                    case R.id.year3:
                        year="3rd year";
                    case R.id.year4:
                        year="4th year";

                }
            }
        });
        userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("year").setValue(year);
        userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("gender").setValue(gender);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choice=parent.getItemAtPosition(position).toString();
        userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Branch").setValue(choice);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
