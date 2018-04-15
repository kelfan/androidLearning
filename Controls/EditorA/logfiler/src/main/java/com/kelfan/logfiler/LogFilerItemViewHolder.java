package com.kelfan.logfiler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LogFilerItemViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public LogFilerItemViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.log_item_text_view);

    }
}
