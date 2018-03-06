package com.kelfan.fragmentimooc;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 5/03/2018.
 */

public class StaticCommuteFragment extends Fragment {

    private String aaa;

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // layout 布局文件转换成View对象

        /**
         * resource: Fragment 需要加载的布局文件
         * Root: 加载Layout的父ViewGroup
         * attactToRoot: False, 不返还父ViewGroup
         */
        View view = inflater.inflate(R.layout.fragment, container, false);
        TextView textView = view.findViewById(R.id.text);
        Button button = view.findViewById(R.id.button);
        textView.setText("静态加载Fragment");
        button.setText("获取内容");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aaa = getAaa();
                Toast.makeText(getActivity(), "value=" + aaa, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
