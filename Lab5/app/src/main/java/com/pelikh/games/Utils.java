package com.pelikh.games;

public class Utils {
    public static double getDistanceBetweenPoints(double p1x, double p1y, double p2x, double p2y) {
        return Math.sqrt(
                Math.pow(p2x - p1x, 2) +
                Math.pow(p2y - p1y, 2)
        );
    }
}
