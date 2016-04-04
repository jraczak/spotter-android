package io.spotterapp.android.spotter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Justin on 3/26/16.
 */
public class WorkoutListAdapter extends BaseAdapter{

    private final String LOG_TAG = WorkoutListAdapter.class.getSimpleName();

    private Context mContext;
    public int numberOfWorkouts;
    public ArrayList<Workout> mWorkoutsList;
    public LayoutInflater mInflater;

    public WorkoutListAdapter(Context context, int count, ArrayList<Workout> workouts) {
        mContext = context;
        numberOfWorkouts = count;
        mWorkoutsList = workouts;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mWorkoutsList.size();
    }

    public Object getItem(int position) {
        return mWorkoutsList.toArray()[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CardView cardView;
        TextView workoutNameTextView;
        TextView workoutDateTextView;
        TextView workoutStartTimeTextView;
        TextView workoutProgramName;

        Workout workout = (Workout) this.getItem(position);

        Log.d(LOG_TAG, "convertView is " + convertView);
        Log.d(LOG_TAG, "parent is " + parent);

        if (convertView == null) {
            // If the view isn't recycled, initialize it

            Log.d(LOG_TAG, "Workout name is " + workout.getName());

            cardView = (CardView) mInflater.inflate(R.layout.fragment_workout_list_item, parent, false);
            //workoutNameTextView = (TextView) cardView.findViewById(R.id.list_workouts_workout_name);
            //workoutDateTextView = (TextView) cardView.findViewById(R.id.list_workouts_workout_date);
            //workoutStartTimeTextview = (TextView) cardView.findViewById(R.id.list_workouts_start_time);

            //GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
            //        GridLayout.,
            //        GridLayout.LayoutParams.WRAP_CONTENT
            //);
//
            //layoutParams.setMargins(16, 8, 16, 8);
            //cardView.setLayoutParams(layoutParams);


        }
        else {
            cardView = (CardView) convertView;
        }
        Log.d(LOG_TAG, "Setting card values for " + workout.getName());

        //TODO: Set up real values for the program name

        workoutNameTextView = (TextView) cardView.findViewById(R.id.list_workouts_workout_name);
        workoutDateTextView = (TextView) cardView.findViewById(R.id.list_workouts_workout_date);
        workoutStartTimeTextView = (TextView) cardView.findViewById(R.id.list_workouts_start_time);
        workoutProgramName = (TextView) cardView.findViewById(R.id.list_workouts_program_name);


        workoutProgramName.setText("Size Gains");
        workoutNameTextView.setText(workout.getName());
        workoutDateTextView.setText(workout.getDate());
        workoutStartTimeTextView.setText("Started at " + workout.getStartTime());

        Log.d("getView adapter", "Tried to make card for " + workout.getName());



        return cardView;
    }

}
