package com.example.connectme;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
        TextView name,br,year,em,Id;
        ImageView dp;
        Button next;
        String email="";

        Info infos;


        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.profile_name);
            br=itemView.findViewById(R.id.Branch);
            year=itemView.findViewById(R.id.profile_year);
            em=itemView.findViewById(R.id.Email);

            dp=itemView.findViewById(R.id.display_pic);
            next=itemView.findViewById(R.id.view_profile);
            email=em.toString();

            next.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    int position=getAdapterPosition();
                    Intent i=new Intent(itemView.getContext(),UserProfile.class);
                    i.putExtra("unique", infoArrayList.get(position).getEmail());
                    i.putExtra("uniqueID",infoArrayList.get(position).getIDno());
                    itemView.getContext().startActivity(i);
                }
            });



        }

        public void populate(Info info) {

            name.setText(info.getName());
            br.setText(info.getBranch());
            year.setText(info.getYear());
            if(info.getImage()!=null && (!info.getImage().equals(""))) {
                Glide.with(context).load(info.getImage()).into(dp);
            }
            em.setText(info.getEmail());

        }


    }
}
