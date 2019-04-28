package test;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.lang.Validate;

import Tuil.Util;
import entity.EventLog;
import entity.GetTicket;
import entity.ValidDate;
import entity.ZXingCode;
import entity.getToken;
import net.sf.json.JSONObject;

public class TEst {
	public static void main(String[] args) {
		String str=getToken.get_ACCESSTOKEN();
		System.out.println(str);
	}
	 
}