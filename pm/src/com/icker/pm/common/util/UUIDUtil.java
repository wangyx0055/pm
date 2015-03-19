package com.icker.pm.common.util;

import java.util.UUID;

/**
 * 设定uuid
 * @author Icker
 *
 */
public class UUIDUtil {
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}
