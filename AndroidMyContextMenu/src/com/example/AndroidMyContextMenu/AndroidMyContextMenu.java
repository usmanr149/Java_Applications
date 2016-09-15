package com.example.AndroidMyContextMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidMyContextMenu extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView textStartContextMenu = (TextView) findViewById(R.id.startcontextmenu);
        registerForContextMenu(textStartContextMenu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0, "- A -");
        menu.add(0,1,0, "- B -");
        menu.add(0,2,0, "- C -");
        menu.add(0,3,0, "- D -");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        Toast.makeText(AndroidMyContextMenu.this,
                String.valueOf(item.getItemId()),
                Toast.LENGTH_LONG).show();

        return super.onContextItemSelected(item);
    }
}