package com.example.chaofanz.textappend;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView themeTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeTxt = findViewById(R.id.themeTxt);
        contentTxt = findViewById(R.id.contentTxt);
        themeTxt.setText("四季理论");

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action)&&type!=null){
            if ("text/plain".equals(type)){
                dealTextMessage(intent);
            }
        }
    }

    void dealTextMessage(Intent intent){
        String share = intent.getStringExtra(Intent.EXTRA_TEXT);
        String title = intent.getStringExtra(Intent.EXTRA_TITLE);
        themeTxt.setText("Share");
        if(title != null && !"".equals(title)){
            themeTxt.setText(title);
        }
        contentTxt.setText(share);
    }

    public void saveFile(View view) {
        String themeMsg = "\"" + themeTxt.getText().toString() + "\"";
        String contentMsg = "\"" + contentTxt.getText().toString() + "\"";
        Date currentTime = Calendar.getInstance().getTime();
        String message = themeMsg + ',' + contentMsg + ',' + currentTime + "\n";
        if (contentMsg.equals("\"\"")){
            Toast.makeText(getApplicationContext(), "no contents" , Toast.LENGTH_SHORT).show();
        }else {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            String fileName = "fanlog" + df.format(c).toString();
            generateNoteOnSD(getApplicationContext(),fileName, message);
        }
    }

    /* write a text file in SDCard  */
    public void generateNoteOnSD(Context context, String sFileName, String sBody){
        try{
            // File root = new File(Environment.getExternalStorageDirectory(), "05fanNotes");
            File root = new File(System.getenv("SECONDARY_STORAGE"), "05fanNotes");
            if(!root.exists()){
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
//            gpxfile.createNewFile();
            FileWriter writer = new FileWriter(gpxfile,true);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved" + sBody , Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
