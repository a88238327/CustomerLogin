package entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class tobyte {

	 public static final byte[] input2byte(InputStream inStream)throws IOException {  
         ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
         byte[] buff = new byte[100];  
         int rc = 0;  
         while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
          }  
         byte[] in2b = swapStream.toByteArray();  
         return in2b;  
	 }  
}
