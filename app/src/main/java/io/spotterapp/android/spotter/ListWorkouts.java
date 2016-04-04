package io.spotterapp.android.spotter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListWorkouts extends Activity {

    public final String LOG_TAG = ListWorkouts.class.getSimpleName();

    private WorkoutListAdapter mWorkoutListAdapter;
    private ArrayList<Workout> mWorkoutsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts);

        mWorkoutsList = new ArrayList<>();

        Date now = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("K:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM F, y");

        Workout workout1 = new Workout(
                "Heavy Chest",
                dateFormat.format(now),
                timeFormat.format(now)
        );

        Workout workout2 = new Workout(
                "Heavy Back",
                dateFormat.format(now),
                timeFormat.format(now)
        );

        Workout workout3 = new Workout(
                "Heavy Shoulders",
                dateFormat.format(now),
                timeFormat.format(now)
        );

        Workout workout4 = new Workout(
                "Heavy Biceps",
                dateFormat.format(now),
                timeFormat.format(now)
        );

        mWorkoutsList.add(workout1);
        mWorkoutsList.add(workout2);
        mWorkoutsList.add(workout3);
        mWorkoutsList.add(workout4);

        Log.d("onCreate", "Array list size is " + mWorkoutsList.size());

        Log.d(LOG_TAG, "Instantiating the listview, passing in the array of workouts");
        mWorkoutListAdapter = new WorkoutListAdapter(this, mWorkoutsList.size(), mWorkoutsList);

        // Get the ListView to display the workouts
        Log.d(LOG_TAG, "Getting the listview");
        ListView listView = (ListView) findViewById(R.id.list_workouts_listview);

        // Attach the WorkoutListAdapter
        Log.d(LOG_TAG, "Attaching the adapter to the listview");
        listView.setAdapter(mWorkoutListAdapter);

        setListViewHeightBasedOnItems(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "You tapped " + mWorkoutsList.get(position).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_workouts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
