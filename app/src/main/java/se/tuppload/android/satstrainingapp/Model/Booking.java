package se.tuppload.android.satstrainingapp.Model;


public class Booking {

    public final String status;
    public final Class aClass;
    public final String center;
    public final String bookingId;
    public final int positionInQueue;

    public Booking(String status, Class aClass, String center, String bookingId, int positionInQueue) {
        this.status = status;
        this.aClass = aClass;
        this.center = center;
        this.bookingId = bookingId;
        this.positionInQueue = positionInQueue;
    }
}

