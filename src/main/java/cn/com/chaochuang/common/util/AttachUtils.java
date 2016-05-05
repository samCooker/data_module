/*
 * FileName:    AttachUtils.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2006
 * History:     2006-2-21 (Jyb) 1.0 Create
 */
package cn.com.chaochuang.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        File fileIn = new File(file1);
        File fileOut = new File(file2);
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

    /**
     * 创建Path指定的文件夹
     *
     * @param path
     *            路径
     */
    public static void createFilePath(String path) {
        if (path != null && path.length() > 0) {
            File file = new File(path);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return;
                }
            }
        }
    }

    /**
     * 获取文件的MD5
     *
     * @param fileName
     *            文件名（包含文件的路径）
     * @return
     */
    public static String getFileMD5(String fileName) {
        File file = new File(fileName);
        // 校验文件是否存在
        if (!file.exists()) {
            return "";
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 删除文件
     *
     * @param fileName
     *            文件名
     * @return 删除结果
     */
    public static boolean removeFile(String fileName) {
        try {
            File file = new File(fileName);
            // 若文件存在则删除
            if (file.exists()) {
                System.gc();
                return file.delete();
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 删除文件列表指定的文件
     *
     * @param fileNameList
     *            文件列表
     */
    public static void removeFiles(List fileNameList) {
        if (fileNameList == null || fileNameList.size() == 0) {
            return;
        }
        try {
            for (int i = 0; i < fileNameList.size(); i++) {
                AttachUtils.removeFile((String) fileNameList.get(i));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 清空pathname目录，删除其下的所有文件和目录。
     *
     * @param pathname
     *            要清空的目录路径名
     */
    public static void cleanFolder(String pathname) {
        cleanFolder(new File(pathname));
    }

    /**
     * 清空target目录，删除其下的所有文件和目录。
     *
     * @param target
     *            要清空的目录
     */
    public static void cleanFolder(File target) {
        if (target != null && target.exists()) {
            try {
                if (target.isDirectory()) {
                    File[] subFile = target.listFiles();
                    if (subFile != null) {
                        for (int i = 0; i < subFile.length; i++) {
                            File f = subFile[i];
                            if (f.isDirectory()) {
                                cleanFolder(f);
                            }

                            if (!f.delete()) {
                                throw new RuntimeException("can not delete [" + f.getCanonicalPath()
                                                + "] is no folder!!!");
                            }
                        }
                    }
                } else {
                    throw new RuntimeException("[" + target.getCanonicalPath() + "] is no folder!!!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 清空指定文件夹下 指定前缀或后缀的文件
     *
     * @param filePath
     *            文件路径
     * @param prefix
     *            前缀
     * @param suffix
     *            后缀
     */
    public static void cleanFolder(String filePath, String prefix, String suffix) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File tag = files[i];
                Pattern p = Pattern.compile(prefix + "(.*)" + suffix);
                Matcher m = p.matcher(tag.getName());
                if (m.matches()) {
                    tag.delete();
                }
            }
        }
    }
}
