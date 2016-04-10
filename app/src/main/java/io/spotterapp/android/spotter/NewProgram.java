package io.spotterapp.android.spotter;

import android.os.Bundle;
import android.app.Activity;

public class NewProgram extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_program);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
