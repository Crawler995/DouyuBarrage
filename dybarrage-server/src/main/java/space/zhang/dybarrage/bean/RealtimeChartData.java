package space.zhang.dybarrage.bean;

import java.util.ArrayList;

public class RealtimeChartData {
    private double barrageSendVelocity;
    private ArrayList<SenderLevelData> senderLevel;
    private ArrayList<BarrageData> severalBarrages;

    public RealtimeChartData() {
    }

    public RealtimeChartData(double barrageSendVelocity, ArrayList<SenderLevelData> senderLevel, ArrayList<BarrageData> severalBarrages) {
        this.barrageSendVelocity = barrageSendVelocity;
        this.senderLevel = senderLevel;
        this.severalBarrages = severalBarrages;
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

    public ArrayList<BarrageData> getSeveralBarrages() {
        return severalBarrages;
    }

    public void setSeveralBarrages(ArrayList<BarrageData> severalBarrages) {
        this.severalBarrages = severalBarrages;
    }
}