package com.rnei.common.util;

import static com.rnei.service.constants.RENIDataConstants.SESSION_TIME;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	public static Timestamp currentTimeStamp(){
		return Timestamp.valueOf(LocalDateTime.now());
	}
	public static Timestamp endTimeStamp(){
		return Timestamp.valueOf(LocalDateTime.now().plusMinutes(SESSION_TIME));
	}
	
	//TODO to be deleted

	public static void main(String[] args) {

		String[] originalPassword = {"1001rnei"};
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = "";

		System.out.println("ORIGINAL \t HASHED");
		System.out.println("=========\t=======");
		for(String password : originalPassword){
			hashedPassword = encoder.encode(password);
			System.out.println(password + "\t\t" + hashedPassword);
		}
		/*
		 * INSERT INTO user(`USER_ID`, `PASSWORD`, `STATUS`) VALUES ('1001', 
'$2a$10$D0sOsiHt1lfmxCf/RfNAxuJ5IkKVIZvo1O3vP02xa7iJvA6JeXXjq', '1');


INSERT INTO authorities (USER_ID, AUTHORITY) VALUES ('1001', 'ROLE_ADMIN');

		 */
	}
	
}