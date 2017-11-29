package com.example.chaofanz.mycalendar.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chaofanz.mycalendar.R;
import com.example.chaofanz.mycalendar.bean.Event;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class EventListAdapater extends BaseAdapter {
    private Context context;
    private List<Event> list;

    public EventListAdapater(Context context, List<Event> list) {
        this.context = context;
        this.list = list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setList(List<Event> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.list_event,null);
            holder = new ViewHolder();
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            holder.tv_genre = (TextView) view.findViewById(R.id.tv_genre);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_content.setText(list.get(i).getContent());
        holder.tv_genre.setText(list.get(i).getStartDay());
        return view;
    }

    static class ViewHolder{
        TextView tv_genre, tv_content;
    }
}
