package com.example.android.sunshine;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.support.v7.widget.ShareActionProvider;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.io.File;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private ShareActionProvider mShareActionProvider;
    private String value;
    private Intent shareIntent;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle extras = getActivity().getIntent().getExtras();
        value = null;
        if (extras != null) {
            value = extras.getString(Intent.EXTRA_TEXT);
            Log.v("DetailActivityFragment", value);
            //The key argument here must match that used in the other activity

        }
        TextView weatherTextView = (TextView) rootView.findViewById(R.id.weather_textView);
        weatherTextView.setText(value);

        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //dont inflate the menu item again here, already inflated in Activity
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, value);
        setShareIntent(shareIntent);//item. is deprecated, the new shizz is MenuItemCompat
    }

    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) mShareActionProvider.setShareIntent(shareIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return false;  // return false if the implemenation is the Activity and vice-versa

        if (id == R.id.action_show_pref_loc) return false;

        if (id == R.id.menu_item_share) {
//            Intent shareIntent = new Intent(Intent.ACTION_SEND);
//            shareIntent.setType("text/plain");
//            shareIntent.putExtra(Intent.EXTRA_TEXT, value);
//            setShareIntent(shareIntent);
            startActivity(Intent.createChooser(shareIntent, "Share using"));
        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
