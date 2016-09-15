package com.example.recipesapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by usman on 03/10/15.
 */
public class Options extends PreferenceActivity {

        @Override
        protected void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        }

        public static class MyPreferenceFragment extends PreferenceFragment
        {
            @Override
            public void onCreate(final Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.options);
            }
        }
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        if (Build.VERSION.SDK_INT >= 11)
            addPreferencesFromResource(R.xml.options);
    }
    //Instead of extending the standard Activity class, extends PreferenceActivity
}
*/