package io.spotterapp.android.spotter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;
import io.spotterapp.android.spotter.models.Program;
import io.spotterapp.android.spotter.adapters.ProgramListAdapter;
import io.spotterapp.android.spotter.R;

public class ListPrograms extends Activity {

    private final String LOG_TAG = ListPrograms.class.getSimpleName();

    private Realm realm;

    private ProgramListAdapter mProgramListAdapater;
    private RealmResults<Program> mPrograms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_programs);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        mPrograms = realm.allObjects(Program.class);
        //final Program[] programArray = (Program[]) mExercises.toArray();

        Log.d(LOG_TAG, mPrograms.size() + " programs were found.");

        Log.d(LOG_TAG, "Initializing the programs list adapter");
        mProgramListAdapater = new ProgramListAdapter(this, mPrograms.size(), mPrograms);

        Log.d(LOG_TAG, "Getting the list view");
        ListView listView = (ListView) findViewById(R.id.list_my_programs_listview);

        Log.d(LOG_TAG, "Setting the adapter on the list view");
        listView.setAdapter(mProgramListAdapater);

        // Ensure the list view is the height of all its children
        setListViewHeightBasedOnItems(listView);

        // Set up the click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Program program = mPrograms.get(position);
                Log.d(LOG_TAG, "Program " + program.getName() + " was tapped.");
                Intent intent = new Intent(getApplicationContext(), ViewProgram.class);
                intent.putExtra("program_id", program.getId());
                startActivity(intent);
            }
        });

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
