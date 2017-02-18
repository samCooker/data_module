package common.velocity;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * 2017/1/16
 *
 * @author Shicx
 */
public class VelocityConfigurer extends org.springframework.web.servlet.view.velocity.VelocityConfigurer {
    public VelocityConfigurer() {
    }

    private String trimCommaDelimitedString(String s) {
        String[] strs = StringUtils.commaDelimitedListToStringArray(s);
        strs = StringUtils.trimArrayElements(strs);
        return StringUtils.arrayToCommaDelimitedString(strs);
    }

    public void setResourceLoaderPath(String resourceLoaderPath) {
        super.setResourceLoaderPath(this.trimCommaDelimitedString(resourceLoaderPath));
    }

    protected void initVelocityResourceLoader(VelocityEngine velocityEngine, String resourceLoaderPath) {
        if(this.isPreferFileSystemAccess()) {
            StringBuilder fileResolvedPath = new StringBuilder();
            StringBuilder springResourceResolvedPath = new StringBuilder();
            String[] paths = StringUtils.commaDelimitedListToStringArray(resourceLoaderPath);

            for(int rl = 0; rl < paths.length; ++rl) {
                String path = StringUtils.trimWhitespace(paths[rl]);
                if(!path.startsWith("classpath:")) {
                    Resource resource = this.getResourceLoader().getResource(path);

                    try {
                        File ex = resource.getFile();
                        if(this.logger.isDebugEnabled()) {
                            this.logger.debug("Resource loader path [" + path + "] resolved to file [" + ex.getAbsolutePath() + "]");
                        }

                        if(fileResolvedPath.length() > 0) {
                            fileResolvedPath.append(",");
                        }

                        fileResolvedPath.append(ex.getAbsolutePath());
                    } catch (IOException var10) {
                        if(this.logger.isDebugEnabled()) {
                            this.logger.debug("Cannot resolve resource loader path [" + resourceLoaderPath + "] to [java.io.File]: using SpringResourceLoader", var10);
                        }

                        if(springResourceResolvedPath.length() > 0) {
                            springResourceResolvedPath.append(",");
                        }

                        springResourceResolvedPath.append(path);
                    }
                } else {
                    if(this.logger.isDebugEnabled()) {
                        this.logger.debug("Resource loader path [" + path + "] resolved to classpath using SpringResourceLoader");
                    }

                    if(springResourceResolvedPath.length() > 0) {
                        springResourceResolvedPath.append(",");
                    }

                    springResourceResolvedPath.append(path);
                }
            }

            if(springResourceResolvedPath.length() > 0) {
                this.initSpringResourceLoader(velocityEngine, springResourceResolvedPath.toString());
            }

            if(fileResolvedPath.length() > 0) {
                String var11 = (String)velocityEngine.getProperty("resource.loader");
                if(StringUtils.hasText(var11)) {
                    var11 = "file," + var11;
                } else {
                    var11 = "file";
                }

                velocityEngine.setProperty("resource.loader", var11);
                velocityEngine.setProperty("file.resource.loader.cache", "true");
                velocityEngine.setProperty("file.resource.loader.path", fileResolvedPath.toString());
            }
        } else {
            super.initVelocityResourceLoader(velocityEngine, resourceLoaderPath);
        }

    }
}