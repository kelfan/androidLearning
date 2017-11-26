package com.example.administrator.autocompletetextviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView acTextView;
    private MultiAutoCompleteTextView macTextView;
    private String[] res = {"beijing1","beijing2","shanghai1","shanghai2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** autoCompleteTextView
         * 1. initial controls
         * 2. need an adapter
         * 3. Create dataset -> which match with Key word
         * 4. binding adapter & autoCompleteTextView
         */

        acTextView = findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,res);
        acTextView.setAdapter(adapter);

        /** multiAutoCompleteTextView
         * 1. initial controls
         * 2. need an adapter
         * 3. Create dataset -> which match with Key word
         * 4. binding adapter & autoCompleteTextView
         * 5. setup separator
         */

        macTextView = findViewById(R.id.multiAutoCompleteTextView1);
        macTextView.setAdapter(adapter);
        // set comma as separator
        macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
