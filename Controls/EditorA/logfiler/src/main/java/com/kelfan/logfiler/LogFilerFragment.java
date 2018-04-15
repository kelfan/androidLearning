package com.kelfan.logfiler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LogFilerFragment extends Fragment {

    private String filepath = "";
    private ArrayList<String> lData = new ArrayList<String>();
    private LogFilerRecyclerViewAdapter logFilerRecyclerViewAdapter;

    public LogFilerFragment setFilepath(String fpath) {
        this.filepath = fpath;
        return this;
    }

    public LogFilerFragment setData(ArrayList<String> data){
        this.lData = data;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_filer_fragment, container, false);

        RecyclerView fileRecyclerView =view.findViewById(R.id.log_recycler_view);
        logFilerRecyclerViewAdapter = new LogFilerRecyclerViewAdapter(this.getActivity(),lData);
        fileRecyclerView.setAdapter(logFilerRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        fileRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}