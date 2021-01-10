package com.hariharan.connectme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class NotificationsRVAdapter extends RecyclerView.Adapter<NotificationsRVAdapter.NotifsViewHolder> {
    ArrayList<NotificationModel> notificationModelArrayList;
    Context context;

    public NotificationsRVAdapter(ArrayList<NotificationModel> notificationModelArrayList, Context context) {
        this.notificationModelArrayList = notificationModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationsRVAdapter.NotifsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsRVAdapter.NotifsViewHolder holder, int position) {
        holder.populate(notificationModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }

    public class NotifsViewHolder extends RecyclerView.ViewHolder {
     private  TextView senderTextView,receiverTextView;
       private TextView whatsapp;
       FirebaseAuth mAuth;

        public NotifsViewHolder(@NonNull View itemView) {
            super(itemView);

            receiverTextView=itemView.findViewById(R.id.receiver);
        }

        public void populate(NotificationModel notificationModel) {
            receiverTextView.setText(notificationModel.getName());
            Log.i("Receiver name",notificationModel.getName());
        }
    }
}
