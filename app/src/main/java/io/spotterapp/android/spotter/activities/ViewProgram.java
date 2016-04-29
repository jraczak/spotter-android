package io.spotterapp.android.spotter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmList;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.adapters.ProgramDayListAdapter;
import io.spotterapp.android.spotter.models.Program;
import io.spotterapp.android.spotter.models.ProgramDay;

public class ViewProgram extends Activity {

    private final String LOG_TAG = ViewProgram.class.getSimpleName();

    Realm realm;

    private ProgramDayListAdapter mProgramDayListAdapter;
    private RealmList<ProgramDay> mProgramDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_program);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        String programId = getIntent().getStringExtra("program_id");
        Program program = realm.where(Program.class)
                .equalTo("id", programId)
                .findFirst();

        TextView programNameTextView = (TextView) findViewById(R.id.view_program_program_name);
        programNameTextView.setText(program.getName());

        int programDayCount = program.countProgramDays();

        // Set click listener for new program day button
        Button buildProgramDayButton = (Button) findViewById(R.id.button_new_program_day);
        final Intent buildProgramDayIntent = new Intent(this, NewProgramDay.class);
        buildProgramDayIntent.putExtra("program_id", program.getId());
        buildProgramDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(buildProgramDayIntent);
            }
        });

        //TODO: List out each of the program days
        // Collect all the program days associated with the program
        RealmList<ProgramDay> programDays = program.getProgramDays();

        Log.d(LOG_TAG, "This program has " + programDays.size() + " program days.");

        //TODO: Address when there are no program days and therefor .size causes null exception
        mProgramDayListAdapter = new ProgramDayListAdapter(this, mProgramDays.size(), mProgramDays);

        ListView listView = (ListView) findViewById(R.id.list_program_days_listview);
        listView.setAdapter(mProgramDayListAdapter);
        setListViewHeightBasedOnItems(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgramDay programDay = mProgramDays.get(position);
                Toast.makeText(getApplicationContext(), programDay.getName() + " was clicked.", Toast.LENGTH_LONG).show();
            }
        });




        realm.close();

    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter != null) {
            int numberOfItems = listAdapter.getCount();
            int totalItemsHeight = 0;

            for (int itemPosition = 0; itemPosition < numberOfItems; itemPosition++) {
                View item = listAdapter.getView(itemPosition, null, listView);
                item.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                totalItemsHeight += item.getMeasuredHeight();
            }

            int totalDividersHeight = listView.getDividerHeight() * (numberOfItems -1 );

            ViewGroup.LayoutParams params =
                    listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;
        }
        else {
            return false;
        }
    }

}
