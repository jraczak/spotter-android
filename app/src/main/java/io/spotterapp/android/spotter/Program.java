package io.spotterapp.android.spotter;

import io.realm.RealmObject;

/**
 * Created by Justin on 4/4/16.
 */
public class Program extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
