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
        TextView workoutStartTimeTextview;

        Log.d(LOG_TAG, "convertView is " + convertView);
        Log.d(LOG_TAG, "parent is " + parent);

        if (convertView == null) {
            // If the view isn't recycled, initialize it
            cardView = (CardView) mInflater.inflate(R.layout.fragment_workout_list_item, parent, false);
            workoutNameTextView = (TextView) cardView.findViewById(R.id.list_workouts_workout_name);
            workoutDateTextView = (TextView) cardView.findViewById(R.id.list_workouts_workout_date);
            workoutStartTimeTextview = (TextView) cardView.findViewById(R.id.list_workouts_start_time);

            CardView.LayoutParams layoutParams = new CardView.LayoutParams(
                    CardView.LayoutParams.MATCH_PARENT,
                    CardView.LayoutParams.WRAP_CONTENT
            );

            layoutParams.setMargins(16, 8, 16, 8);
            cardView.setLayoutParams(layoutParams);

            Log.d("getView adapter", "Setting text view values if convertView was null - new card view");
            workoutNameTextView.setText(this.mWorkoutsList.get(position).name);
            workoutDateTextView.setText(this.mWorkoutsList.get(position).date);
            workoutStartTimeTextview.setText(this.mWorkoutsList.get(position).startTime);
        }
        else {
            cardView = (CardView) convertView;
        }
        Log.d("getView adapter", "Tried to make cards");



        return cardView;
    }

}
