package com.kelfan.fragmentimooc;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jzheng4 on 6/03/2018.
 */

public class CommuteActivity extends Activity implements CommuteFragment.MyListener{

    private EditText editText;
    private Button send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commute);
        editText = findViewById(R.id.editText);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                CommuteFragment commuteFragment=new CommuteFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", text);
                commuteFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.layout, commuteFragment, "commuteFragment");
                fragmentTransaction.commit();
                Toast.makeText(CommuteActivity.this, "向Fragment发送数据" + text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void thank(String code) {
        Toast.makeText(CommuteActivity.this, "已成功接收到" + code + ",客气了!", Toast.LENGTH_SHORT).show();
    }
}
