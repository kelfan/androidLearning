package com.kelfan.recyclerviewimooc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<MyViewholder> {

    private LayoutInflater sInflater;
    private Context sContext;
    private List<String> sData;

    public SimpleAdapter(Context context, List<String> data) {
        this.sContext = context;
        this.sData = data;
        sInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = sInflater.inflate(R.layout.item_single_textview, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.tv.setText(sData.get(position));
    }

    @Override
    public int getItemCount() {
        return sData.size();
    }
}

class MyViewholder extends RecyclerView.ViewHolder {

    TextView tv;

    public MyViewholder(View itemView) {
        super(itemView);

        tv = itemView.findViewById(R.id.tv);
    }
}