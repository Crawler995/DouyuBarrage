package space.zhang.dybarrage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class CrawlRequestService {
    private static HashMap<String, LiveProcess> crawlingProcesses = new HashMap<>();
    private static final int PROCESS_LIFE = 20;
    private static final Logger logger = LoggerFactory.getLogger(CrawlRequestService.class);

    @Value("${crawler.path}")
    private String crawlerPath;

    public CrawlRequestService() {
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(5000);
                        for(String roomId : crawlingProcesses.keySet()) {
                            LiveProcess liveProcess = crawlingProcesses.get(roomId);

                            liveProcess.reduceLife(5);
                            if(liveProcess.getLife() <= 0) {
                                liveProcess.getProcess().destroy();
                                crawlingProcesses.remove(roomId);
                                logger.info("destroy crawl process: " + roomId);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void handleCrawlRequest(String roomId) {
        if(!crawlingProcesses.containsKey(roomId)) {
            logger.info("add crawl process: " + roomId);
            startCrawlProcess(roomId);
        } else {
            logger.info("recover crawl process: " + roomId);
            crawlingProcesses.get(roomId).recover();
        }
    }

    private void startCrawlProcess(String roomId) {
        try {
            String cmd = "python \"" + crawlerPath + "\\main.py\" " + roomId;
            Process proc = Runtime.getRuntime().exec(cmd);
            crawlingProcesses.put(roomId, new LiveProcess(proc, PROCESS_LIFE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class LiveProcess {
    private Process process;
    private int life;
    private int fullLife;

    public LiveProcess(Process process, int life) {
        this.process = process;
        this.life = life;
        this.fullLife = life;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void reduceLife(int reduce) {
        this.life -= reduce;
    }

    public void recover() {
        this.life = fullLife;
    }
}
