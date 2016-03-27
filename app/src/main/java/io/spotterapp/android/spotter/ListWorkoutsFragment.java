package io.spotterapp.android.spotter;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListWorkoutsFragment extends Fragment {
    public final String LOG_TAG = ListWorkoutsFragment.class.getSimpleName();

    private WorkoutListAdapter mWorkoutListAdapter;
    private ArrayList<Workout> mWorkoutsList;

    public ListWorkoutsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mWorkoutsList = new ArrayList<>();
//
//        Log.d(LOG_TAG, "Creating workout objects with stock data");
//        Workout workout1 = new Workout(
//                "Heavy Chest",
//                Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH),
//                Calendar.getInstance().get(Calendar.HOUR_OF_DAY) +
//                        Calendar.getInstance().get(Calendar.MINUTE)
//        );
//
//        Workout workout2 = new Workout(
//                "Heavy Back",
//                Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH),
//                Calendar.getInstance().get(Calendar.HOUR_OF_DAY) +
//                        Calendar.getInstance().get(Calendar.MINUTE)
//        );
//
//        Workout workout3 = new Workout(
//                "Heavy Shoulders",
//                Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH),
//                Calendar.getInstance().get(Calendar.HOUR_OF_DAY) +
//                        Calendar.getInstance().get(Calendar.MINUTE)
//        );

//        mWorkoutsList.add(workout1);
//        mWorkoutsList.add(workout2);
//        mWorkoutsList.add(workout3);

        Log.d("onCreate", "Array list size is " + mWorkoutsList.size() + " after creating workouts.");

       Log.d(LOG_TAG, "Instantiating the listview, passing in the array of workouts");
        mWorkoutListAdapter = new WorkoutListAdapter(getActivity(), mWorkoutsList.size(), mWorkoutsList);

        // Get the ListView to display the workouts
        Log.d(LOG_TAG, "Getting the listview");
        ListView listView = (ListView) getActivity().findViewById(R.id.list_workouts_listview);

        // Attach the WorkoutListAdapter
        Log.d(LOG_TAG, "Attaching the adapter to the listview");
        listView.setAdapter(mWorkoutListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_workouts, container, false);
    }

    public class PrepareWorkoutsTask {


    }

}
