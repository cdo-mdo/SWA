package cs590de.ownerservice.model;

public class OwnerInfo {
    private String licensePlate; private String name; private String address;
    public OwnerInfo(){}
    public OwnerInfo(String plate,String name,String addr){ this.licensePlate=plate; this.name=name; this.address=addr; }
    public String getLicensePlate(){ return licensePlate; }
    public String getName(){ return name; }
    public String getAddress(){ return address; }
}

