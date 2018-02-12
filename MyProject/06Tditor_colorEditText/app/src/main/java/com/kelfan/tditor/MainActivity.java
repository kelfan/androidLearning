package com.kelfan.tditor;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import Util.FileHandler;
import Util.FileWorker;
import Util.StringStyleWorker;
import Util.StringWorker;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText editText;
    private TextView textView;
    private MenuItem sortItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editHandler.saveFile(editText, Constant.DEFAULT_FILE_NAME, view);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // editText
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i1 == 0 && i > 0) {
                    char lastEnter = charSequence.charAt(i);
                    if (lastEnter == '\n') {
                        StringBuilder s = new StringBuilder(charSequence);
                        String sub = s.substring(0, i);
                        int lastN = sub.lastIndexOf("\n");
                        if (lastN < s.length() - 1) {
                            char lastChar = s.charAt(lastN + 1);
                            if (lastChar != '\n') {
                                s.insert(i + 1, lastChar);
                                s.insert(i + 2, " ");
                                CharSequence displayStr = editHandler.todoHandle(s.toString());
                                editText.setText(displayStr);
                                editText.setSelection(i + 3);
                            }
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // change line number in TextView when text in editText Change
                editHandler.addLineNumber(editText, textView, 0);
            }
        });
        String fileStr = FileHandler.read_app_file(Constant.DEFAULT_FILE_NAME);
        CharSequence displayStr = editHandler.todoHandle(fileStr);
        editText.setText(displayStr);
        // fix the problem for line numbers in start up
        editText.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                editHandler.addLineNumber(editText, textView, 0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sort(MenuItem item) {
        String text = editText.getText().toString();
        text = StringWorker.stringSortByLine(text, false);
        CharSequence ts = editHandler.todoHandle(text);
        int cursorPosition = editText.getSelectionStart();
        editText.setText(ts);
        editText.setSelection(cursorPosition);
    }
}
