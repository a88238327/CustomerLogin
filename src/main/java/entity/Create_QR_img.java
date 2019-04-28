package entity;

import java.io.File;

public class Create_QR_img {

	public static void create(File logoFile, File qrCodeFile, String url, String note) {
		ZXingCode.drawLogoQRCode(logoFile, qrCodeFile, url, note);
		
	}

}
