package send_recv_string;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Recv_string extends Thread{
	private String ip;
	private int port;
	private String msg = null;
	private Socket socket;
	private InputStream is;
	
	public Recv_string(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public String get_string() {
		return this.msg;
	}
	
	public void run() {
		try {
			this.socket = new Socket(ip,port);
			System.out.println("socket created");
			
			this.is = this.socket.getInputStream();
			byte[] msg_bytes = new byte[1024];
			this.is.read(msg_bytes, 0, msg_bytes.length);
			this.msg = new String(msg_bytes);
			this.msg = this.msg.substring(0, this.msg.indexOf(":::"));
			System.out.println("recv msg");
			
			this.is.close();
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
