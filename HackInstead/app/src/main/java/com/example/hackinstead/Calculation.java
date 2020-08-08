package com.example.hackinstead;

public class Calculation {
    public static int getRideValue(double excitement, int excitementModifier, double intensity, int intensityModifier, double nausea, int nauseaModifier) {
        int excitementValue = (int)(excitement * 100 * excitementModifier * 32) / 32768;
        int intensityValue = (int)(intensity * 100 * intensityModifier * 32) / 32768;
        int nauseaValue = (int)(nausea * 100 * nauseaModifier * 32) / 32768;
        return excitementValue + intensityValue + nauseaValue;
    }

    public static int[] getAgeValue(int rideValue) {
        return new int[] {
                (int)(rideValue * 1.5),
                (int)(rideValue * 1.2),
                rideValue,
                (int)(rideValue * 0.75),
                (int)(rideValue * 0.56),
                (int)(rideValue * 0.42),
                (int)(rideValue * 0.32),
                (int)(rideValue * 0.16),
                (int)(rideValue * 0.08),
                (int)(rideValue * 0.56)
        };
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
