/*
 * FileName:    UploadFileController.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年07月01日 (Shicx) 1.0 Create
 */

package dbdata.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Shicx
 */
@Controller
@RequestMapping("file")
public class UploadFileController {

    private Logger logger = Logger.getLogger("schedule.task.logger");

    /**文件上传根目录*/
    @Value("${file.upload.root.path}")
    private String uploadRootPath;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping("upload.action")
    @ResponseBody
    public String uploadFile(MultipartFile file) throws IOException {
        if(file==null){
            return "文件为空";
        }
        logger.debug("开始上传文件："+file.getOriginalFilename());
        if(file.isEmpty()){
            return "文件为空，上传失败。";
        }else{
            File rootFolder = new File(uploadRootPath);
            if(!rootFolder.exists()){
                rootFolder.mkdirs();
            }
            File targetFile = new File(uploadRootPath+File.separatorChar+file.getOriginalFilename());
            file.transferTo(targetFile);
            return "success";
        }
    }
}

