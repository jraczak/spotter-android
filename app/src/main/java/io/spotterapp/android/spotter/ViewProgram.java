package io.spotterapp.android.spotter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import io.realm.Realm;

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

    }

}
