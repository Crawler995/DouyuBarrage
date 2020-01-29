package space.zhang.dybarrage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.zhang.dybarrage.bean.RealtimeChartData;
import space.zhang.dybarrage.service.RealtimeChartDataService;

@RestController
@RequestMapping("/api/room")
public class RealtimeChartDataController {
    @Autowired
    private RealtimeChartDataService realtimeChartDataService;

    @RequestMapping("/{roomId}/chartdata")
    public RealtimeChartData getRealtimeChartData(@PathVariable String roomId) {
        return realtimeChartDataService.getRealtimeChartData(roomId);
    }
}
