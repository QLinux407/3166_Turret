package frc.robot;

public class Tracker {
    private static final Tracker instance = new Tracker();
    
    public static Tracker getInstance() {
        return instance;
    }

    private Tracker() {

    }
}