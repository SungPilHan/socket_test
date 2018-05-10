import socket
import time
import threading
import cv2
import numpy as np

class Recv_img:
    img = b''
    img_leng = 0

    def __init__(self):
        self.sock = socket.socket()
        self.sock.bind(('', 5002))
        print('init end')

    def connect_and_recv(self):
        self.sock.listen(10)
        print('wait client')

        cli, info = self.sock.accept()
        print('client accepted')

        self.img = self.recv_img(cli)
        cli.close()
        return self.img

    def recv_img(self, cli):
        data = cli.recv(1024)
        print(len(data))

        img_length = data.split(":::")[0]
        print(int(img_length))

        time.sleep(0.01)
        row_img = ""
        i = int(img_length)
        self.img_leng = i
        while i > 0:
            row_data = cli.recv(4096)
            if i>4096:
                row_img += row_data
            else:
                row_img += row_data[0:i]
            i -= 4096
        return row_img

    def run(self):
        while (True):
            self.img = self.connect_and_recv()
            print(len(self.img))

            if (float(len(self.img)) / self.img_leng) >= 0.95:
                f = open("test.png", "w")
                f.write(self.img)
                f.close()
                break

    def get_msg(self):
        return self.img

if __name__=="__main__":
    recv = Recv_img()
    t = threading.Thread(target = recv.run())
    t.start()
    t.join()
#    recv.run()
#    img = recv.get_msg()
#    img = recv.connect_and_recv()