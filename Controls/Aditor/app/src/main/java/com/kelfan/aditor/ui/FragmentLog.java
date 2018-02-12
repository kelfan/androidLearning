package com.kelfan.aditor.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelfan.aditor.R;
import com.kelfan.aditor.Util.FileHandler;
import com.kelfan.aditor.Util.StringWorker;

/**
 * Created by Administrator on 11/02/2018.
 */

public class FragmentLog extends Fragment {

    public TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Refresh(view);
    }

    public static void Refresh(View view) {
        TextView textView = view.findViewById(R.id.log_tv);
        String content = FileHandler.read_log_file("4season");
        content = StringWorker.stringSortByLine(content, true);
        textView.setText(content);
    }
}
