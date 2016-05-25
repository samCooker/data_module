package task;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utils.FileHelper;
import utils.MD5Helper;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by cookie on 2016/5/23.
 */
@Component
public class ScheduleGenerateXmlFile {

    private Logger logger = Logger.getLogger("schedule.task.logger");

    //发送
    @Value("${send.xml.filepath}")
    private String sendXmlFilePath;
    //接收
    @Value("${receive.xml.filepath}")
    private String receiveXmlFilePath;

    private int fileNum=0;

    @PostConstruct
    public void initVelocity(){
        logger.debug("初始化velocity。");
        Properties properties = new Properties();
        // 获取类路径
        properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
        properties.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        properties.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        Velocity.init(properties);
    }

    //@Scheduled(cron = "5/15 * * * * ?")
    public void generateSendXmlFile(){
        logger.debug("生成发送xml文件");
        BufferedWriter writer = null;
        try {
            Date currentDate = new Date();
            //若目标文件不存在，则生成目标文件夹
            String folderName = sendXmlFilePath +UUID.randomUUID().toString();
            FileHelper.createFilePath(folderName);
            VelocityContext context = new VelocityContext();

            String contentFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(currentDate) + ".xml";
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
            context.put("currentTime", currentTime);
            context.put("contentFileName", contentFileName);
            context.put("number", ++fileNum);
            //生成远程公文交换文件包名:文件包名为UUID
            String envelopeUuid = UUID.randomUUID().toString();
            context.put("envelopeUuid",envelopeUuid);
            String title = new SimpleDateFormat("MM-dd HH:mm:ss").format(currentDate)+"关于公文交换的测试(请勿操作)";
            context.put("title",title);
            //生成封体文件
            Template template = Velocity.getTemplate("sendtemplate/envelopeContentTemplate.vm");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folderName +"/"+ contentFileName), "utf-8"));
            template.merge(context, writer);
            writer.flush();
            writer.close();
            File contentFile = new File(folderName+"/" + contentFileName);
            if(contentFile.exists()){
                //context.put("contentFileMd5",FileHelper.getFileMd5Code(contentFile));
            }
            //生成封首文件
            template = Velocity.getTemplate("sendtemplate/envelopeTemplate.vm");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folderName+"/" + "envelope.xml"), "utf-8"));
            template.merge(context, writer);
            writer.flush();
            logger.debug("生成发送文件夹："+folderName);
//            writer.close();
//            //移动文件
//            FileHelper.createFilePath(targetFilepath);
//            FileHelper.moveFolder(folderName,targetFilepath);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //@Scheduled(cron = "5/10 1/30  * * * ?")
    public void generateReceiveXmlFile(){
        logger.debug("开始生成接收xml文件");
        BufferedWriter writer = null;
        try {
            Date currentDate = new Date();
            //若目标文件不存在，则生成目标文件夹
            String folderName = receiveXmlFilePath +UUID.randomUUID().toString();
            FileHelper.createFilePath(folderName);
            VelocityContext context = new VelocityContext();

            String contentFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(currentDate) + ".xml";
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
            context.put("currentTime", currentTime);
            context.put("contentFileName", contentFileName);
            context.put("number", ++fileNum);
            //生成远程公文交换文件包名:文件包名为UUID
            String envelopeUuid = UUID.randomUUID().toString();
            context.put("envelopeUuid",envelopeUuid);
            String title = new SimpleDateFormat("MM-dd HH:mm:ss").format(currentDate)+"关于公文交换的测试(请勿操作)";
            context.put("title",title);
            //生成封体文件
            Template template = Velocity.getTemplate("receivetemplate/envelopeContentTemplate.vm");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folderName +"/"+ contentFileName), "utf-8"));
            template.merge(context, writer);
            writer.flush();
            writer.close();
            File contentFile = new File(folderName+"/" + contentFileName);
            if(contentFile.exists()){
                context.put("contentFileMd5", MD5Helper.getFileMD5(contentFile));
                logger.debug("md5:"+MD5Helper.getFileMD5(contentFile));
            }
            //生成封首文件
            template = Velocity.getTemplate("receivetemplate/envelopeTemplate.vm");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folderName+"/" + "envelope.xml"), "utf-8"));
            template.merge(context, writer);
            writer.flush();
            logger.debug("生成接收文件夹："+folderName);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
