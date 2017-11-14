package com.example.chaofanz.textappend;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveFile(View view) {
        EditText themeText = (EditText) findViewById(R.id.themeTxt);
        EditText contentText = (EditText) findViewById(R.id.contentTxt);
        String themeMsg = "\"" + themeText.getText().toString() + "\"";
        String contentMsg = "\"" + contentText.getText().toString() + "\"";
        Date currentTime = Calendar.getInstance().getTime();
        String message = themeMsg + ',' + contentMsg + ',' + currentTime + "\n";
        if (contentMsg.equals("\"\"")){
            Toast.makeText(getApplicationContext(), "no contents" , Toast.LENGTH_SHORT).show();
        }else {
            generateNoteOnSD(getApplicationContext(),"fanLog", message);
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
