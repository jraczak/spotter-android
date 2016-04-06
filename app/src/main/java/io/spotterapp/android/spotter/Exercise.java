package io.spotterapp.android.spotter;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Justin on 4/2/16.
 */
public class Exercise extends RealmObject {

    @PrimaryKey
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
