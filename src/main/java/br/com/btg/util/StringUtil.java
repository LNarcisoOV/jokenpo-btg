package br.com.btg.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {

	public static String objectToJSon(Object o) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(o);
			System.out.println("ResultingJSONstring = " + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}
}
