package io.spotterapp.android.spotter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

import io.realm.Realm;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.models.Program;
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
        Button createProgramDayButton = (Button) findViewById(R.id.button_create_program_day);
        createProgramDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProgramDay(v);
            }
        });
    }

    public void createProgramDay(View view) {

        EditText name = (EditText) findViewById(R.id.program_day_name_text_field);
        String programId = getIntent().getStringExtra("program_id");
        Program program = realm.where(Program.class)
                .equalTo("id", programId)
                .findFirst();
        realm.beginTransaction();
        ProgramDay programDay = realm.createObject(ProgramDay.class);

        programDay.setId(UUID.randomUUID().toString());
        Log.d(LOG_TAG, "Setting program day name to " + name.getText());
        programDay.setName(name.getText().toString());
        Log.d(LOG_TAG, "Setting program to " + program.getName());
        programDay.setProgram(program);

        Log.d(LOG_TAG, "The program day object is: " + programDay);

        Log.d(LOG_TAG, "Adding the program day to the program");
        program.getProgramDays().add(programDay);

        realm.commitTransaction();
        realm.close();

        Intent intent = new Intent(this, ViewProgram.class);
        intent.putExtra("program_id", programId);
        startActivity(intent);

    }

}
