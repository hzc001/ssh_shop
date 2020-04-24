package cn.it.shop.utils.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.it.shop.model.FileImage;
import cn.it.shop.utils.FileUpload;

/**
 * 文件上传工具的实现类
 * @author Hzc
 *
 */
@Component("fileUpload")
public class FileUploadUtil implements FileUpload {

	// @Value表示去beans.xml文件中找id="prop"的bean，它是通过注解的方式读取properties配置文件的，然后去相应的配置文件中读取key=filePath的值
	@Value("#{prop.basePath+prop.filePath}")
	private String filePath;

	@Value("#{prop.basePath+prop.bankImagePath}")
	private String bankImagePath;

//	public void setFilePath(String filePath) {
//		System.out.println(filePath);
//		this.filePath = filePath;
//	}
//	
//	public void setBankImagePath(String bankImagePath) {
//		System.out.println(bankImagePath);
//		this.bankImagePath = bankImagePath;
//	}

	// 获取银行图标
	public String[] getBankImage() {
		// new File(path).list() 方法是获取路径中所有文件名，new File(path).list(FilenameFilter filter) 带有过滤功能，可以过滤掉不想要的文件，只返回想要的文件到数组中
		String[] list = new File(bankImagePath).list(new FilenameFilter() {

			// 测试指定文件是否应该包含在某一文件列表中
			@Override
			public boolean accept(File dir, String name) { // FilenameFilter 接口中只有一个accept方法
				System.out.println("dir:" + dir + ",name:" + name);
				// 通过后缀名来实现文件的过滤效果
				// 返回真就放到list中，返回假就过滤掉
				return name.endsWith(".gif");
			}
		});
		return list;
	}

	// 实现文件上传的功能，返回上传后新的文件名称
	@Override
	public String uploadFile(FileImage fileImage) {
		// 获取新唯一文件名
		String pic = newFileName(fileImage.getFileName());
		try {
			FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));
			return pic;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			fileImage.getFile().delete();
		}
	}

	// 1. 通过文件名获取扩展名
	private String getFileExt(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	// 2. 生成UUID随机数，作为新的文件名
	private String newFileName(String fileName) {
		String ext = getFileExt(fileName);
		return UUID.randomUUID().toString() + "." + ext;
	}

}
