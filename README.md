# DouyuBarrage

斗鱼弹幕抓取及实时弹幕数据可视化，分为crawler(弹幕抓取)，server(弹幕统计数据服务器)，web(统计数据可视化前端)三部分。

正在开发中……

![预览](https://github.com/Crawler995/DouyuBarrage/blob/master/doc/preview.jpg)

## 运行

### 安装依赖
```bash
# dybarrage-crawler
pip install -r requirements.txt
# dybarrage-server
mvn clean install
# dybarrage-web
npm install
```

### 准备

1. 确保本地mysql数据库处于可用状态
2. 打开dybarrage-crawler中的`config.ini`文件，修改其中的mysql连接设置
3. 打开dybarrage-server中的`application.properties`文件，修改其中的`crawler.path`为`dybarrage-crawler`的绝对路径

### 运行

```bash
# dybarrage-server
mvn spring-boot:run
# dybarrage-web
npm start
```

然后打开浏览器，访问`localhost:3000?roomid=[斗鱼房间号]`即可，如`localhost:3000?roomid=12306`。

不需要管`dybarrage-crawler`模块，其将会由`dybarrage-server`启动。

## 相关技术

1. `dybarrage-crawler`

   Python 3.7, Mysql

2. `dybarrage-server`

   Java 8, Spring Boot, Mybatis

3. `dybarrage-web`

   JavaScript, React, Ant Design, Echarts

## 架构

   ![系统运行基本流程](https://github.com/Crawler995/DouyuBarrage/blob/master/doc/process.jpg)

先随便画一个，有一说一，要说清楚这个系统有丶难，我得去复习软件工程了，不然我没法画数据流图什么的，讲不清楚这个系统的结构……

### 前后端接口

注：baseURL = /api/room/{roomId}

| 接口          | 描述                           |
| :------------ | :----------------------------- |
| /roombaseinfo | 获取房间基本信息               |
| /sysbaseinfo  | 获取系统基本信息               |
| /chartdata    | 获取图表相关数据               |
| /barragedata  | 获取弹幕信息                   |
| /crawl        | 心跳请求，保持弹幕抓取进程运行 |

## 一些闲聊

1. 自己运行了两天，主要分析了C皇和电棍的直播间，发现棍孝子确实只会刷屏，平均一个人发了5、6条弹幕，有一个头号棍孝子一个人发了400多条弹幕；C皇观众比较均匀，平均一个人发了2条弹幕，最多的一个人也只发了20多条。