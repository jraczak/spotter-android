package io.spotterapp.android.spotter.activities;

import android.os.Bundle;
import android.app.Activity;

import io.spotterapp.android.spotter.R;

public class ListExercises extends Activity {


    //TODO: Fix package name in hooks.rb to accommodate new nested packages
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
