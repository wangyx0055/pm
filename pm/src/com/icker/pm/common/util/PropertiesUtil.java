package com.icker.pm.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import com.icker.pm.common.email.Mail;

public class PropertiesUtil {
	static String path = Mail.class.getResource("/web.properties").getPath();
	public static Properties loadProperties(){
		Properties properties = new Properties();
		try {
			path = URLDecoder.decode(path, "UTF-8");
			System.out.println(path);
			properties.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			System.err.println("找不到该properties配置文件");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("转换成流失败");
			e.printStackTrace();
		}
		return properties;
	}
}
