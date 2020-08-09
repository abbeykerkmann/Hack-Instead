package com.example.hackinstead;

public class Rides {
    String type, name;
    double excitement, intensity, nausea;
    boolean sameRideType, entryFee;

    public String getType() {return type; }

    public void setType(String type) { this.type = type; }

    public String getName() {return name; }

    public void setName(String name) { this.type = name; }

    public double getExcitement(){ return excitement; }

    public void setExcitement(double excitement) { this.excitement = excitement; }

    public double getIntensity(){ return intensity; }

    public void setIntensity(double intensity) { this.intensity = intensity; }

    public double getNausea(){ return nausea; }

    public void setNausea(double nausea) { this.nausea = nausea; }

    public boolean getSameRideType(){ return sameRideType; }

    public void setSameRideType(boolean sameRideType) { this.sameRideType = sameRideType; }

    public boolean getEntryFee(){ return entryFee; }

    public void setEntryFee(boolean entryFee) { this.entryFee = entryFee; }


    public Rides(String type, double excitement, double intensity, double nausea, boolean sameRideType, boolean entryFee){
        this.type = type;
        this.excitement = excitement;
        this.intensity = intensity;
        this.nausea = nausea;
        this.sameRideType = sameRideType;
        this.entryFee = entryFee;
    }

    public Rides(String name, String type, double excitement, double intensity, double nausea, boolean sameRideType, boolean entryFee){
        this.name = name;
        this.type = type;
        this.excitement = excitement;
        this.intensity = intensity;
        this.nausea = nausea;
        this.sameRideType = sameRideType;
        this.entryFee = entryFee;
    }
}
