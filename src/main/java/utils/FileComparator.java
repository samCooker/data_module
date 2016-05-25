/*
 * FileName:    FileComparator.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2009
 * History:     2009-8-28 (LLM) 1.0 Create
 */
package utils;

import java.io.File;
import java.util.Comparator;


public class FileComparator {
    /**
     * 按文件的最后修改时间排序
     */
    public static class CompratorByLastModified<T> implements Comparator<T> {
        /**
         * @see Comparator#compare(Object, Object)
         */
        public int compare(T O1, T O2) {
            File file1 = (File)O1;
            File file2 = (File)O2;

            long diff = file1.lastModified() - file2.lastModified();
            if (diff > 0) {
                return 1;
            } else if (diff == 0) {
                return 0;
            } else {
                return -1;
            }
        }
    }
    /**
     * 按文件大小排序
     * The <code>CompratorBySize</code>
     *
     * @author  LLM
     * @version 1.0, 2009-8-28
     */
    public static class CompratorBySize<T> implements Comparator<T> {
        /**
         * @see Comparator#compare(Object, Object)
         */
        public int compare(T O1, T O2) {
            File file1 = (File)O1;
            File file2 = (File)O2;
            long diff = file1.length() - file2.length();
            if (diff > 0) {
                return 1;
            } else if (diff == 0) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * 按文件名排序
     * The <code>CompratorByName</code>
     *
     * @author  LLM
     * @version 1.0, 2011-8-5
     */
    public static class CompratorByName<T> implements Comparator<T> {
        /**
         * @see Comparator#compare(Object, Object)
         */
        public int compare(T O1, T O2) {
            File file1 = (File)O1;
            File file2 = (File)O2;

            return file1.getName().compareTo(file2.getName());
        }
    }
}
