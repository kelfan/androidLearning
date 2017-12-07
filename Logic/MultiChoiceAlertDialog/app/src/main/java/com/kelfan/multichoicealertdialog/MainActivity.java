package com.kelfan.multichoicealertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl = findViewById(R.id.rl);
        Button btn = findViewById(R.id.btn);
        final TextView tv = findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // build an alertdialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // String Array for Alert Dialog multi choice items
                String[] colors = new String[]{
                        "red", "green", "blue", "purple", "olive"
                };

                // Boolean Array for initial selected items
                final boolean[] checkedColors = new boolean[]{
                        false, true, false, true, false
                        // "red", "green", "blue", "purple", "olive"
                };

                // Convert the color array to list
                final List<String> colorsList = Arrays.asList(colors);

                // Set multiple choice items for alert dialog
                /*
                    AlertDialog.Builder setMultiChoiceItems(CharSequence[] items, boolean[]
                    checkedItems, DialogInterface.OnMultiChoiceClickListener listener)
                        Set a list of items to be displayed in the dialog as the content,
                        you will be notified of the selected item via the supplied listener.
                 */
                /*
                    DialogInterface.OnMultiChoiceClickListener
                    public abstract void onClick (DialogInterface dialog, int which, boolean isChecked)

                        This method will be invoked when an item in the dialog is clicked.

                        Parameters
                        dialog The dialog where the selection was made.
                        which The position of the item in the list that was clicked.
                        isChecked True if the click checked the item, else false.
                 */
                builder.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        // update the current focused item's checked status
                        checkedColors[which] = isChecked;

                        // get the current focused item
                        String currentItem = colorsList.get(which);

                        // notify the current action
                        Toast.makeText(getApplicationContext(),
                                currentItem + " " + isChecked,
                                Toast.LENGTH_SHORT).show();
                    }
                });

                // specify the Dialog is not cancelable
                builder.setCancelable(false);

                // set a title for Alert Dialog
                builder.setTitle("your perferred colors?");

                // set the positive/yes Button Click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // do something when click positive Button
                        tv.setText("your preferred colors .... \n");
                        for (int i = 0; i < checkedColors.length; i++) {
                            boolean checked = checkedColors[i];
                            if (checked) {
                                tv.setText(tv.getText() + colorsList.get(i) + "\n");
                            }
                        }
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

            }
        });
    }
}
