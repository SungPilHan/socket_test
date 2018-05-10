package send_recv_img;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Send_img extends Thread{
	private String ip;
	private int port;
	private byte[] img;
	private int img_length;
	private Socket socket;
	private OutputStream os;
	private DataOutputStream dos;
	
	public Send_img(String ip, int port, byte[] img, int img_length) {
		this.ip = ip;
		this.port = port;
		this.img = img;
		this.img_length = img_length;
	}
	
	public void run() {
		try {
			this.socket = new Socket(this.ip,this.port);
			System.out.println("socket create");
			
			this.os = this.socket.getOutputStream();
			this.dos = new DataOutputStream(this.os);
			System.out.println(String.valueOf(this.img_length)+":::");
			
			StringBuffer sb = new StringBuffer();
			sb.append(String.valueOf(this.img_length)+":::");
			sb.setLength(1024);
			this.dos.write(sb.toString().getBytes());
			this.dos.flush();
			System.out.println("img length send");
			System.out.println(sb.toString().length());
			
			Thread.sleep(10);
			int i = this.img_length;
			int count = 0;
			while (i > 0) {
				if(i>4093) {
					this.dos.write(this.img, count*4096, 4096);
				}
				else {
					this.dos.write(this.img, count*4096, i);
				}
				this.dos.flush();
				System.out.println("img send");
				
				i = i - 4096;
				//Thread.sleep(10);
				count ++;
				System.out.println(count);
			}			
			this.dos.close();
			this.os.close();
			this.socket.close();
			System.out.println("thread end");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("thread error");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sleep error");
		}
	}
}
