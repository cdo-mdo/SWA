package cs590de.feeservice.model;

// OwnerEnrichedEvent.java
public class OwnerEnrichedEvent {
    private String licensePlate; private double speedMph; private String ownerName; private String ownerAddress;
    public OwnerEnrichedEvent(){}
    public String getLicensePlate(){ return licensePlate; }
    public double getSpeedMph(){ return speedMph; }
    public String getOwnerName(){ return ownerName; }
    public String getOwnerAddress(){ return ownerAddress; }
}
