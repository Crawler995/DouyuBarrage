package space.zhang.dybarrage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zhang.dybarrage.bean.BarrageData;
import space.zhang.dybarrage.bean.RealtimeChartData;
import space.zhang.dybarrage.bean.SenderLevelData;
import space.zhang.dybarrage.mapper.RealtimeChartDataMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class RealtimeChartDataService {
    @Autowired
    private RealtimeChartDataMapper realtimeChartDataMapper;

    public RealtimeChartData getRealtimeChartData(String roomId) {
        double barrageSendVelocity = realtimeChartDataMapper.getBarrageSendVelocity(roomId, getFiveSecAgoStr());
        ArrayList<SenderLevelData> senderLevelData = realtimeChartDataMapper.getSenderLevelData(roomId);
        ArrayList<BarrageData> severalBarrages = realtimeChartDataMapper.getSeveralBarrages(roomId, getFiveSecAgoStr());

        return new RealtimeChartData(barrageSendVelocity, senderLevelData, severalBarrages);
    }

    private String getFiveSecAgoStr() {
        long fiveSecAgo = new Date().getTime() - 5000;
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fiveSecAgo);
    }
}
