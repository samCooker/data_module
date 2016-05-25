package utils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cookie on 2016/5/2.
 */
public class FileHelper {

    /**
     * 排序：按文件大小
     */
    public static String SORTBY_SIZE = "size";
    /**
     * 排序：按最后修改时间
     */
    public static String SORTBY_LASTMOD = "lastmod";

    /**
     * 获取排序的文件列表
     *
     * @param directory 文件目录
     * @param filter    过滤器
     * @param sortFlag  排序标志
     * @return 文件列表
     */
    public static File[] getFolderFilesSort(String directory, FilenameFilter filter, String sortFlag) {
        File file = new File(directory);
        File[] files = null;
        if (filter == null) {
            files = file.listFiles();
        } else {
            files = file.listFiles(filter);
        }
        if (files == null || files.length == 0) {
            return new File[0];
        }
        if (sortFlag == null) {
            return files;
        }
        if (sortFlag.equals(SORTBY_SIZE)) {
            Arrays.sort(files, new FileComparator.CompratorBySize<File>());
        } else if (sortFlag.equals(SORTBY_LASTMOD)) {
            Arrays.sort(files, new FileComparator.CompratorByLastModified<File>());
        } else {
            Arrays.sort(files);
        }
        return files;
    }

    /**
     * 移走指定目录下文件
     *
     * @param sourcePath 源目录
     * @param targetPath 目标目录
     */
    public static void moveFolder(String sourcePath, String targetPath) throws IOException {
        File sFile = new File(sourcePath);
        if (!sFile.exists()) {
            return;
        }
        File tFile = new File(targetPath);
        if (!tFile.exists()) {
            createFilePath(targetPath);
        }
        tFile = new File(targetPath + "/" + sFile.getName());
        if (!tFile.exists()) {
            createFilePath(targetPath + "/" + sFile.getName());
        }
        Map<String, File> files = new HashMap<String, File>();
        for (int i = 0; i < sFile.listFiles().length; i++) {
            files.put(sFile.listFiles()[i].getPath(), sFile.listFiles()[i]);
        }
        File temp;
        //移走主xml文件(先拷贝到目的 再从源目录删除)
        for (Map.Entry<String, File> entry : files.entrySet()) {
            temp = entry.getValue();
            //假如是目录要递归复制文件
            if (temp.isDirectory()) {
                moveFolder(temp.getPath(), tFile.getPath() + "/" + temp.getName());
            } else {
                //实体文件则进行复制和删除操作
                copyFile(temp.getPath(), tFile.getPath() + "/" + temp.getName());
            }
            if (temp.exists()) {
                removeFile(temp.getPath());
            }
        }
        removeFile(sFile.getPath());
    }

    /**
     * 创建Path指定的文件夹
     *
     * @param path 路径
     */
    public static boolean createFilePath(String path) {
        if (path != null && path.trim().length() > 0) {
            File file = new File(path);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 拷贝文件
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws Exception Exception
     */
    public static void copyFile(String sourceFile, String targetFile) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            File fileIn = new File(sourceFile);
            File fileOut = new File(targetFile);
            if (!fileOut.exists()) {
                fileOut.getParentFile().mkdirs();
                fileOut.createNewFile();
            }
            in = new FileInputStream(fileIn);
            out = new FileOutputStream(fileOut);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = in.read(bytes)) != -1) {
                out.write(bytes, 0, c);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 移走文件
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws Exception Exception
     */
    public static void moveFile(String sourceFile, String targetFile) throws IOException {
        File fileIn = new File(sourceFile);
        if (!fileIn.exists()) {
            return;
        }
        copyFile(sourceFile, targetFile);
        removeFile(sourceFile);
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @return 删除结果
     */
    public static boolean removeFile(String fileName) {
        File file = new File(fileName);
        //若文件存在则删除
        if (file.exists()) {
            System.gc();
            return file.delete();
        }
        return false;
    }

    /**
     * @param dirPath 源目录或文件路径
     * @return true：删除成功，false：删除失败
     * @Function : deleteSelfAndAllChildren
     * @Desc : 刪除指定目录和其下所有子目录和文件，如果指定的是文件则直接删除
     */
    public static boolean deleteSelfAndAllChildren(String dirPath) {
        File srcFile = new File(dirPath);
        if (!srcFile.exists()) {
            return false;
        }

        if (srcFile.isFile()) {
            srcFile.delete();
        } else {
            File[] children = srcFile.listFiles();

            if (children.length == 0) {
                srcFile.delete();
            } else {
                for (File file : children) {//先删除子文件和目录
                    deleteSelfAndAllChildren(file.getAbsolutePath());
                }
                //再删除本身
                srcFile.delete();
            }
        }
        return true;
    }

    /**
     * @param dirPath
     * @return true：删除成功，false：删除失败
     * @Function :  deleteAllChildren
     * @Desc :  删除一个目录下所有的子目录和文件
     */
    public static boolean deleteAllChildren(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return false;
        }
        File[] children = dir.listFiles();
        for (File file : children) {
            deleteSelfAndAllChildren(file.getAbsolutePath());
        }

        return true;
    }

    /**
     * 获取文件的ND5码
     *
     * @param file 要获取MD5码的文件
     * @return
     * @throws IOException
     */
    @Deprecated
    @SuppressWarnings("resource")
    public static String getFileMd5Code(File file) throws IOException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (java.security.NoSuchAlgorithmException ex) {
            throw new AssertionError(ex);
        }
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        md.update(byteBuffer);
        in.close();
        ch.close();
        return bufferToHex(md.digest());
    }

    /**
     * 算出字节数组的MD5码
     *
     * @param bytes
     * @return
     */
    private static String bufferToHex(byte bytes[]) {
        int m = 0, n = bytes.length;
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    /**
     * @param bt
     * @param stringbuffer
     */
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

}
