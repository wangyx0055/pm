package com.icker.pm.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class FileUtil {
	private static final String FOLDER_SEPARATOR = "/";
	private static final char EXTENSION_SEPARATOR = '.';

	public static File createFile(String filePath, String suffix, String data,
			HttpServletRequest request) throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
		/** 构建代码保存的目录 **/
		String answerPathDir = "/" + filePath + "/"
				+ dateformat.format(new Date());
		/** 得到图片保存目录的真实路径 **/
		String answerRealPathDir = request.getSession().getServletContext()
				.getRealPath(answerPathDir);
		File answerFile = new File(answerRealPathDir);
		if (!answerFile.exists())
			answerFile.mkdirs();
		String newfileName = UUIDUtil.getUUID() + "." + suffix;
		String fileName = answerFile + File.separator + newfileName;
		File file = new File(fileName);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(data);
			fw.close();
		} catch (IOException e) {
			throw new IOException("文件写入数据失败");
		}
		System.out.println(fileName);
		return file;
	}

	/**
	 * 功能：复制文件或者文件夹。
	 * 
	 * @date 2014年06月24日
	 * @param inputFile
	 *            源文件
	 * @param outputFile
	 *            目的文件
	 * @param isOverWrite
	 *            是否覆盖(只针对文件)
	 * @throws IOException
	 */
	public static void copy(File inputFile, File outputFile, boolean isOverWrite)
			throws IOException {
		if (!inputFile.exists()) {
			throw new RuntimeException(inputFile.getPath() + "源目录不存在!");
		}
		copyPri(inputFile, outputFile, isOverWrite);
	}

	/**
	 * 功能：为copy 做递归使用。
	 * 
	 * 
	 * @date 2014年06月24日
	 * @param inputFile
	 * @param outputFile
	 * @param isOverWrite
	 * @throws IOException
	 */
	private static void copyPri(File inputFile, File outputFile,
			boolean isOverWrite) throws IOException {
		// 是个文件。
		if (inputFile.isFile()) {
			copySimpleFile(inputFile, outputFile, isOverWrite);
		} else {
			// 文件夹
			if (!outputFile.exists()) {
				outputFile.mkdir();
			}
			// 循环子文件夹
			for (File child : inputFile.listFiles()) {
				copy(child,
						new File(outputFile.getPath() + "/" + child.getName()),
						isOverWrite);
			}
		}
	}

	/**
	 * 功能：copy单个文件
	 * 
	 * 
	 * @date 2014年06月24日
	 * @param inputFile
	 *            源文件
	 * @param outputFile
	 *            目标文件
	 * @param isOverWrite
	 *            是否允许覆盖
	 * @throws IOException
	 */
	private static void copySimpleFile(File inputFile, File outputFile,
			boolean isOverWrite) throws IOException {
		// 目标文件已经存在
		if (outputFile.exists()) {
			if (isOverWrite) {
				if (!outputFile.delete()) {
					throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
				}
			} else {
				// 不允许覆盖
				return;
			}
		}
		InputStream in = new FileInputStream(inputFile);
		OutputStream out = new FileOutputStream(outputFile);
		byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		in.close();
		out.close();
	}

	/**
	 * 功能：删除文件
	 * 
	 * 
	 * @date 2014年06月24日
	 * @param file
	 *            文件
	 */
	public static void delete(File file) {
		deleteFile(file);
	}

	/**
	 * 功能：删除文件，内部递归使用
	 * 
	 * 
	 * @date 2014年06月24日
	 * @param file
	 *            文件
	 * @return boolean true 删除成功，false 删除失败。
	 */
	private static void deleteFile(File file) {
		if (file == null || !file.exists()) {
			return;
		}
		// 单文件
		if (!file.isDirectory()) {
			boolean delFlag = file.delete();
			if (!delFlag) {
				throw new RuntimeException(file.getPath() + "删除失败！");
			} else {
				return;
			}
		}
		// 删除子目录
		for (File child : file.listFiles()) {
			deleteFile(child);
		}
		// 删除自己
		file.delete();
	}

	/**
	 * 从文件路径中抽取文件的扩展名, 例如. "mypath/myfile.txt" -> "txt". *
	 * 
	 * @date 2014年06月24日
	 * @param 文件路径
	 * @return 如果path为null，直接返回null。
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return path.substring(extIndex + 1);
	}

	/**
	 * 从文件路径中抽取文件名, 例如： "mypath/myfile.txt" -> "myfile.txt"。 *
	 * 
	 * @date 2014年06月24日
	 * @param path
	 *            文件路径。
	 * @return 抽取出来的文件名, 如果path为null，直接返回null。
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1)
				: path);
	}

	/**
	 * 功能：保存文件。
	 * 
	 * 
	 * @date 2014年06月24日
	 * @param content
	 *            字节
	 * @param file
	 *            保存到的文件
	 * @throws IOException
	 */
	public static void save(byte[] content, File file) throws IOException {
		if (file == null) {
			throw new RuntimeException("保存文件不能为空");
		}
		if (content == null) {
			throw new RuntimeException("文件流不能为空");
		}
		InputStream is = new ByteArrayInputStream(content);
		save(is, file);
	}

	/**
	 * 功能：保存文件
	 * 
	 * @date 2014年06月24日
	 * @param streamIn
	 *            文件流
	 * @param file
	 *            保存到的文件
	 * @throws IOException
	 */
	public static void save(InputStream streamIn, File file) throws IOException {
		if (file == null) {
			throw new RuntimeException("保存文件不能为空");
		}
		if (streamIn == null) {
			throw new RuntimeException("文件流不能为空");
		}
		// 输出流
		OutputStream streamOut = null;
		// 文件夹不存在就创建。
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		streamOut = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
			streamOut.write(buffer, 0, bytesRead);
		}
		streamOut.close();
		streamIn.close();
	}
}