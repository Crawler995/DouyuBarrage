import websocket


class DyBarrageWebSocketClient:
    def __init__(self, on_open, on_message, on_close):
        self.__url = 'wss://danmuproxy.douyu.com:8506/'

        self.__websocket = websocket.WebSocketApp(self.__url,
            on_open=on_open, on_message=on_message, 
            on_error=self.__on_error, on_close=on_close)
    
    def start(self):
        self.__websocket.run_forever()

    def stop(self):
        self.__websocket.close()

    def send(self, msg):
        self.__websocket.send(msg)

    def __on_error(self, err):
        print(err)

    # def __on_close(self):
    #     print('close')