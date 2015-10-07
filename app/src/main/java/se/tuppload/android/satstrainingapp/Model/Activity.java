package se.tuppload.android.satstrainingapp.Model;

public final class Activity implements  Comparable<Activity> {

    public final Booking booking;
    public final String comment;
    public final String date;
    public final int distanceInKm;
    public final int durationInMinutes;
    public final String id;
    public final String source;
    public final String status;
    public final String subType;
    public final String type;


    public Activity(Booking booking, String comment, String date, int distanceInKm, int durationInMinutes,
                    String id, String source, String status, String subType, String type) {

        this.booking = booking;
        this.comment = comment;
        this.date = date;
        this.distanceInKm = distanceInKm;
        this.durationInMinutes = durationInMinutes;
        this.id = id;
        this.source = source;
        this.status = status;
        this.subType = subType;
        this.type = type;
    }

    @Override
    public int compareTo(Activity other) {
        return date.compareTo(other.date);
    }
}
