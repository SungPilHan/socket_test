package send_recv_string;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Send_string ss;
//		int i = 0;
//		while (i<=10) {
//			ss = new Send_string("192.168.0.5", 5000, "hello...........");
//			ss.start();
//			i++;	
//		}
		
		
		Recv_string rs;
		int i = 0;
		i = 0;
		while(i<=10) {
			rs = new Recv_string("192.168.0.5",5001);
			rs.start();
			try {
				rs.join();
				String msg = rs.get_string();
				System.out.println(msg);
				i++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("join problem");
			}
		}
	}
}
