package com.example.chaofanz.mycalendar.app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chaofanz.mycalendar.R;
import com.example.chaofanz.mycalendar.bean.Event;
import com.example.chaofanz.mycalendar.bean.Status;
import com.example.chaofanz.mycalendar.utils.Constant;
import com.example.chaofanz.mycalendar.utils.DatetimePicker;
import com.example.chaofanz.mycalendar.utils.DbManager;
import com.example.chaofanz.mycalendar.utils.StatusManager;
import com.example.chaofanz.mycalendar.utils.TimeHandler;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Administrator on 2017/11/26.
 */

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnAddUpdate, btnStartDate, btnStartTime, btnEndDate, btnEndTime,btnRepeat;
    EditText edtContent, edtDetail, edtGenre, edtStartDate, edtEndDate, edtStartTime, edtEndTime, edtLevel, edtLocation, edtRepeat;
    Spinner spinnerStatus;
    private ArrayAdapter spinnerAdapter;
    private Event event;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        // aqcuire resources from view
        btnAddUpdate = findViewById(R.id.btnAddUpdate);
        btnStartDate = findViewById(R.id.btnStartDate);
        btnStartTime = findViewById(R.id.btnStartTime);
        btnEndDate = findViewById(R.id.btnEndDate);
        btnEndTime = findViewById(R.id.btnEndTime);
        btnRepeat = findViewById(R.id.btnRepeat);
        edtContent = findViewById(R.id.edtContent);
        edtDetail = findViewById(R.id.edtDetail);
        edtGenre = findViewById(R.id.edtGenre);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtStartTime = findViewById(R.id.edtStartTime);
        edtEndDate = findViewById(R.id.edtEndDate);
        edtEndTime = findViewById(R.id.edtEndTime);
        edtLevel = findViewById(R.id.edtLevel);
        edtLocation = findViewById(R.id.edtLocation);
        edtRepeat = findViewById(R.id.edtRepeat);
        spinnerStatus = findViewById(R.id.spinnerStatus);

        // set the spinner for dropdown status
        List<String> statusList = StatusManager.getStatusList();
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                statusList);
        spinnerStatus.setAdapter(spinnerAdapter);
        spinnerStatus.setOnItemSelectedListener(this);

        // acquire the event id from selected item of mainactivity's listview
        int eventId = getIntent().getExtras().getInt(Constant.EVENT_ITEM_INTENT);
        if (eventId > 0 ) {
//            set content text of AddActivity view
            event = DbManager.getEventById(eventId);
            edtContent.setText(event.getContent());
            edtDetail.setText(event.getDetail());
            edtGenre.setText(event.getGenre());
            edtStartDate.setText(TimeHandler.getDateString(event.getPlan_start()));
            edtStartTime.setText(TimeHandler.getTimeString(event.getPlan_start()));
            edtEndDate.setText(TimeHandler.getDateString(event.getPlan_end()));
            edtEndTime.setText(TimeHandler.getTimeString(event.getPlan_end()));
            edtLevel.setText(String.valueOf(event.getLevel()));
            edtLocation.setText(event.getLocation());
            edtRepeat.setText(String.valueOf(event.getRepeat_type()));
            spinnerStatus.setSelection(event.getStatus());
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (event != null && event.getStatus() != i){
            event.setStatus(i);
            DbManager.updateEvent(event);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartDate:
                DatetimePicker.datePicker(this,edtStartDate);
                break;
            case R.id.btnEndDate:
                DatetimePicker.datePicker(this,edtEndDate);
                break;
            case R.id.btnStartTime:
                DatetimePicker.timePicker(this,edtStartTime);
                break;
            case R.id.btnEndTime:
                DatetimePicker.timePicker(this,edtEndTime);
                break;
            case R.id.btnAddUpdate:
                String start = TimeHandler.combineDateTime(this,edtStartDate.getText().toString(),edtStartTime.getText().toString());
                if ("".equals(start) ){ break; }
                String end = TimeHandler.combineDateTime(this,edtEndDate.getText().toString(),edtEndTime.getText().toString());
                if ("".equals(end)){ break; }

                if (event!=null){
                    long result = DbManager.updateEvent(
                            event.getId(),
                            edtContent.getText().toString(),
                            edtGenre.getText().toString(),
                            start,
                            end,
                            edtDetail.getText().toString(),
                            edtLocation.getText().toString(),
                            null,
                            Integer.parseInt(edtLevel.getText().toString()),
                            (int)spinnerStatus.getSelectedItemId(),
                            Integer.parseInt(edtRepeat.getText().toString())
                            );
                    if (result < 0) {
                        Toast.makeText(this, "update failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "update success", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    long result = DbManager.addEvent(
                            edtContent.getText().toString(),
                            edtGenre.getText().toString(),
                            start,
                            end,
                            edtDetail.getText().toString(),
                            edtLocation.getText().toString(),
                            event.getCompleted().toString(),
                            Integer.parseInt(edtLevel.getText().toString()),
                            (int)spinnerStatus.getSelectedItemId(),
                            Integer.parseInt(edtRepeat.getText().toString())
                    );
                    if (result < 0) {
                        Toast.makeText(this, "add failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "add success", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnDelete:
                if (event != null) {
                    DbManager.deleteEvent(event);
                    Intent intent = new Intent();
                    intent.putExtra("id", event.getId());
                    setResult(2, intent);
                    finish();
                }
                break;
            case R.id.btnRepeat:
                multiSelectDialog(this, Constant.REPEAT_WAYS, edtRepeat);
                break;
        }
    }

    private String multiSelectDialog(Context context, final String[] options, EditText edtText) {
        boolean[] bool = new boolean[options.length];
        return multiSelectDialog(context, options, bool, edtText);
    }

    private String multiSelectDialog(Context context, final String[] options, final boolean[] checked, final EditText edtText) {
        // build an alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String[] output = {""};
        builder.setMultiChoiceItems(options, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                // update the current focused item's checked status
                checked[which] = isChecked;
            }
        });

        // set a title for Alert Dialog
        builder.setTitle("repeat type");

        // set the positive/yes Button Click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                // do something when click positive Button
                output[0] = "";
                for (int i=0;i<=checked.length-1;i++) {
                    if (checked[i]) {
                        output[0] += i+"";
                    }
                }
                edtText.setText(output[0]);
            }
        });

        // set the neutral/cancel button Click Listener
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // do something when click the neutral button
            }
        });

        // Create & show
        AlertDialog dialog = builder.create();
        dialog.show();

        return output[0];
    }
}
