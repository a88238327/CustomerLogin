package entity;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

public class postSms {

	public static String  login(String phone) {
		String code=(int)((Math.random()*9+1)*100000)+"";
		//必填:待发送手机号phone
		//必填:短信签名-可在短信控制台中找到
		String SignName ="車家网";
		//必填:短信模板-可在短信控制台中找到
		String TemplateCode="SMS_151045114";
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为"{\"name\":\"Tom\", \"code\":\"123\"}"
		String TemplateParam="{\"code\":\""+code+"\"}";
		Sms sms=new Sms();
		 SendSmsResponse result = null;
		try {
			result = sms.sendSms(phone, SignName, TemplateCode, TemplateParam);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        System.out.println("短信接口返回的数据----------------");
	        System.out.println("Code=" + result.getCode());
	        System.out.println("Message=" + result.getMessage());
	        System.out.println("RequestId=" + result.getRequestId());
	        System.out.println("BizId=" + result.getBizId());
	        if(result.getCode().equals("OK"))
	        {
	        	return code;
	        }
	        else {
	        	return "";
	        }
	}
	public static String  signup(String phone) {
		String code=(int)((Math.random()*9+1)*100000)+"";
		//必填:待发送手机号phone
		//必填:短信签名-可在短信控制台中找到
		String SignName ="車家网";
		//必填:短信模板-可在短信控制台中找到
		String TemplateCode="SMS_151045112";
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为"{\"name\":\"Tom\", \"code\":\"123\"}"
		String TemplateParam="{\"code\":\""+code+"\"}";
		Sms sms=new Sms();
		 SendSmsResponse result = null;
		try {
			result = sms.sendSms(phone, SignName, TemplateCode, TemplateParam);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        System.out.println("短信接口返回的数据----------------");
	        System.out.println("Code=" + result.getCode());
	        System.out.println("Message=" + result.getMessage());
	        System.out.println("RequestId=" + result.getRequestId());
	        System.out.println("BizId=" + result.getBizId());
	        if(result.getCode().equals("OK"))
	        {
	        	System.out.println(code);
	        	return code;
	        }
	        else {
	        	return "";
	        }
	}
	

}
