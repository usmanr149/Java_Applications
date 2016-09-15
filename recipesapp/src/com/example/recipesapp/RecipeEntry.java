package com.example.recipesapp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by usman on 26/10/15.
 */
public class RecipeEntry extends Activity {
    /** Called when the activity is first created **/
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.recipe_tab_new);
    }
}
