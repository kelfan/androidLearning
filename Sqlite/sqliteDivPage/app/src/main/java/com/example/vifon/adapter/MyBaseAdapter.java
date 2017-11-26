package com.example.vifon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vifon.bean.Person;
import com.example.vifon.sqlitedivpage.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Person> list;

    public MyBaseAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            holder=new ViewHolder();
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.tv_id.setText(list.get(i).get_id()+"");
        holder.tv_name.setText(list.get(i).getName()+"");
        holder.tv_age.setText(list.get(i).getAge()+"");
        return convertView;
    }

    static class ViewHolder{
        TextView tv_id,tv_name,tv_age;
    }
}
