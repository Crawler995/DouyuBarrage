package space.zhang.dybarrage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.zhang.dybarrage.bean.ChartData;
import space.zhang.dybarrage.service.ChartDataService;

@RestController
@RequestMapping("/api/room")
public class ChartDataController {
    @Autowired
    private ChartDataService chartDataService;

    @RequestMapping("/{roomId}/chartdata")
    public ChartData getChartData(@PathVariable String roomId) {
        return chartDataService.getChartData(roomId);
    }
}
