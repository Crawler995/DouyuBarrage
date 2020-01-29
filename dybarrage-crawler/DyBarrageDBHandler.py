import pymysql
import datetime

class _DyBarrageDBHandler:
    def __init__(self):
        self.__conn = None
        self.__cursor = None
        self.__db_name = 'dybarrage'
        self.__table_name = 'barrages'
        self.__cols = ['rid', 'uid', 'cid', 'nn', 'level', 'txt', 'stime', 'ic']
        self.__cols = [
            ['rid', 'varchar(10) not null'],
            ['uid', 'varchar(20) not null'],
            ['cid', 'varchar(32) not null'],
            ['nn', 'varchar(60) not null'],
            ['level', 'int default 0 not null'],
            ['txt', 'varchar(200) not null'],
            ['stime', 'datetime not null'],
            ['ic', 'varchar(200) not null']
        ]
    
    def connect(self):
        self.__conn = pymysql.connect(host='localhost', user='root', password='crawler995', charset='utf8')
        self.__cursor = self.__conn.cursor()

    def __create_db(self):
        self.__cursor.execute('create database if not exists %s;' % self.__db_name)
        self.__cursor.execute('use %s;' % self.__db_name)
        self.__conn.commit()

    def __create_table(self):
        sql = 'create table if not exists %s (\n' % self.__table_name
        for col in self.__cols:
            sql += col[0] + ' ' + col[1] + ',\n'
        sql = sql[0:-2] + '\n);\n'
        
        self.__cursor.execute(sql)
        self.__conn.commit()

    def prepare(self):
        self.__create_db()
        self.__create_table()

    def disconnect(self):
        self.__cursor.close()
        self.__conn.close()

    def insert_barrage(self, barrage):
        insert_col_sql = 'insert into %s(' % self.__table_name
        insert_value_sql = 'values('

        barrage['stime'] = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

        for k in barrage.keys():
            for col in self.__cols:
                if k == col[0]:
                    insert_col_sql += k + ', '
                    if 'int' in col[1]:
                        insert_value_sql += barrage[k] + ', '
                    else:
                        insert_value_sql += '\'' + barrage[k].replace('\'', '') + '\', '

        sql = insert_col_sql[0:-2] + ') ' + insert_value_sql[0:-2] + ');'
        print(sql)
        
        self.__cursor.execute(sql)
        self.__conn.commit()


dy_barrage_db_handler = _DyBarrageDBHandler()
