import socket
import threading

class Send_string:
    def __init__(self, msg):
        self.msg = msg
        self.sock = socket.socket()
        self.sock.bind(('', 5001))
        print('init end')

    def connect_and_send(self):
        self.sock.listen(10)
        print('wait client')
        cli, info = self.sock.accept()
        print('client accepted')
        self.send_string(cli)

    def send_string(self, cli):
        cli.sendall(self.msg + ":::")
        print('send all')

    def run(self):
        while (True):
            self.connect_and_send()

if __name__=='__main__':
    send = Send_string("test send")
    t2 = threading.Thread(target=send.run())
    t2.start()