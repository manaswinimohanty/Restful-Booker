package com.api.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Map;

public class JsonUtils {
	
	public static ObjectMapper mapper=new ObjectMapper();
	
	
	public static String getBaseUrl(String path) throws Throwable{
		
		Map<String,String>map=mapper.readValue(new File(path), new TypeReference<Map<String,String>>(){});
		
		
		return map.get("endpoint");
	}

}
