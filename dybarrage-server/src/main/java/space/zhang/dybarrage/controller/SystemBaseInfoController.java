package space.zhang.dybarrage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.zhang.dybarrage.bean.NameDataCouple;
import space.zhang.dybarrage.service.SystemBaseInfoService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/room")
public class SystemBaseInfoController {
    @Autowired
    private SystemBaseInfoService systemBaseInfoService;

    @RequestMapping("/{roomId}/sysbaseinfo")
    public ArrayList<NameDataCouple> getSystemBaseInfo(@PathVariable String roomId) {
        return systemBaseInfoService.getSystemBaseInfo(roomId);
    }
}
