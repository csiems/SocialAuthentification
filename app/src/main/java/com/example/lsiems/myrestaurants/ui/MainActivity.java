package com.example.lsiems.myrestaurants.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lsiems.myrestaurants.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    @Bind(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //causes android to run default actions for activity
        setContentView(R.layout.activity_main); //tells android which layout to use (R is resources)
        ButterKnife.bind(this);

        mSharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mFindRestaurantsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mFindRestaurantsButton) {
            // Pattern matcher regex to confirm zip code entry
            String location = mLocationEditText.getText().toString();
            String zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$";
            Pattern pattern = Pattern.compile(zipRegex);
            Matcher matcher = pattern.matcher(location);
            if(!(location).equals("")) {
                addToSharedPreferences(location);
            }
            if ( !matcher.matches() && mSharedPreferences == null) {
                mLocationEditText.setError( "A five-digit zip code is required!" );
            } else {
                Intent intent = new Intent(MainActivity.this, RestaurantsListActivity.class);
                startActivity(intent);
            }
        }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString("location", location)
                .commit();
    }

}
