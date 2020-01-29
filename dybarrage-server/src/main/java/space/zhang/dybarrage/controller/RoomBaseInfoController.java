package space.zhang.dybarrage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.zhang.dybarrage.service.RoomBaseInfoService;

@RestController
@RequestMapping("/api/room")
public class RoomBaseInfoController {
    @Autowired
    private RoomBaseInfoService roomBaseInfoService;

    @RequestMapping("/{roomId}/roombaseinfo")
    public String getRoomBaseInfo(@PathVariable String roomId) {
        return roomBaseInfoService.getRoomBaseInfo(roomId);
    }
}
