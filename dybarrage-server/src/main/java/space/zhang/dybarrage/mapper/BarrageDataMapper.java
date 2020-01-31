package space.zhang.dybarrage.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import space.zhang.dybarrage.bean.BarrageData;

import java.util.ArrayList;

@Repository
public interface BarrageDataMapper {
    @Select("select nn as username, txt as barrage, ic as avatar_url from barrages " +
            "where rid=#{roomId} and stime>=#{fiveSecAgoStr} limit ${num};")
    ArrayList<BarrageData> getBarrageData(String roomId, String fiveSecAgoStr, int num);
}
