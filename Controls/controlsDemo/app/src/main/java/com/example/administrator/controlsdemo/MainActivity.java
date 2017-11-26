package com.example.administrator.controlsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = findViewById(R.id.TextView1);
        ToggleButton tg = findViewById(R.id.togglebutton);

        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                tv.setText(isChecked?"On":"Off");
//                tv.setBackgroundResource(isChecked?R.drawable.on:R.drawable.off);
            }
        });

        final CheckBox cb = findViewById(R.id.checkbox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String text = cb.getText().toString();
                Log.i("tag", b?"checked":"unchecked");
            }
        });

        RadioGroup rg = findViewById(R.id.rgid);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        Log.i("tag","one");
                        break;
                    case R.id.rb2:
                        Log.i("tag","two");
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
