package com.example.ashleyhomestoreclone.Bean.Adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashleyhomestoreclone.Bean.ChatMessageBean;
import com.example.ashleyhomestoreclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static final int MSG_TYPE_LEFT202 = 0;
    public static final int MSG_TYPE_RIGHT202 = 1;

    private Context mContext202;
    private ArrayList<ChatMessageBean> arrayList202;

    FirebaseUser firebaseUser202;

    public MessageAdapter(Context mContext202, ArrayList<ChatMessageBean> arrayList202) {
        this.mContext202 = mContext202;
        this.arrayList202 = arrayList202;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT202) {
            View view = LayoutInflater.from(mContext202).inflate(R.layout.item_message_sent, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext202).inflate(R.layout.item_message_received, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        ChatMessageBean chat202 = arrayList202.get(position);
        holder.txtMessage202.setText(chat202.getMessage());
        holder.txtTime202.setText(chat202.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList202.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage202, txtTime202;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMessage202 = itemView.findViewById(R.id.text_message_body);
            txtTime202 = itemView.findViewById(R.id.text_message_time);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser202 = FirebaseAuth.getInstance().getCurrentUser();
        if(arrayList202.get(position).getSender().equals(firebaseUser202.getUid())) {
            return MSG_TYPE_RIGHT202;
        } else {
            return MSG_TYPE_LEFT202;
        }
    }
}
