package com.reni.common.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import static com.reni.data.constants.RENIDataConstants.SESSION_TIME;
import org.springframework.util.StringUtils;

public class CommonUtil {

	//TODO - util methods to make code generic.
	
	public static boolean isNullOrEmpty(String... values){
		if(values!=null && values.length>0){
			for(String value : values){
				if(StringUtils.isEmpty(value)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static String generateSessionId(){
		return  UUID.randomUUID().toString();
	}
	public static void main(String[] args) {
		currentTimeStamp();
	}
	public static Timestamp currentTimeStamp(){
		return Timestamp.valueOf(LocalDateTime.now());
	}
	public static Timestamp endTimeStamp(){
		return Timestamp.valueOf(LocalDateTime.now().plusMinutes(SESSION_TIME));
	}
}
