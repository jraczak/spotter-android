package io.spotterapp.android.spotter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.UUID;

import io.realm.Realm;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.models.ProgramDay;

public class NewProgramDay extends Activity {

    private final String LOG_TAG = NewProgramDay.class.getSimpleName();

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_program_day);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        //TODO: Set the create program listener here
    }

    public void createProgramDay(View view) {

        EditText name = (EditText) findViewById(R.id.program_day_name_text_field);

        realm.beginTransaction();
        ProgramDay programDay = realm.createObject(ProgramDay.class);

        programDay.setId(UUID.randomUUID().toString());
        programDay.setName(name.getText().toString());

        Log.d(LOG_TAG, "The program day object is: " + programDay);

        realm.commitTransaction();
        realm.close();

        Intent intent = new Intent(this, ViewProgram.class);

                //TODO: How do I get reference to the parent program here?

    }

}
