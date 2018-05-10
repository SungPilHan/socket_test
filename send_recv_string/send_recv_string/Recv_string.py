import socket
import threading


class Recv_string:
    msg = ""

    def __init__(self):
        self.sock = socket.socket()
        self.sock.bind(('', 5000))
        print('init end')

    def connect_and_recv(self):
        self.sock.listen(10)
        print('wait client')
        cli, info = self.sock.accept()
        print('client accepted')
        msg = self.recv_string(cli)
        return msg

    def recv_string(self, cli):
        data = cli.recv(1024)
        cli.close()
        msg = data.split(":::")[0]
        return msg

    def run(self):
        while (True):
            msg = self.connect_and_recv()
            print(msg)

    def get_msg(self):
        return self.msg

if __name__=='__main__':
	recv = Recv_string()
	t = threading.Thread(target = recv.run())
	t.start()