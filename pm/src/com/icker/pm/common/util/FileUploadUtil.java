package com.icker.pm.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
	public static Map<String, String> monofileUpload(HttpServletRequest request)
			throws Exception {
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
		System.out.println("文件原名: " + multipartFile.getOriginalFilename());
		System.out.println("文件新名: " + newFileName);

		Map<String, String> map = new HashMap<String, String>();
		map.put("oldFileName", oldFileName);
		map.put("path", logoPathDir + "/" + newFileName);
		return map;
	}

	/**
	 * Spring多文件上传
	 * 
	 * @param myfiles
	 * @param request
	 * @param logoPathDir
	 *            可以为空
	 * @param changeName
	 *            判断是否需要改变文件名
	 * @return 返回原文件名以及所有路径
	 */
	public static List<Map<String, String>> multifileUpload(
			MultipartFile[] myfiles, HttpServletRequest request,
			String logoPathDir, boolean changeName) throws Exception {
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		// 并且上传多个文件时，前台表单中的所有<input
		// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (MultipartFile multipartFile : myfiles) {
			if (multipartFile.isEmpty())
				System.out.println("文件未上传");
			else {
				String path = File.separator + "WEB-INF";
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy"
						+ File.separator + "MM" + File.separator + "dd"
						+ File.separator + "HH");
				String dirs = File.separator + "resources" + File.separator
						+ dateformat.format(new Date());
				/** 构建文件保存的目录 **/
				if (StringUtils.isBlank(logoPathDir))
					logoPathDir = path + dirs;

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

				String oldFileName = multipartFile.getOriginalFilename();
				String newFileName = null;
				/** 使用UUID生成文件名称 **/
				if (changeName)
					newFileName = UUIDUtil.getUUID() + suffix;// 构建文件名称
				else
					newFileName = oldFileName;

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
				System.out.println("文件长度: " + multipartFile.getSize() / 1024L
						+ " KB");
				System.out.println("文件类型: " + multipartFile.getContentType());
				System.out.println("文件名称: " + multipartFile.getName());
				System.out.println("文件原名: "
						+ multipartFile.getOriginalFilename());
				System.out.println("文件新名: " + newFileName);
				String filePath = dirs + File.separator + newFileName;
				System.out.println("文件路径: " + filePath);

				Map<String, String> map = new HashMap<String, String>();
				map.put("fileName", oldFileName);
				map.put("newFileName", newFileName);
				map.put("format", multipartFile.getContentType());
				map.put("size", String.valueOf(multipartFile.getSize() / 1024L));
				map.put("path", filePath);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 文件下载 主要方法
	 * 
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		// 获取项目根目录
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("");
		// 获取下载文件露肩
		String downLoadPath = ctxPath + File.separator + "WEB-INF" + storeName;
		// 获取文件的长度
		long fileLength = new File(downLoadPath).length();
		// 设置文件输出类型
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(storeName.getBytes("utf-8"), "ISO8859-1"));
		// 设置输出长度
		response.setHeader("Content-Length", String.valueOf(fileLength));
		// 获取输入流
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		// 输出流
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		// 关闭流
		bis.close();
		bos.close();
	}
}
