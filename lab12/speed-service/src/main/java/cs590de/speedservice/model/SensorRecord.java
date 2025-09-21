package cs590de.speedservice.model;

// SensorRecord.java
import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorRecord {
    @JsonProperty("licencePlate") private String licencePlate; // some producers might use this
    @JsonProperty("licensePlate") private String licensePlate;
    private int minute;
    private int second;
    private int cameraId;

    public String getPlate(){ return licensePlate != null ? licensePlate : licencePlate; }
    public int getMinute(){ return minute; }
    public int getSecond(){ return second; }
    public int getCameraId(){ return cameraId; }
    public void setLicencePlate(String v){ this.licencePlate=v; }
    public void setLicensePlate(String v){ this.licensePlate=v; }
    public void setMinute(int v){ this.minute=v; }
    public void setSecond(int v){ this.second=v; }
    public void setCameraId(int v){ this.cameraId=v; }
}

