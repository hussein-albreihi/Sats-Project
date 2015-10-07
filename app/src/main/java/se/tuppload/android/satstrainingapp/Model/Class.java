package se.tuppload.android.satstrainingapp.Model;

public class Class {

    public final String centerId;
//    public final String centerFilterId;
    public final String classTypeId;
    public final int durationInMinutes;
    public final String id;
    public final String instructorId;
    public final String name;
    public final String startTime;
    public final int bookedPersonsCount;
    public final int maxPersonsCount;
    public final int waitingListCount;

    public Class(String centerId, String classTypeId, int durationInMinutes, String id, String instructorId,
                 String name, String startTime, int bookedPersonsCount, int maxPersonsCount, int waitingListCount) {

        this.centerId = centerId;
//        this.centerFilterId = centerFilterId;
        this.classTypeId = classTypeId;
        this.durationInMinutes = durationInMinutes;
        this.id = id;
        this.instructorId = instructorId;
        this.name = name;
        this.startTime = startTime;
        this.bookedPersonsCount = bookedPersonsCount;
        this.maxPersonsCount = maxPersonsCount;
        this.waitingListCount = waitingListCount;
    }
}
