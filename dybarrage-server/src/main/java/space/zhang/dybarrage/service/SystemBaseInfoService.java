package space.zhang.dybarrage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zhang.dybarrage.bean.NameDataCouple;
import space.zhang.dybarrage.mapper.SystemBaseInfoMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class SystemBaseInfoService {
    @Autowired
    private SystemBaseInfoMapper systemBaseInfoMapper;

    private static final long appThisLaunchTime = new Date().getTime();
    private static final String appThisLaunchTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
            format(appThisLaunchTime);

    public ArrayList<NameDataCouple> getSystemBaseInfo(String roomId) {
        int barrageNumFromThisLaunch = systemBaseInfoMapper.getBarrageNumFromThisLaunch(roomId, appThisLaunchTimeStr);
        int totalBarrageNum = systemBaseInfoMapper.getTotalBarrageNum(roomId);
        int senderNumFromThisLaunch = systemBaseInfoMapper.getSenderNumFromThisLaunch(roomId, appThisLaunchTimeStr);
        int totalSenderNum = systemBaseInfoMapper.getTotalSenderNum(roomId);

        ArrayList<NameDataCouple> res = new ArrayList<>();

        res.add(new NameDataCouple("系统此次运行时间", getAppThisRunTime()));
        res.add(new NameDataCouple("系统总共运行时间", "0"));
        res.add(new NameDataCouple("此次获取弹幕数", String.valueOf(barrageNumFromThisLaunch)));
        res.add(new NameDataCouple("总共获取弹幕数", String.valueOf(totalBarrageNum)));
        res.add(new NameDataCouple("此次发送弹幕用户数", String.valueOf(senderNumFromThisLaunch)));
        res.add(new NameDataCouple("总共发送弹幕用户数", String.valueOf(totalSenderNum)));

        return res;
    }

    private String getAppThisRunTime() {
        long now = new Date().getTime();
        return String.valueOf((int)((now - SystemBaseInfoService.appThisLaunchTime) / 1000));
    }
}
