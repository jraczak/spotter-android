package io.spotterapp.android.spotter.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import io.realm.RealmResults;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.models.Program;

/**
 * Created by Justin on 3/26/16.
 */
public class ProgramListAdapter extends BaseAdapter{

    private final String LOG_TAG = ProgramListAdapter.class.getSimpleName();

    private Context mContext;
    public int numberOfPrograms;
    public RealmResults<Program> mPrograms;
    public LayoutInflater mInflater;

    public ProgramListAdapter(Context context, int count, RealmResults<Program> programs) {
        mContext = context;
        numberOfPrograms = count;
        mPrograms = programs;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mPrograms.size();
    }

    public Object getItem(int position) {
        return mPrograms.toArray()[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CardView cardView;
        TextView programNameTextView;
        TextView programDescriptionTextView;

        Program program = (Program) this.getItem(position);

        Log.d(LOG_TAG, "convertView is " + convertView);
        Log.d(LOG_TAG, "parent is " + parent);

        if (convertView == null) {
            // If the view isn't recycled, initialize it

            Log.d(LOG_TAG, "Program name is " + program.getName());

            cardView = (CardView) mInflater.inflate(R.layout.fragment_program_list_item, parent, false);
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
        Log.d(LOG_TAG, "Setting card values for " + program.getName());

        //TODO: Set up real values for the program name

        programNameTextView = (TextView) cardView.findViewById(R.id.list_programs_program_name);
        programDescriptionTextView = (TextView) cardView.findViewById(R.id.list_programs_description);

        programNameTextView.setText(program.getName());
        programDescriptionTextView.setText(program.getDescription());

        Log.d("getView adapter", "Tried to make card for " + program.getName());

        return cardView;
    }

}
