package com.hariharan.connectme;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spark.submitbutton.SubmitButton;

import java.util.ArrayList;

public class InfoRvAdapter extends RecyclerView.Adapter<InfoRvAdapter.InfoViewHolder> {
    @NonNull
    ArrayList<Info> infoArrayList;
    Context context;

    public InfoRvAdapter(@NonNull ArrayList<Info> info, Context context) {
        this.infoArrayList = info;
        this.context = context;
    }

    public InfoRvAdapter.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InfoRvAdapter.InfoViewHolder holder, int position) {
        holder.populate(infoArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return infoArrayList.size();
    }


    public class InfoViewHolder extends RecyclerView.ViewHolder {
        TextView name,br,year,em,interest,from,Id;
        ImageView dp;
        SubmitButton next;
        String email="",Year;
        Info infos;


        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.profile_name);
            br=itemView.findViewById(R.id.Branch);
            year=itemView.findViewById(R.id.profile_year);
            interest=itemView.findViewById(R.id.interest);
            from=itemView.findViewById(R.id.from);
            dp=itemView.findViewById(R.id.display_pic);
            next=itemView.findViewById(R.id.view_profile);
            //email=em.toString();
            Year=year.toString();



            next.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override

                public void onClick(View v) {
                    int position=getAdapterPosition();
                    Intent intent=new Intent(itemView.getContext(),UserProfile.class);
                    intent.putExtra("unique", infoArrayList.get(position).getEmail());
                    intent.putExtra("uniqueID",infoArrayList.get(position).getIDno());
                    Pair[] pairs=new Pair[5];
                    pairs[0]=new Pair<View,String>(dp,"imageTrans");
                    pairs[1]=new Pair<View,String>(name,"NameTrans");
                    pairs[2]=new Pair<View,String>(year,"YearTrans");
                    pairs[3]=new Pair<View,String>(em,"EmailTrans");
                    pairs[4]=new Pair<View,String>(br,"branchtrans");
                    ActivityOptions options = ActivityOptions.
                            makeSceneTransitionAnimation((Activity) itemView.getContext(),name, ViewCompat.getTransitionName(dp));
                    if((Year!=null||Year.equals("")))
                    {itemView.getContext().startActivity(intent,options.toBundle());}
                    else {
                        Toast.makeText(context,"Details incomplete",Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }

        public void populate(Info info) {

            name.setText(info.getName());
            br.setText(info.getBranch());
            year.setText(info.getYear());
            interest.setText(info.getInterests());
            from.setText(info.getMovies());
            Year=info.getYear();
            //if(info.getImage()!=null && (!info.getImage().equals(""))) {
                Glide.with(context).load(info.getImage()).into(dp);
            //}

            //em.setText(info.getEmail());

        }


    }
}
