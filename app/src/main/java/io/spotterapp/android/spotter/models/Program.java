package io.spotterapp.android.spotter.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Justin on 4/4/16.
 */
public class Program extends RealmObject {

    private String name;
    private String description;
    private String id;
    private RealmList<ProgramDay> programDays;

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
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<ProgramDay> getProgramDays() {
        return programDays;
    }

    public void setProgramDays(RealmList<ProgramDay> programDays) {
        this.programDays = programDays;
    }

    //public int countProgramDays() {
    //
    //    return (Integer(this.programDays.size()) != null) ? 0 : this.programDays.size();
    //}
}
