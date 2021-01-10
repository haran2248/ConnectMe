package com.hariharan.connectme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Memories extends AppCompatActivity {
ImageView p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);

        p1=findViewById(R.id.c1);
        p2=findViewById(R.id.c2);
        p3=findViewById(R.id.c3);
        p4=findViewById(R.id.c4);
        p5=findViewById(R.id.c5);
        p6=findViewById(R.id.c6);
        p7=findViewById(R.id.c7);
        p8=findViewById(R.id.c8);
        p9=findViewById(R.id.c9);
        p10=findViewById(R.id.c10);
        p11=findViewById(R.id.c11);
        p12=findViewById(R.id.c12);

        p1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p1, ViewCompat.getTransitionName(p1));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.campuspic1); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p2, ViewCompat.getTransitionName(p2));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.cam2); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p3, ViewCompat.getTransitionName(p3));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.cam3); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p4, ViewCompat.getTransitionName(p4));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.cam4); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p5, ViewCompat.getTransitionName(p5));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.cam5); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p6, ViewCompat.getTransitionName(p6));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.googlebits1); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p7, ViewCompat.getTransitionName(p7));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.audibitsg); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p8, ViewCompat.getTransitionName(p8));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.librarybits); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p9.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p9, ViewCompat.getTransitionName(p9));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.library2); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p10.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p10, ViewCompat.getTransitionName(p10));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.pic1); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());
            }
        });
        p11.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p11, ViewCompat.getTransitionName(p11));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.pic2); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });
        p12.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Memories.this,MemoryActivity.class);
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Memories.this,p12, ViewCompat.getTransitionName(p12));
                Bitmap bitmap = BitmapFactory.decodeResource
                        (getResources(), R.drawable.pic3); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent,options.toBundle());

            }
        });

    }
}