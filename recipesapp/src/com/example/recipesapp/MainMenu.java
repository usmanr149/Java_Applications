package com.example.recipesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View aboutButton = findViewById(R.id.main_about_button);
        aboutButton.setOnClickListener(this);
        Button newRecipe = (Button)this.findViewById(R.id.main_menu_new);
        newRecipe.setOnClickListener(this);
    }

    public void onClick(View thisView) {
        switch (thisView.getId()){
            case R.id.main_about_button:
                Intent showAbout = new Intent(this, About.class);
                startActivity(showAbout);
                break;
            case R.id.new_button:
                Intent doMenuClick = new Intent(this, RecipeEntry.class);
                startActivity(doMenuClick);
                break;
        }
    }
    //This code overrides the defaut menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu, menu);
        menu.findItem(R.id.main_menu_new).setIntent(new Intent(this, RecipeEntry.class));
        //menu.findItem(R.id.main_menu_search).setIntent(new Intent(this, SearchRecipe.class));
        menu.findItem(R.id.main_menu_options).setIntent(new Intent(this, Options.class));
        //menu.findItem(R.id.main_menu_new).setIntent(new Intent(this, RecipeEntry.class));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem itm){
        super.onOptionsItemSelected(itm);
       // startActivity(itm.getIntent());
        Intent menuIntent = itm.getIntent();
        if (menuIntent != null)    startActivity(menuIntent);
        return true;
    }

}
