package com.example.chaofanz.readfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button readBtn;
    TextView textTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readBtn = (Button) findViewById(R.id.readBtn);
        textTv = (TextView) findViewById(R.id.textTv);

        readBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text = "";
                try{
                    InputStream is = getAssets().open("file.txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);
                }catch (IOException ex) {
                    ex.printStackTrace();;
                }
                textTv.setText(text);
            }
        });
    }
}
