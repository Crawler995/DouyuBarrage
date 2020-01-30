package space.zhang.dybarrage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zhang.dybarrage.bean.ChartData;
import space.zhang.dybarrage.bean.SenderLevelData;
import space.zhang.dybarrage.mapper.ChartDataMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ChartDataService {
    @Autowired
    private ChartDataMapper chartDataMapper;

    public ChartData getChartData(String roomId) {
        double barrageSendVelocity = chartDataMapper.getBarrageSendVelocity(roomId, getFiveSecAgoStr());
        ArrayList<SenderLevelData> senderLevelData = chartDataMapper.getSenderLevelData(roomId);

        return new ChartData(barrageSendVelocity, senderLevelData);
    }

    private String getFiveSecAgoStr() {
        long fiveSecAgo = new Date().getTime() - 5000;
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fiveSecAgo);
    }
}
