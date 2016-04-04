package io.spotterapp.android.spotter;

import io.realm.RealmObject;

/**
 * Created by Justin on 4/2/16.
 */
public class Exercise extends RealmObject {

    private String name;
    private String type;
    private String muscleGroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
