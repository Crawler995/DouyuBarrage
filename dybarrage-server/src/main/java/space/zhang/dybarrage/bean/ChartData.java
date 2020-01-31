package space.zhang.dybarrage.bean;

import java.util.ArrayList;

public class ChartData {
    private double barrageSendVelocity;
    private ArrayList<NameDataCouple> senderLevel;
    private ArrayList<NameDataCouple> topSon;

    public ChartData() {
    }

    public ChartData(double barrageSendVelocity, ArrayList<NameDataCouple> senderLevel, ArrayList<NameDataCouple> topSon) {
        this.barrageSendVelocity = barrageSendVelocity;
        this.senderLevel = senderLevel;
        this.topSon = topSon;
    }

    public double getBarrageSendVelocity() {
        return barrageSendVelocity;
    }

    public void setBarrageSendVelocity(double barrageSendVelocity) {
        this.barrageSendVelocity = barrageSendVelocity;
    }

    public ArrayList<NameDataCouple> getSenderLevel() {
        return senderLevel;
    }

    public void setSenderLevel(ArrayList<NameDataCouple> senderLevel) {
        this.senderLevel = senderLevel;
    }

    public ArrayList<NameDataCouple> getTopSon() {
        return topSon;
    }

    public void setTopSon(ArrayList<NameDataCouple> topSon) {
        this.topSon = topSon;
    }
}