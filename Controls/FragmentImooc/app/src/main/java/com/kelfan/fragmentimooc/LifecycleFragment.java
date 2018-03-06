package com.kelfan.fragmentimooc;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 5/03/2018.
 */

public class LifecycleFragment extends Fragment {

    /**
     * 每次创建都会绘制Fragment的view组件时回调该方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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
        textView.setText("第一个Fragment");
        Log.i("Main", "Fragment1---onCreateView()");
        return view;
    }


    /**
     * 当Fragment被添加到Activities时候会回调这个方法， 并且只调用一次
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Main", "Fragment1---onAttach()");
    }

    /**
     * 创建Fragment时候回调，只调用一次
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Main", "Fragment1---onCreate()");
    }

    /**
     * 当Fragment所在的Activity启动完成后调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Main", "Fragment1---onActivityCreated()");
    }

    /**
     * 启动Fragment
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.i("Main", "Fragment1---onStart()");
    }

    /**
     * 恢复Fragment时会被回调，调用onStart（）方法后面一定会调用onResume方法
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.i("Main", "Fragment1---onResume()");
    }

    /**
     * 暂停Fragment
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.i("Main", "Fragment1---onPause()");
    }

    /**
     * 停止Fragment
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.i("Main", "Fragment1---onStop()");
    }

    /**
     * 销毁Fragment所包含的view组件时
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Main", "Fragment1---onDestroyView()");
    }

    /**
     * 在销毁Fragment时回调
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Main", "Fragment1---onDestroy()");
    }

    /**
     * Fragment从Activity中删除时会回调该方法 方法只调用一次
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Main", "Fragment1---onDestroy()");
    }
}



















































