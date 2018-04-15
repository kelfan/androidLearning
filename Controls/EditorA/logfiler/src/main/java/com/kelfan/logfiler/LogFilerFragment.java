package com.kelfan.logfiler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LogFilerFragment extends Fragment {

    private String filepath = "";

    public Fragment setFilepath(String fpath) {
        this.filepath = fpath;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_filer_fragment, container, false);
        TextView textView = view.findViewById(R.id.log_filer_text);
        textView.setText(filepath);

        return view;
    }
}
