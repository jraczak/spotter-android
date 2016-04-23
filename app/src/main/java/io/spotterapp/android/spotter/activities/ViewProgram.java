package io.spotterapp.android.spotter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.models.Program;

public class ViewProgram extends Activity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_program);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        String programId = getIntent().getStringExtra("program_id");
        Program program = realm.where(Program.class)
                .equalTo("Id", programId)
                .findFirst();

        TextView programNameTextView = (TextView) findViewById(R.id.view_program_program_name);
        programNameTextView.setText(program.getName());

        realm.close();

        // Set click listener for new program day button
        Button buildProgramDayButton = (Button) findViewById(R.id.button_new_program_day);
        final Intent buildProgramDayIntent = new Intent(this, NewProgramDay.class);
        buildProgramDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(buildProgramDayIntent);
            }
        });

    }

}
