class _DyBarrageRawMsgHandler:
    def dy_encode(self, msg):
        data_len = len(msg) + 9

        msg_byte = msg.encode('utf-8')
        len_byte = int.to_bytes(data_len, 4, 'little')
        send_byte = bytearray([0xb1, 0x02, 0x00, 0x00])
        end_byte = bytearray([0x00])

        data = len_byte + len_byte + send_byte + msg_byte + end_byte

        return data

    def dy_decode(self, msg_byte):
        pos = 0
        msg = []

        while pos < len(msg_byte):
            content_length = int.from_bytes(msg_byte[pos: pos + 4], byteorder='little')
            content = msg_byte[pos + 12: pos + 3 + content_length].decode(encoding='utf-8', errors='ignore')
            msg.append(content)
            pos += (4 + content_length)

        return msg

    def get_chat_messages(self, msg_byte):
        decode_msg = self.dy_decode(msg_byte)
        messages = []
        for msg in decode_msg:
            res = self.__parse_msg(msg)
            if res['type'] != 'chatmsg':
                continue
            messages.append(res)
        
        return messages

    def __parse_msg(self, raw_msg):
        res = {}
        attrs = raw_msg.split('/')[0:-1]
        for attr in attrs:
            attr = attr.replace('@S', '/')
            attr = attr.replace('@A', '@')
            couple = attr.split('@=')
            res[couple[0]] = couple[1]
        
        return res
        


dy_barrage_raw_msg_handler = _DyBarrageRawMsgHandler()