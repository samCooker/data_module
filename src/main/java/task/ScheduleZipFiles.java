package task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utils.FileHelper;
import utils.ZipFileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cookie on 2016/7/19.
 */
@Component
public class ScheduleZipFiles {

    private Logger logger = Logger.getLogger("schedule.task.logger");

    private boolean isZipFileTaskRunning=false;
    private boolean isUnZipFileTaskRunning=false;

    //收件箱要压缩的文件路径
    @Value("${inbox.zip.filepath}")
    private String inboxZipFilepath;
    //收件箱压缩后文件存放路径
    @Value("${inbox.zip.file.place.path}")
    private String inboxZipFilePlacePath;
    @Value("${inbox.move.to.filePath}")
    private String inboxMoveToFilePath;

    //发件箱要解压的文件夹路径
    @Value("${outbox.unzip.folder.path}")
    private String outboxUnzipFolderPath;
    //发件箱解压后文件存放路径
    @Value("${outbox.unzip.file.place.path}")
    private String outboxUnzipFilePlacePath;

//    @Scheduled(cron = "15/15 * * * * ?")
    public void zipInboxFiles(){
        try {
            if (isZipFileTaskRunning) {
                return;
            }
            isZipFileTaskRunning=true;
            //判断文件是否存在
            File placeFolder = new File(inboxZipFilePlacePath);
            if(!placeFolder.exists()){
                placeFolder.mkdirs();
            }
            File moveToFolder = new File(inboxMoveToFilePath);
            if(!moveToFolder.exists()){
                moveToFolder.mkdirs();
            }
            //只压缩文件夹
            File[] filesList = FileHelper.getFolderFilesSort(inboxZipFilepath,null,FileHelper.SORTBY_LASTMOD);
            List<File> folderList = new ArrayList<File>();
            for (File file : filesList) {
                if (file.isDirectory()) {
                    folderList.add(file);
                }
            }
            if(folderList.size()==0){
                logger.debug("没有可压缩的文件");
                return;
            }
            //生成压缩文件的文件名
            String zipFileName = inboxZipFilePlacePath+"inbox"+new Date().getTime()+".zip";
            logger.debug("开始压缩任务。压缩文件名:"+zipFileName);
            ZipFileUtil.zipFolderFile(folderList,inboxZipFilepath, zipFileName,inboxMoveToFilePath);
        }catch (Exception e){
            logger.error("压缩文件任务异常："+traceToString(e));
        }finally {
            isZipFileTaskRunning=false;
            logger.debug("压缩任务结束。");
        }
    }

//    @Scheduled(cron = "25/25 * * * * ?")
    public void unzipOutboxFiles(){
        try {
            if (isUnZipFileTaskRunning) {
                return;
            }
            isUnZipFileTaskRunning=true;
            //判断文件是否存在
            File placeFolder = new File(outboxUnzipFilePlacePath);
            if(!placeFolder.exists()){
                placeFolder.mkdirs();
            }
            File[] zipFiles = FileHelper.getFolderFilesSort(outboxUnzipFolderPath,null,FileHelper.SORTBY_LASTMOD);
            logger.debug("开始解压任务。文件数："+zipFiles.length);
            for(File zipFile :zipFiles){
                String fileName = zipFile.getName().toLowerCase();
                if(fileName.contains(".zip")){
                    logger.debug("解压文件:"+fileName);
                    ZipFileUtil.upZipFile(zipFile.getAbsolutePath(), outboxUnzipFilePlacePath);
                    logger.debug("删除压缩文件:"+zipFile.getName());
                    zipFile.delete();
                }
            }
        }catch (Exception e){
            logger.error("解压文件任务异常："+traceToString(e));
        }finally {
            isUnZipFileTaskRunning=false;
            logger.debug("解压任务结束。");
        }
    }

    /**
     * 将异常信息转成string返回。
     * @param t
     * @return
     */
    public String traceToString(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

}
