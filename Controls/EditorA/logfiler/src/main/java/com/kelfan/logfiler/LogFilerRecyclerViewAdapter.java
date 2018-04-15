package com.kelfan.logfiler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class LogFilerRecyclerViewAdapter extends RecyclerView.Adapter<LogFilerItemViewHolder> {

    private LayoutInflater fInflater;
    private Context fContext;
    private ArrayList<String> fData;

    public LogFilerRecyclerViewAdapter(Context context, ArrayList<String> data) {
        this.fContext = context;
        this.fData = data;
        fInflater = LayoutInflater.from(context);
    }

    @Override
    public LogFilerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = fInflater.inflate(R.layout.log_item_view, parent, false);
        LogFilerItemViewHolder filelistViewholder = new LogFilerItemViewHolder(view);
        return filelistViewholder;
    }

    @Override
    public void onBindViewHolder(LogFilerItemViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.textView.setTag(position);
        holder.textView.setText(fData.get(position));
    }

    @Override
    public int getItemCount() {
        return fData.size();
    }
}
