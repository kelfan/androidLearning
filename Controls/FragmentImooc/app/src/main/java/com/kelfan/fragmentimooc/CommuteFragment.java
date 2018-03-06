package com.kelfan.fragmentimooc;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jzheng4 on 6/03/2018.
 */

public class CommuteFragment extends Fragment {

    private String code = "Thank you, Activity!";

    public MyListener myListener;
    public interface MyListener{
        public void thank(String code);
    }

    @Override
    public void onAttach(Activity activity) {
        myListener = (MyListener) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commute, container, false);
        TextView textView=view.findViewById(R.id.text);
        String text = getArguments().get("name").toString();
        textView.setText(text);
        Toast.makeText(getActivity(), "已成功接受到" + text, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "向Activity发送" + code, Toast.LENGTH_SHORT).show();
        myListener.thank(code);
        return view;
    }
}
