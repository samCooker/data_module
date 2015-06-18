/*
 * FileName:    AttachUtils.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2006
 * History:     2006-2-21 (Jyb) 1.0 Create
 */
package cn.com.chaochuang.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jyb
 * @version 1.0, 2007-7-3
 */
public abstract class AttachUtils {
    @Value("${upload.rootpath}")
    public static String                  rootPath           = "/upload_files/";

    /**
     * 用于产生唯一文件名的算法，在文件标识相同时做进一步区别的识别流水号
     */
    private static int                    uniqueFileSequence = 0;

    /**
     * 用于产生唯一文件名的算法，记录上一个产生的文件标识
     */
    private static Date                   preFileIdentify    = null;

    /**
     * 用于产生唯一文件名的算法，用来产生文件名字符串
     */
    private static final SimpleDateFormat FILE_NAME_FORMAT   = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * @return 唯一文件名
     */
    public static synchronized String getUniqueFileName() {
        Date fileIdentify = new Date();
        if (fileIdentify.equals(preFileIdentify)) {
            uniqueFileSequence++;
        } else {
            uniqueFileSequence = 0;
        }
        preFileIdentify = fileIdentify;

        StringBuffer fileName = new StringBuffer();
        fileName.append(FILE_NAME_FORMAT.format(fileIdentify));
        if (uniqueFileSequence > 0) {
            fileName.append(Integer.toString(uniqueFileSequence, 10));
        }

        return fileName.toString();
    }

    /**
     * 下载附件
     *
     * @param response
     *            HttpServletResponse
     * @param fileName
     *            输出的文件名
     * @param savePath
     *            文件所在路径
     * @param isImage
     *            是否是图片格式
     * @return null 或者是 错误描述
     */
    public static String downloadAttach(HttpServletResponse response, String fileName, String savePath, boolean isImage) {
        BufferedInputStream is = null;
        OutputStream os = null;
        String result = null;
        try {
            response.setContentType("application/octet-stream");
            String trueName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            if (isImage) {
                response.setHeader("Content-Disposition", "inline; filename=\"" + trueName + "\"");
            } else {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + trueName + "\"");
            }
            is = new BufferedInputStream(new FileInputStream(savePath));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int len = 0;
            while ((len = is.read(b)) > 0) {
                os.write(b, 0, len);
            }
        } catch (DataAccessException ex) {
            result = "查找附件信息异常：" + ex.getMessage();
        } catch (IOException ex) {
            // 下载过程中用户取消或其它IO异常
            result = "用户取消下载或其它IO异常" + ex.getMessage();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    result = "关闭输入流异常：" + ex.getMessage();
                }
            }
            if (os != null) {
                try {
                    os.flush();
                } catch (IOException ex) {
                    result = "清空输出缓冲异常：" + ex.getMessage();
                }
                try {
                    os.close();
                } catch (IOException ex) {
                    result = "关闭输出流异常：" + ex.getMessage();
                }
            }
        }
        return result;
    }

    /**
     * 将文件保存到指定目录
     *
     * @param multipartFile
     *            MultipartFile
     * @param physicalFile
     *            物理文件名
     */
    public static void saveToFile(MultipartFile multipartFile, String physicalFile) {
        try {
            File f = new File(physicalFile);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
            }
            multipartFile.transferTo(f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 读取输入流中的数据保存至指定目录
     *
     * @param is
     *            输入流
     * @param physicalFile
     *            文件存储目录
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void saveToFile(InputStream is, String physicalFile) throws FileNotFoundException, IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(physicalFile)));
        int len = -1;
        while ((len = bis.read()) != -1) {
            bos.write(len);
            bos.flush();
        }
        bos.close();
        bis.close();
    }

    /**
     * 拷贝文件
     *
     * @param file1
     *            源文件
     * @param file2
     *            目标文件
     * @throws Exception
     *             Exception
     */
    public static void copyFile(String file1, String file2) throws Exception {
        java.io.File fileIn = new java.io.File(file1);
        java.io.File fileOut = new java.io.File(file2);
        if (!fileOut.exists()) {
            fileOut.getParentFile().mkdirs();
            fileOut.createNewFile();
        }
        FileInputStream in1 = new FileInputStream(fileIn);
        FileOutputStream out1 = new FileOutputStream(fileOut);
        byte[] bytes = new byte[1024];
        int c;
        while ((c = in1.read(bytes)) != -1) {
            out1.write(bytes, 0, c);
        }
        in1.close();
        out1.close();
    }
}
