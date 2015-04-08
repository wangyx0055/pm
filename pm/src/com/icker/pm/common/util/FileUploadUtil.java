package com.icker.pm.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件上传工具类
 * 
 * @author Icker
 * 
 */
public class FileUploadUtil {

	/**
	 * spring单文件上传
	 * 
	 * @param request
	 * @return 返回文件存储相对路径
	 */
	public static Map<String, String> monofileUpload(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
		/** 构建图片保存的目录 **/
		String logoPathDir = "/files/" + dateformat.format(new Date());
		/** 得到图片保存目录的真实路径 **/
		String logoRealPathDir = request.getSession().getServletContext()
				.getRealPath(logoPathDir);
		/** 根据真实路径创建目录 **/
		File logoSaveFile = new File(logoRealPathDir);
		if (!logoSaveFile.exists())
			logoSaveFile.mkdirs();
		/** 页面控件的文件流 **/
		MultipartFile multipartFile = multipartRequest.getFile("file");
		/** 获取文件的后缀 **/
		String suffix = multipartFile.getOriginalFilename().substring(
				multipartFile.getOriginalFilename().lastIndexOf("."));
		/** 使用UUID生成文件名称 **/
		String newFileName = UUIDUtil.getUUID() + suffix;// 构建文件名称
		String oldFileName = multipartFile.getOriginalFilename();
		/** 拼成完整的文件保存路径加文件 **/
		String fileName = logoRealPathDir + File.separator + newFileName;
		File file = new File(fileName);

		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("========================================");
		System.out.println("文件长度: " + multipartFile.getSize());
		System.out.println("文件类型: " + multipartFile.getContentType());
		System.out.println("文件名称: " + multipartFile.getName());
		System.out.println("文件原名: "
				+ multipartFile.getOriginalFilename());
		System.out.println("文件新名: "+newFileName);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("oldFileName", oldFileName);
		map.put("path", logoPathDir+"/"+newFileName);
		return map;
	}

	/**
	 * Spring多文件上传
	 * 
	 * @param myfiles
	 * @param request
	 * @return 返回原文件名以及所有路径
	 */
	public static List<Map<String, String>> multifileUpload(
			MultipartFile[] myfiles, HttpServletRequest request) throws Exception{
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		// 并且上传多个文件时，前台表单中的所有<input
		// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for (MultipartFile multipartFile : myfiles) {
			if (multipartFile.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				SimpleDateFormat dateformat = new SimpleDateFormat(
						"yyyy/MM/dd/HH");
				/** 构建图片保存的目录 **/
				String logoPathDir = "/files/" + dateformat.format(new Date());
				/** 得到图片保存目录的真实路径 **/
				String logoRealPathDir = request.getSession()
						.getServletContext().getRealPath(logoPathDir);
				/** 根据真实路径创建目录 **/
				File logoSaveFile = new File(logoRealPathDir);
				if (!logoSaveFile.exists())
					logoSaveFile.mkdirs();

				/** 获取文件的后缀 **/
				String suffix = multipartFile.getOriginalFilename().substring(
						multipartFile.getOriginalFilename().lastIndexOf("."));
				/** 使用UUID生成文件名称 **/
				String newFileName = UUIDUtil.getUUID() + suffix;// 构建文件名称
				String oldFileName = multipartFile.getOriginalFilename();
				/** 拼成完整的文件保存路径加文件 **/
				String fileName = logoRealPathDir + File.separator
						+ newFileName;
				File file = new File(fileName);
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println("========================================");
				System.out.println("文件长度: " + multipartFile.getSize());
				System.out.println("文件类型: " + multipartFile.getContentType());
				System.out.println("文件名称: " + multipartFile.getName());
				System.out.println("文件原名: "
						+ multipartFile.getOriginalFilename());
				System.out.println("文件新名: "+newFileName);
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("oldFileName", oldFileName);
				map.put("path", logoPathDir+"/"+newFileName);
				list.add(map);
			}
		}
		return list;
	}

}
