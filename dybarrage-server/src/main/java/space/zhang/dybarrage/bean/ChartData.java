package space.zhang.dybarrage.bean;

import java.util.ArrayList;

public class ChartData {
    private double barrageSendVelocity;
    private ArrayList<SenderLevelData> senderLevel;

    public ChartData() {
    }

    public ChartData(double barrageSendVelocity, ArrayList<SenderLevelData> senderLevel) {
        this.barrageSendVelocity = barrageSendVelocity;
        this.senderLevel = senderLevel;
    }

    public double getBarrageSendVelocity() {
        return barrageSendVelocity;
    }

    public void setBarrageSendVelocity(double barrageSendVelocity) {
        this.barrageSendVelocity = barrageSendVelocity;
    }

    public ArrayList<SenderLevelData> getSenderLevel() {
        return senderLevel;
    }

    public void setSenderLevel(ArrayList<SenderLevelData> senderLevel) {
        this.senderLevel = senderLevel;
    }
}