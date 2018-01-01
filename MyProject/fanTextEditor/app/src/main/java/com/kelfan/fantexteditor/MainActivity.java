package com.kelfan.fantexteditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText contentTxt;
    Button saveBtn, sortBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTxt = findViewById(R.id.contentTxt);
        saveBtn = findViewById(R.id.saveBtn);
        sortBtn = findViewById(R.id.sortBtn);

        contentTxt.setText(FileWorker.readStringFromSecondSD(Constant.PATH,
                Constant.FILENAME));

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveBtn:
                String text = contentTxt.getText().toString();
                int result = FileWorker.writeToSecondSD(Constant.PATH,
                        Constant.FILENAME, text);
                NotificationWorker.notifyResult(this,"save", result);
                break;
            case R.id.sortBtn:
                text = contentTxt.getText().toString();
                text = StringWorker.stringSortByLine(text, false);
                contentTxt.setText(text);
                break;
        }
    }
}
