package space.zhang.dybarrage.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemBaseInfoMapper {
    @Select("select count(*) from barrages where rid=#{roomId} and stime >= #{appThisLaunchTime}")
    int getBarrageNumFromThisLaunch(String roomId, String appThisLaunchTime);

    @Select("select count(*) from barrages where rid=#{roomId}")
    int getTotalBarrageNum(String roomId);

    @Select("select count(distinct uid) from barrages where rid=#{roomId} and stime >= #{appThisLaunchTime}")
    int getSenderNumFromThisLaunch(String roomId, String appThisLaunchTime);

    @Select("select count(distinct uid) from barrages where rid=#{roomId}")
    int getTotalSenderNum(String roomId);
}
