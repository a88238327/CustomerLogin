package entity;

import java.util.HashMap;

import org.apache.log4j.chainsaw.Main;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;



public class ocr {

	//设置APPID/AK/SK
    
    public static String ocr_plateLicense(byte[] file) {
    	String APP_ID = "15720099";
        String API_KEY = "hKHNhMOylAKsjUQ7VQXb74EN";
        String SECRET_KEY = "vnfiX3IG7ROhaDeOGMDonP5GYgvsM8Zi";
    	AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("multi_detect", "false");
        // 参数为二进制数组
        JSONObject res = client.plateLicense(file, options);
        System.out.println(res.toString(2));
        return res.toString();
    }
    public static String ocr_vehicleLicense_front(byte[] file){
    	String APP_ID = "15720099";
        String API_KEY = "hKHNhMOylAKsjUQ7VQXb74EN";
        String SECRET_KEY = "vnfiX3IG7ROhaDeOGMDonP5GYgvsM8Zi";
    	AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
    	HashMap<String, String> options = new HashMap<String, String>();
       
        options.put("accuracy", "normal");      
         //参数为二进制数组
        JSONObject res = client.vehicleLicense(file, options);
        System.out.println(res.toString());
        if(res.has("words_result"))
        {
        	System.out.println(res.get("words_result").toString());
            return res.get("words_result").toString();
        }
        return "error";
        
    }
    public static String ocr_vehicleLicense_back(byte[] file){
    	String APP_ID = "15720099";
        String API_KEY = "hKHNhMOylAKsjUQ7VQXb74EN";
        String SECRET_KEY = "vnfiX3IG7ROhaDeOGMDonP5GYgvsM8Zi";
    	AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
    	HashMap<String, String> options = new HashMap<String, String>();
        
        options.put("accuracy", "normal");  
        options.put("vehicle_license_side", "back"); 
         //参数为二进制数组
        JSONObject res = client.vehicleLicense(file, options);
        System.out.println(res.toString());
        if(res.has("words_result"))
        {
	        System.out.println(res.get("words_result").toString());
	        return res.get("words_result").toString();
        }
        return "error";
        
    }
	public static String ocr_drivingLicense_front(byte[] file) {
		String APP_ID = "15720099";
        String API_KEY = "hKHNhMOylAKsjUQ7VQXb74EN";
        String SECRET_KEY = "vnfiX3IG7ROhaDeOGMDonP5GYgvsM8Zi";
    	AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
    	HashMap<String, String> options = new HashMap<String, String>();
    	
         //参数为二进制数组
        JSONObject res = client.drivingLicense(file, options);
        System.out.println(res.toString());
        if(res.has("words_result"))
        {
	        System.out.println(res.get("words_result").toString());
	        return res.get("words_result").toString();
        }
        return "error";
	}
	public static String ocr_drivingLicense_back(byte[] file) {
		String APP_ID = "15720099";
        String API_KEY = "hKHNhMOylAKsjUQ7VQXb74EN";
        String SECRET_KEY = "vnfiX3IG7ROhaDeOGMDonP5GYgvsM8Zi";
    	AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
    	HashMap<String, String> options = new HashMap<String, String>();
//    	options.put("driving_license_side", "back");
         //参数为二进制数组
        JSONObject res = client.drivingLicense(file, options);
        System.out.println(res.toString());
        if(res.has("words_result"))
        {
	        System.out.println(res.get("words_result").toString());
	        return res.get("words_result").toString();
        }
        return "error";
	}
    
    
}
