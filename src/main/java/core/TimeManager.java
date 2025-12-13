package core;

public class TimeManager {
    public enum TimeOfDay { MORNING, AFTERNOON, EVENING, NIGHT }

    private int day;
    private TimeOfDay timeOfDay;

    public TimeManager() {
        this.day = 1;
        this.timeOfDay = TimeOfDay.MORNING;
    }

    public void advanceTime() {
        switch(timeOfDay) {
            case MORNING -> timeOfDay = TimeOfDay.AFTERNOON;
            case AFTERNOON -> timeOfDay = TimeOfDay.EVENING;
            case EVENING -> timeOfDay = TimeOfDay.NIGHT;
            case NIGHT -> {
                timeOfDay = TimeOfDay.MORNING;
                day++;
            }
        }
    }

    public int getDay() { return day; }
    public TimeOfDay getTimeOfDay() { return timeOfDay; }
}