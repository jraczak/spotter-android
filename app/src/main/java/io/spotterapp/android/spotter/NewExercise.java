package io.spotterapp.android.spotter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class NewExercise extends Activity {

    final private String LOG_TAG = NewExercise.class.getSimpleName();

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        realm = Realm.getDefaultInstance();

        Spinner muscleGroupSpinner = (Spinner) findViewById(R.id.exercise_muscle_group_spinner);
        Spinner exerciseTypeSpinner = (Spinner) findViewById(R.id.exercise_type_spinner);

        ArrayAdapter<CharSequence> muscleGroupAdapter = ArrayAdapter.createFromResource(this, R.array.muscle_group_values, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> exerciseTypeAdapter = ArrayAdapter.createFromResource(this, R.array.exercise_type_values, android.R.layout.simple_spinner_item);

        muscleGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        muscleGroupSpinner.setAdapter(muscleGroupAdapter);
        exerciseTypeSpinner.setAdapter(exerciseTypeAdapter);

    }

    public void createExercise(View view) {

        EditText name = (EditText) findViewById(R.id.exercise_name_text_field);
        Spinner type = (Spinner) findViewById(R.id.exercise_type_spinner);
        Spinner muscleGroup = (Spinner) findViewById(R.id.exercise_muscle_group_spinner);

        // Check if an exercise with this name already exists in the same muscle group
        if (isNewExercise(name.getText().toString(), muscleGroup.getSelectedItem().toString())) {

            realm.beginTransaction();
            Exercise exercise = realm.createObject(Exercise.class);

            exercise.setId(UUID.randomUUID().toString());
            exercise.setName(name.getText().toString());
            exercise.setType(type.getSelectedItem().toString());
            exercise.setMuscleGroup(muscleGroup.getSelectedItem().toString());

            Log.d(LOG_TAG, "The exercise state is: " + exercise);

            realm.commitTransaction();
            realm.close();

            Intent intent = new Intent(this, ListWorkouts.class);
            intent.putExtra("exercise_name", exercise.getName());
            startActivity(intent);

            Toast.makeText(this, exercise.getName() + " was added to your library", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "This exercise already exists within this muscle group", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isNewExercise(String exerciseName, String muscleGroup) {

        // Search for an existing exercise by name and muscle group
        RealmResults<Exercise> exercises = realm.where(Exercise.class)
                .equalTo("name", exerciseName)
                .equalTo("muscleGroup", muscleGroup)
                .findAll();

        // Return a bool based on the presence of results from the query
        return(exercises.size() == 0);
    }

    public void showDuplicateExerciseError(String exerciseName) {
        //TODO: Decide if an error message in UI would be better than a Toast
    }

}
