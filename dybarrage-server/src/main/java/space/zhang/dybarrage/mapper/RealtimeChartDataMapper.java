package space.zhang.dybarrage.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import space.zhang.dybarrage.bean.BarrageData;
import space.zhang.dybarrage.bean.SenderLevelData;

import java.util.ArrayList;

@Repository
public interface RealtimeChartDataMapper {
    @Select("select (count(*) / 5.0) as barrage_send_velocity from barrages " +
            "where rid=#{roomId} and stime>=#{fiveSecAgoStr};")
    double getBarrageSendVelocity(String roomId, String fiveSecAgoStr);

    @Select("select level, count(*) as user_num from barrages " +
            "where rid=#{roomId} " +
            "group by level " +
            "order by level")
    ArrayList<SenderLevelData> getSenderLevelData(String roomId);

    @Select("select nn as username, txt as barrage, ic as avatar_url from barrages " +
            "where rid=#{roomId} and stime>=#{fiveSecAgoStr} limit 10;")
    ArrayList<BarrageData> getSeveralBarrages(String roomId, String fiveSecAgoStr);
}
