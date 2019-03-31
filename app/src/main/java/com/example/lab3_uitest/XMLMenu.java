package com.example.lab3_uitest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class XMLMenu extends AppCompatActivity {

    private TextView menutv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlmenu);
        menutv = (TextView)findViewById(R.id.menutv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.font_10:
                menutv.setTextSize(10*2);
                break;
            case R.id.font_16:
                menutv.setTextSize(32);
                break;
            case R.id.font_20:
                menutv.setTextSize(40);
                break;
            case R.id.normal_menu:
                Toast tost = Toast.makeText(XMLMenu.this, "这是普通单击项", Toast.LENGTH_SHORT);
                tost.show();
                break;
            case R.id.font_red:
                menutv.setTextColor(Color.RED);
                break;
            case R.id.font_black:
                menutv.setTextColor(Color.BLACK);
                break;
        }
        return true;
    }
}
