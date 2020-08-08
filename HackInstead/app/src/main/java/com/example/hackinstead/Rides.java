package com.example.hackinstead;

public class Rides {
    String ridename;
    Integer ride_excitement;
    Integer ride_intensity;
    Integer ride_nausea;

    public String getRidename() {return ridename; }

    public void setRidename(String ridename) { this.ridename = ridename; }

    public Integer getRideExcitement(){ return ride_excitement; }

    public void setExcitement(Integer ride_excitement) { this.ride_excitement = ride_excitement; }

    public Integer getRideIntensity(){ return ride_intensity; }

    public void setIntensity(Integer ride_intensity) { this.ride_intensity = ride_intensity; }

    public Integer getRideNausea(){ return ride_nausea; }

    public void setNausea(Integer ride_nausea) { this.ride_nausea = ride_nausea; }


    public Rides(String ridename, Integer ride_excitement, Integer ride_intensity, Integer ride_nausea){
        this.ridename = ridename;
        this.ride_excitement = ride_excitement;
        this.ride_intensity = ride_intensity;
        this.ride_nausea = ride_nausea;
    }

}
