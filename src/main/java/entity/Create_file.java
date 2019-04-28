package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Create_file {

	public static boolean create_leaflet(String phone, ResultSet rs) throws Exception {
		
		String qr_path;
		//获取二维码url
		qr_path=Read_File.get_qrpath(phone);
		System.out.println(qr_path);
		String leaflet_path=img_path.leaflet_path+rs.getString("leaflet_name");
		System.out.println(leaflet_path);
		InputStream qr = new FileInputStream(qr_path);  
        InputStream leaflet = new FileInputStream(leaflet_path);  
        BufferedImage image = ImageIO.read(leaflet); 
        BufferedImage image2 = ImageIO.read(qr);  
        Graphics g = image.getGraphics();  
        g.drawImage(image2, rs.getInt("x"), rs.getInt("y"), rs.getInt("width"),rs.getInt("height"),null);  
        OutputStream outImage = new FileOutputStream(img_path.path+phone+rs.getString("leaflet_name"));  
        JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(outImage);  
        enc.encode(image);  
        qr.close();  
        leaflet.close();  
        outImage.close();  

		return true;
	}

}
