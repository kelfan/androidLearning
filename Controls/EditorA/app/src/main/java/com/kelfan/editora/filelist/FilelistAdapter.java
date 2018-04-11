package com.kelfan.editora.filelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelfan.editora.R;

import java.util.List;

public class FilelistAdapter extends RecyclerView.Adapter<FilelistViewholder> implements View.OnClickListener{

    private LayoutInflater fInflater;
    private Context fContext;
    private List<String> fData;
    private FilelistAdapter.OnItemClickListener fOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);


    }

    public FilelistAdapter(Context context, List<String> data) {
        this.fContext = context;
        this.fData = data;
        fInflater = LayoutInflater.from(context);
    }

    @Override
    public FilelistViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = fInflater.inflate(R.layout.file_list_item, parent, false);
        FilelistViewholder filelistViewholder = new FilelistViewholder(view);
        view.setOnClickListener(this);
        return filelistViewholder;
    }

    @Override
    public void onBindViewHolder(FilelistViewholder holder, int position) {
        holder.filelistTextView.setText(fData.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return fData.size();
    }

    @Override
    public void onClick(View view) {
        if (fOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            fOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.fOnItemClickListener = listener;
    }

}

class FilelistViewholder extends RecyclerView.ViewHolder {

    TextView filelistTextView;
    ImageView filelistImageView;

    public FilelistViewholder(View itemView) {
        super(itemView);
        filelistTextView = itemView.findViewById(R.id.file_list_tv);
        filelistImageView = itemView.findViewById(R.id.file_list_icon);
    }
}