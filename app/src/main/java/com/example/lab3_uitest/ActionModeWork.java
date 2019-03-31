package com.example.lab3_uitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionModeWork extends AppCompatActivity {

    private ListView listView;
    private List<Map<String, Object>> listItems;
    private SimpleAdapter adapter;
    private int item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode_work);
        init();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.contex_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_delete:
                        listItems.remove(item_id);
                        adapter.notifyDataSetChanged();
                        actionMode.finish();
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listView2);
        listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 6; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("number", i);
            listItem.put("images", R.mipmap.ic_launcher);
            listItems.add(listItem);
        }
        adapter = new SimpleAdapter(this, listItems, R.layout.item2,
                new String[]{"number", "images"}, new int[]{R.id.number, R.id.img});
        listView.setAdapter(adapter);
        //registerForContextMenu(listView);
    }



//    @Override
//    public  void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
//        item_id = info.position;
//        MenuInflater inflater = new MenuInflater(this);
//        inflater.inflate(R.menu.contex_menu, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_delete:
//                listItems.remove(item_id);
//                adapter.notifyDataSetChanged();
//                break;
//        }
//        return true;
//    }

}

