package com.icker.pm.common.util;

import java.util.List;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.icker.pm.pojo.User;

public class JsonUtil {
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public String writeListToJson(List<User> users) throws Exception{
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		
        System.out.println("jsonGenerator");
        //list转换成JSON字符串
		jsonGenerator.writeObject(users);
		System.out.println();
	    System.out.println("ObjectMapper");
	    //用objectMapper直接返回list转换成的JSON字符串
	    System.out.println("1###" + objectMapper.writeValueAsString(users));
	    System.out.print("2###");
	    //objectMapper list转换成JSON字符串
	    objectMapper.writeValue(System.out, users);
        
		return null;
		
	}
}