package com.davidrosu.ciucaslapet;

import java.util.List;

public class Data {

    private Double angle;

    public Data(List<Byte> list) {
        String string = new String();

        for (Byte e : list) {
            string += String.valueOf((char)((byte)e));
        }

        this.angle = Double.valueOf(string);
    }

    @Override
    public String toString() {
        return String.valueOf(angle);
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public boolean isUP() {
        return this.angle > 0.85;
    }

    public boolean isDOWN() {
        return this.angle < 0.20;
    }
}
