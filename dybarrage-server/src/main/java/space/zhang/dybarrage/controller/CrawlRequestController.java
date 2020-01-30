package space.zhang.dybarrage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import space.zhang.dybarrage.service.CrawlRequestService;

@Controller
@RequestMapping("/api/room")
public class CrawlRequestController {
    @Autowired
    private CrawlRequestService crawlRequestService;

    @RequestMapping("/{roomId}/crawl")
    public ResponseEntity<Object> handleCrawlRequest(@PathVariable String roomId) {
        crawlRequestService.handleCrawlRequest(roomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
