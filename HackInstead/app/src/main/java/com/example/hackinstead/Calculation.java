package com.example.hackinstead;

public class Calculation {
    public static int getRideValue(int excitement, int excitementModifier, int intensity, int intensityModifier, int nausea, int nauseaModifier) {
        int excitementValue = (excitement * 100 * excitementModifier * 32) / 32768;
        int intensityValue = (intensity * 100 * intensityModifier * 32) / 32768;
        int nauseaValue = (nausea * 100 * nauseaModifier * 32) / 32768;
        return excitementValue + intensityValue + nauseaValue;
    }

    public static int getAgeValue(int rideValue, int age) {
        if(age < 5)
            return (int)(rideValue * 1.5);
        else if(age < 13)
            return (int)(rideValue * 1.2);
        else if(age < 40)
            return rideValue;
        else if(age < 64)
            return (int)(rideValue * 0.75);
        else if(age < 88)
            return (int)(rideValue * 0.56);
        else if(age < 104)
            return (int)(rideValue * 0.42);
        else if(age < 120)
            return (int)(rideValue * 0.32);
        else if(age < 128)
            return (int)(rideValue * 0.16);
        else if(age < 200)
            return (int)(rideValue * 0.08);
        else
            return (int)(rideValue * 0.56);
    }

    public static int getExistingRideTypeValue(boolean exists, int rideValue) {
        if(exists)
            return (int)(rideValue * 0.75);
        else
            return rideValue;
    }

    public static int getAdmissionValue(boolean admission, int rideValue) {
        if(admission)
            return (int)(rideValue * 0.25);
        else
            return rideValue;
    }

    public static double getMaxPrice(int rideValue) {
        return ((rideValue / 10) * 2) - 0.1;
    }
}
