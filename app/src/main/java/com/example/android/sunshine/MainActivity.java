package com.example.android.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);

        }
        if(id == R.id.action_show_pref_loc){
            String pref_loc_value;
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
// then you use
            pref_loc_value = prefs.getString("location","560037");
            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+pref_loc_value);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            if(mapIntent.resolveActivity(getPackageManager())!= null)
            {
                startActivity(mapIntent);
            }
             // mapIntent.setPackage("com.google.android.apps.maps");
            else {
                Log.d("MainActivityFragment","Could not find the appropriate intent");
            }


        }


        return super.onOptionsItemSelected(item);
    }
}
