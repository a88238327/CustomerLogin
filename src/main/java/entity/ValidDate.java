package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidDate {

	 
	 
		/**
		 * 验证字符串时间，是否在365天内
		 * @param str
		 * @return
		 */
		public static boolean isValidDate(String str,int time) {
			boolean convertSuccess=true;
			//时间格式定义
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//获取当前时间日期--nowDate
			String nowDate = format.format(new Date());
			//获取30天前的时间日期--minDate
			Calendar calc = Calendar.getInstance();
			calc.add(Calendar.DAY_OF_MONTH, -time);
			String minDate = format.format(calc.getTime());
			try {
				//设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				format.setLenient(false);
				//获取字符串转换后的时间--strDate
				String strDate = format.format(format.parse(str));
				//判断传的STR时间，是否在当前时间之前，且在30天日期之后-----测试的时候打印输出结果
//				System.out.println("nowDate.compareTo(strDate):"+ nowDate.compareTo(strDate));
//				System.out.println("strDate.compareTo(minDate):"+ strDate.compareTo(minDate));
				if (nowDate.compareTo(strDate) >= 0 && strDate.compareTo(minDate) >= 0){
					convertSuccess = true;
				}else{
					convertSuccess = false;
				}
			} catch (ParseException e) {
				convertSuccess=false;
			}
			return convertSuccess;
		}
	
}
