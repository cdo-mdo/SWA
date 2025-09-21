package cs590de.ownerservice.model;

public class OwnerEnrichedEvent {
    private String licensePlate; private double speedMph; private String ownerName; private String ownerAddress;
    public OwnerEnrichedEvent(){}
    public OwnerEnrichedEvent(String plate,double mph,String name,String addr){
        this.licensePlate=plate; this.speedMph=mph; this.ownerName=name; this.ownerAddress=addr;
    }
    public String getLicensePlate(){ return licensePlate; }
    public double getSpeedMph(){ return speedMph; }
    public String getOwnerName(){ return ownerName; }
    public String getOwnerAddress(){ return ownerAddress; }
}

