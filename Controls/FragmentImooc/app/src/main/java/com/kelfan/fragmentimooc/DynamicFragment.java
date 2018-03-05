package com.kelfan.fragmentimooc;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 5/03/2018.
 */

public class DynamicFragment extends Fragment {
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
        textView.setText("动态加载Fragment");

        return view;
    }
}
