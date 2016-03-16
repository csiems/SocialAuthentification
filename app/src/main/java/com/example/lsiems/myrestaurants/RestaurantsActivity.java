package com.example.lsiems.myrestaurants;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantsActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    private String[] restaurants = new String[] { "Fire On The Mountain", "Burrito Azteca", "Po'Shines", "Posies'", "The Breakside", "Tin Shed Garden Cafe", "Bunk", "La Sirenita", "Fat Head's Brewery", "Eastside Deli", "Pizzicato", "Potbelly Sandwiches", "Flavour Spot" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, restaurants);
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);
    }
}
