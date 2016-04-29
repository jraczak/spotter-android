package io.spotterapp.android.spotter.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import io.realm.RealmList;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.models.ProgramDay;

/**
 * Created by Justin on 3/26/16.
 */
public class ProgramDayListAdapter extends BaseAdapter{

    private final String LOG_TAG = ProgramDayListAdapter.class.getSimpleName();

    private Context mContext;
    public int numberOfProgramDays;
    public RealmList<ProgramDay> mProgramDays;
    public LayoutInflater mInflater;

    public ProgramDayListAdapter(Context context, int count, RealmList<ProgramDay> programDays) {
        mContext = context;
        numberOfProgramDays = count;
        mProgramDays = programDays;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mProgramDays.size();
    }

    public Object getItem(int position) {
        return mProgramDays.toArray()[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CardView cardView;
        TextView programDayNameTextView;
        TextView programDayDetailTextView;

        ProgramDay programDay = (ProgramDay) this.getItem(position);

        Log.d(LOG_TAG, "convertView is " + convertView);
        Log.d(LOG_TAG, "parent is " + parent);

        if (convertView == null) {
            // If the view isn't recycled, initialize it

            Log.d(LOG_TAG, "Program day name is " + programDay.getName());

            cardView = (CardView) mInflater.inflate(R.layout.fragment_program_day_list_item, parent, false);


        }
        else {
            cardView = (CardView) convertView;
        }
        Log.d(LOG_TAG, "Setting card values for " + programDay.getName());

        programDayNameTextView = (TextView) cardView.findViewById(R.id.list_programs_program_name);
        programDayDetailTextView = (TextView) cardView.findViewById(R.id.list_programs_description);

        programDayNameTextView.setText(programDay.getName());
        programDayDetailTextView.setText(programDay.getDetailString());

        Log.d("getView adapter", "Tried to make card for " + programDay.getName());

        return cardView;
    }

}
