package space.zhang.dybarrage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zhang.dybarrage.bean.BarrageData;
import space.zhang.dybarrage.mapper.BarrageDataMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class BarrageDataService {
    @Autowired
    private BarrageDataMapper barrageDataMapper;

    public ArrayList<BarrageData> getBarrageData(String roomId) {
        return barrageDataMapper.getBarrageData(roomId, getFiveSecAgoStr());
    }

    private String getFiveSecAgoStr() {
        long fiveSecAgo = new Date().getTime() - 5000;
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fiveSecAgo);
    }
}
