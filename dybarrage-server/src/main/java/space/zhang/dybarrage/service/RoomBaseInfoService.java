package space.zhang.dybarrage.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class RoomBaseInfoService {

    public String getRoomBaseInfo(String roomId) {
        StringBuilder sb = new StringBuilder();
        try {
            String baseURL = "http://open.douyucdn.cn/api/RoomApi/room/";
            URL url = new URL(baseURL + roomId);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            if(connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                while((len = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, len));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
