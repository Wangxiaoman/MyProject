package qlm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class QLMFileInFile {

	public static void main(String[] args) throws Exception {
		File file = new File("/Users/xiaoman/Downloads/rf.data");
		InputStream fis = new FileInputStream(file);
		byte[] byt = new byte[fis.available()];
		fis.read(byt);
		fis.close();

		System.out.println("byt size:" + byt.length);

		int j = 1;
		for (int i = 0; i < byt.length;) {
			byte byteType = byt[i];
			int type = byteToInt(byteType);
			i = i + 1;
			System.out.println("byteType:" + type);
			byte[] byteSize = Arrays.copyOfRange(byt, i, i + 4);
			i = i + 4;
			int size = byteArrayToInt(byteSize);
			System.out.println("byteSize:" + size);

			// byte[] byteFile = Arrays.copyOfRange(byt, i,i+size);

			if (type == 0) {
				File filei = new File("/Users/xiaoman/Downloads/temppng/" + j++ +".png");
				OutputStream ot = new FileOutputStream(filei);
				ot.write(byt, i, i + size);
				ot.close();
			}else if(type == 2){
				break;
			}

			i = i + size;
		}

	}

	public static int byteArrayToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	public static int byteToInt(byte b) {
		// Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
		return b & 0xFF;
	}

}
