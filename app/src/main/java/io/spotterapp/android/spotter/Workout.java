package io.spotterapp.android.spotter;



/**
 * Created by Justin on 3/26/16.
 */
public class Workout {

    private String name;
    private String date;
    private String startTime;


    public Workout(String name, String date, String startTime) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
