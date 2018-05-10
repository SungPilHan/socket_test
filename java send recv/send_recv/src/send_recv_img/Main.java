package send_recv_img;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			File file = new File("test.png");
			try {
				FileInputStream fis = new FileInputStream(file);
				try {
					int img_length = fis.available();
					System.out.println("img_length : " + String.valueOf(img_length));
					byte[] img = new byte[img_length];
					fis.read(img);
					int i = 0;
					while(i<20) {
						Send_img si = new Send_img("192.168.0.5",5002,img,img_length);
						si.start();
						i++;
					}
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("fis.available() error");
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("fileinputstream error");
			}
	}
}
