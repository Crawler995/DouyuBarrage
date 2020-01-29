from DyBarrageWebSocketClient import *
from DyBarrageRawMsgHandler import *
from DyBarrageDBHandler import *
import threading
import time


class DyBarrageCrawler:
    def __init__(self, room_id):
        self.__room_id = room_id
        self.__heartbeat_thread = None
        self.__client = DyBarrageWebSocketClient(on_open=self.__prepare, on_message=self.__receive_msg, on_close=self.__stop)
        self.__msg_handler = dy_barrage_raw_msg_handler
        self.__db_handler = dy_barrage_db_handler
        self.__should_stop_heartbeat = False

    def start(self):
        self.__db_handler.connect()
        self.__db_handler.prepare()
        self.__client.start()

    def __stop(self):
        self.__logout()
        self.__client.stop()
        self.__db_handler.disconnect()
        self.__should_stop_heartbeat = True

    def __prepare(self):
        self.__login()
        self.__join_group()
        self.__start_heartbeat()

    def __receive_msg(self, msg):
        chat_messages = self.__msg_handler.get_chat_messages(msg)
        for chat_message in chat_messages:
            self.__db_handler.insert_barrage(chat_message)

    def __login(self):
        login_msg = 'type@=loginreq/room_id@=%s/dfl@=sn@A=105@Sss@A=1/username@=%s/uid@=%s/ver@=20190610/aver@=218101901/ct@=0/' % (self.__room_id, '61609154', '61609154')
        self.__client.send(self.__msg_handler.dy_encode(login_msg))

    def __join_group(self):
        join_group_msg = 'type@=joingroup/rid@=%s/gid@=1/' % (self.__room_id)
        self.__client.send(self.__msg_handler.dy_encode(join_group_msg))

    def __start_heartbeat(self):
        self.__heartbeat_thread = threading.Thread(target=self.__heartbeat)
        self.__heartbeat_thread.start()
        
    def __heartbeat(self):
        heartbeat_msg = 'type@=mrkl/'
        heartbeat_msg_byte = self.__msg_handler.dy_encode(heartbeat_msg)

        while True:
            self.__client.send(heartbeat_msg_byte)
            for i in range(90):
                time.sleep(0.5)
                if self.__should_stop_heartbeat:
                    return

    def __logout(self):
        logout_msg = 'type@=logout/'
        logout_msg_byte = self.__msg_handler.dy_encode(logout_msg)