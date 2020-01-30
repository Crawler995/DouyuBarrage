package space.zhang.dybarrage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.zhang.dybarrage.bean.BarrageData;
import space.zhang.dybarrage.service.BarrageDataService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/room")
public class BarrageDataController {
    @Autowired
    private BarrageDataService barrageDataService;

    @RequestMapping("/{roomId}/barragedata")
    public ArrayList<BarrageData> getBarrageData(@PathVariable  String roomId) {
        return barrageDataService.getBarrageData(roomId);
    }
}
