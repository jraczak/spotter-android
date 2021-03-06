package io.spotterapp.android.spotter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.spotterapp.android.spotter.models.Exercise;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.models.Workout;
import io.spotterapp.android.spotter.adapters.WorkoutListAdapter;

public class ListWorkouts extends Activity {

    public final String LOG_TAG = ListWorkouts.class.getSimpleName();

    private Realm realm;

    private WorkoutListAdapter mWorkoutListAdapter;
    private ArrayList<Workout> mWorkoutsList;

    // Nav drawer variables
    private String[] mNavDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts);

        // Set up the nav drawer
        mNavDrawerItems = getResources().getStringArray(R.array.nav_drawer_labels);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer_drawer);
        mDrawerListView = (ListView) findViewById(R.id.nav_drawer_listview);


        // Set the adapter on the ListView
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.nav_drawer_list_item, mNavDrawerItems));

        // Set up the click listener
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, "Item value is " + mNavDrawerItems[position]);

                if (position == 1 ) {
                    //(mNavDrawerItems[position] == "Add New Exercise")
                    Intent intent = new Intent(getApplicationContext(), NewExercise.class);
                    startActivity(intent);
                } else //if //(mNavDrawerItems[position] == "Workout History") {
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), ListWorkouts.class);
                    startActivity(intent);
                } else
                    if (position == 2) {
                        Intent intent = new Intent(getApplicationContext(), NewProgram.class);
                        startActivity(intent);
                    }
                else if (position == 3) {
                        Intent intent = new Intent(getApplicationContext(), ListPrograms.class);
                        startActivity(intent);
                    }
                else if (position == 4) {
                        Intent intent = new Intent(getApplicationContext(), ListExercises.class);
                        startActivity(intent);
                    }
            }
        });


        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(getTitle());
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(getTitle());
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);


        //TODO: Figure out where this is actually supposed to go.
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("spotter.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();

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


        RealmResults<Exercise> results = realm.allObjects(Exercise.class);

        TextView exerciseList = (TextView) findViewById(R.id.exercise_list);

        String exerciseString = "";

        for(Exercise r: results) {
            exerciseString +=
                    r.getName() + " - " + r.getMuscleGroup() + "\n";
        }

        exerciseList.setText(exerciseString);

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

        Button newWorkoutButton = (Button) findViewById(R.id.button_new_workout);
        newWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewExercise.class);
                startActivity(intent);
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

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
