package io.spotterapp.android.spotter.models;

import io.realm.RealmObject;

/**
 * Created by Justin on 4/4/16.
 */
public class Program extends RealmObject {

    private String name;
    private String description;
    private String Id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
