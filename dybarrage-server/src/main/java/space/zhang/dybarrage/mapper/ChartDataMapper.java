package space.zhang.dybarrage.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import space.zhang.dybarrage.bean.NameDataCouple;
import space.zhang.dybarrage.bean.SenderLevelData;

import java.util.ArrayList;

@Repository
public interface ChartDataMapper {
    @Select("select (count(*) / 5.0) as barrage_send_velocity from barrages " +
            "where rid=#{roomId} and stime>=#{fiveSecAgoStr};")
    double getBarrageSendVelocity(String roomId, String fiveSecAgoStr);

    @Select("select level, count(*) from barrages " +
            "where rid=#{roomId} " +
            "group by level " +
            "order by level")
    ArrayList<NameDataCouple> getSenderLevelData(String roomId);

    @Select("select nn, count(*) from barrages " +
            "where rid=#{roomId} " +
            "group by nn " +
            "order by count(*) desc " +
            "limit 20")
    ArrayList<NameDataCouple> getTopSon(String roomId);
}
