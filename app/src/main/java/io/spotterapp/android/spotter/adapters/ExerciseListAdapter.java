package io.spotterapp.android.spotter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.RealmResults;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.models.Exercise;

/**
 * Created by Justin on 3/26/16.
 */
public class ExerciseListAdapter extends BaseAdapter{

    private final String LOG_TAG = ExerciseListAdapter.class.getSimpleName();

    private Context mContext;
    public int numberOfExercises;
    public RealmResults<Exercise> mExercises;
    public LayoutInflater mInflater;

    public ExerciseListAdapter(Context context, int count, RealmResults<Exercise> programs) {
        mContext = context;
        numberOfExercises = count;
        mExercises = programs;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mExercises.size();
    }

    public Object getItem(int position) {
        return mExercises.toArray()[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        TextView exerciseNameTextView;
        TextView exerciseMuscleGroupTextView;
        TextView exerciseTypeTextView;

        Exercise exercise = (Exercise) this.getItem(position);

        Log.d(LOG_TAG, "convertView is " + convertView);
        Log.d(LOG_TAG, "parent is " + parent);

        if (convertView == null) {
            // If the view isn't recycled, initialize it

            Log.d(LOG_TAG, "Exercise name is " + exercise.getName());

            linearLayout = (LinearLayout) mInflater.inflate(R.layout.fragment_exercise_list_item, parent, false);
        }
        else {
            linearLayout = (LinearLayout) convertView;
        }
        Log.d(LOG_TAG, "Setting list item values for " + exercise.getName());

        exerciseNameTextView = (TextView) linearLayout.findViewById(R.id.list_exercises_exercise_name);
        exerciseMuscleGroupTextView = (TextView) linearLayout.findViewById(R.id.list_exercises_exercise_muscle_group);
        exerciseTypeTextView = (TextView) linearLayout.findViewById(R.id.list_exercises_exercise_type);

        exerciseNameTextView.setText(exercise.getName());
        exerciseMuscleGroupTextView.setText(exercise.getMuscleGroup());
        exerciseTypeTextView.setText(exercise.getType());

        Log.d("getView adapter", "Tried to make list item for " + exercise.getName());

        return linearLayout;
    }

}
