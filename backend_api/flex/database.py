import pymysql
import datetime
import security

class DB():
    def __init__(self):
        self.host = 'localhost'
        self.database = 'cst_flex'
        self.user = 'david'
        self.password = 'david'
        self.connect()

    def connect(self):
        self.db = pymysql.connect(
            self.host,
            self.user,
            self.password,
            self.database
        )

    def query(self, sql):
        cursor = self.db.cursor()
        cursor.execute(sql)

        return cursor

    def create_user(self, username, password, firstname, lastname, weight, age, gender):
        self.db.cursor().execute(
            '''
            INSERT INTO users
            (username, password, firstname, lastname, weight, age, gender)
            VALUES(%s, %s, %s, %s, %s, %s, %s)
            ''',
            (username, security.encrypt_password(password), firstname, lastname, weight, age, gender)
        )

        self.add_training(username, '0', '0', '0', '0', '0')

        self.db.commit()
               
    def login(self, username, password):
        cursor = self.db.cursor(pymysql.cursors.DictCursor)
        
        if cursor.execute(
            '''
            SELECT * FROM users
            WHERE username = %s
            ''',
            (username,)
            ) != 1:
            return False

        user = cursor.fetchone()

        if user == None:
            return False

        return security.check_encrypted_password(password, user['password'])


    def add_training(self, username, start_timestamp, stop_timestamp, total_count, success_count, calories_count):
        self.db.cursor().execute(
            '''
            INSERT INTO training
            (username, start_timestamp, stop_timestamp, total_count, success_count, calories_count)
            VALUES(%s, %s, %s, %s, %s, %s)
            ''',
            (username, start_timestamp, stop_timestamp, total_count, success_count, calories_count)
        )

        self.db.commit()

        return True

    def total_count(self, username):
        cursor = self.db.cursor(pymysql.cursors.DictCursor)

        cursor.execute(
            '''
            SELECT sum(success_count) as count FROM training
            WHERE username = %s
            ''',
            (username, )
        )

        return int(cursor.fetchone()['count'])

    def weight(self, username):
        cursor = self.db.cursor(pymysql.cursors.DictCursor)

        cursor.execute(
            '''
            SELECT weight FROM users
            WHERE username = %s
            ''',
            (username, )
        )

        return int(cursor.fetchone()['weight'])