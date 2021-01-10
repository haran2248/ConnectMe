package com.hariharan.connectme;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MemoryActivity extends AppCompatActivity {

    ImageView bigpic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        bigpic=findViewById(R.id.full);
        if(getIntent().hasExtra("byteArray")) {
            ImageView imv= new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
            bigpic.setImageBitmap(bitmap);
        }

    }
}