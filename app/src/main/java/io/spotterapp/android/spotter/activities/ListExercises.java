package io.spotterapp.android.spotter.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;
import io.spotterapp.android.spotter.R;
import io.spotterapp.android.spotter.adapters.ExerciseListAdapter;
import io.spotterapp.android.spotter.models.Exercise;

public class ListExercises extends Activity {
    //TODO: Fix package name in hooks.rb to accommodate new nested packages

    private final String LOG_TAG = ListExercises.class.getSimpleName();

    private Realm realm;

    private ExerciseListAdapter mExerciseListAdapter;
    private RealmResults<Exercise> mExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        mExercises = realm.allObjects(Exercise.class);

        Log.d(LOG_TAG, mExercises.size() + "exercises were found.");

        Log.d(LOG_TAG, "Initializing the exercises list adapter");
        mExerciseListAdapter = new ExerciseListAdapter(this, mExercises.size(), mExercises);

        Log.d(LOG_TAG, "Get the list view");
        ListView listView = (ListView) findViewById(R.id.list_exercises_listview);

        Log.d(LOG_TAG, "Setting the adapater on the list view");
        listView.setAdapter(mExerciseListAdapter);

        // Set the list view to be the height of all the children after inflation
        // to prevent scrolling
        setListViewHeightBasedOnItems(listView);

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
