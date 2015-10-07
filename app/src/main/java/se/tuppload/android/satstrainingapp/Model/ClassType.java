package se.tuppload.android.satstrainingapp.Model;


import java.util.HashMap;
import java.util.List;

public class ClassType {

    public final List<String> classCategoryIds;
    public final String description;
    public final String id;
    public final String name;
    public final HashMap<String, Profile> profile;
    public final String videoUrl;

    public ClassType(List<String> classCategoryIds, String description, String id, String name, HashMap<String, Profile> profile, String videoUrl) {

        this.classCategoryIds = classCategoryIds;
        this.description = description;
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.videoUrl = videoUrl;
    }
}