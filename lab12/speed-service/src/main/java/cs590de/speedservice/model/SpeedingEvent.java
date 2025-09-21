package cs590de.speedservice.model;

// SpeedingEvent.java
public class SpeedingEvent {
    private String licensePlate;
    private double speedMph;
    private int t1Seconds;
    private int t2Seconds;
    public SpeedingEvent(){}
    public SpeedingEvent(String plate,double mph,int t1,int t2){
        this.licensePlate=plate; this.speedMph=mph; this.t1Seconds=t1; this.t2Seconds=t2;
    }
    public String getLicensePlate(){ return licensePlate; }
    public double getSpeedMph(){ return speedMph; }
    public int getT1Seconds(){ return t1Seconds; }
    public int getT2Seconds(){ return t2Seconds; }
}
