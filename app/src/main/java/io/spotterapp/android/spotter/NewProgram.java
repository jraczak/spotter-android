package io.spotterapp.android.spotter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import io.realm.Realm;

public class NewProgram extends Activity {

    final private String LOG_TAG = NewProgram.class.getSimpleName();

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_program);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();
    }

    public void createProgram(View view) {

        EditText name = (EditText) findViewById(R.id.program_name_text_field);
        EditText description = (EditText) findViewById(R.id.program_description_text_field);

            realm.beginTransaction();
            Program program = realm.createObject(Program.class);

            program.setId(UUID.randomUUID().toString());
            program.setName(name.getText().toString());
            program.setDescription(description.getText().toString());

            Log.d(LOG_TAG, "The program state is: " + program);

            realm.commitTransaction();
            realm.close();

            Intent intent = new Intent(this, ViewProgram.class);
            intent.putExtra("program_name", program.getName());
            startActivity(intent);

            Toast.makeText(this, program.getName() + " was added to your program library", Toast.LENGTH_LONG).show();
    }

}
