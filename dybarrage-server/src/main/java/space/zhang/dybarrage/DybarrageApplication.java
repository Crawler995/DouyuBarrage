package space.zhang.dybarrage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("space.zhang.dybarrage.mapper")
@SpringBootApplication
public class DybarrageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DybarrageApplication.class, args);
    }

}
