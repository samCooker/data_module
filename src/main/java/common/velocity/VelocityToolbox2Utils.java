package common.velocity;

import org.apache.velocity.tools.config.ConfigurationUtils;
import org.apache.velocity.tools.config.FactoryConfiguration;
import org.apache.velocity.tools.config.XmlFactoryConfiguration;
import org.apache.velocity.tools.view.ViewToolManager;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;

/**
 * 2017/1/16
 *
 * @author Shicx
 */
public abstract class VelocityToolbox2Utils {
    public VelocityToolbox2Utils() {
    }

    public static FactoryConfiguration getDefaultTools() {
        XmlFactoryConfiguration config = new XmlFactoryConfiguration("ConfigurationUtils.getDefaultTools()");
        config.read("/org/apache/velocity/tools/generic/tools.xml");
        config.read("/org/apache/velocity/tools/view/tools.xml", false);
        ConfigurationUtils.clean(config);
        return config;
    }

    public static FactoryConfiguration getAutoLoaded(boolean includeDefaults) {
        FactoryConfiguration auto;
        if(includeDefaults) {
            auto = getDefaultTools();
        } else {
            auto = new FactoryConfiguration("ConfigurationUtils.getAutoLoaded(false)");
        }

        FactoryConfiguration cpXml = ConfigurationUtils.findInClasspath("tools.xml");
        if(cpXml != null) {
            auto.addConfiguration(cpXml);
        }

        FactoryConfiguration cpProps = ConfigurationUtils.findInClasspath("tools.properties");
        if(cpProps != null) {
            auto.addConfiguration(cpProps);
        }

        FactoryConfiguration fsXml = ConfigurationUtils.findInFileSystem("tools.xml");
        if(fsXml != null) {
            auto.addConfiguration(fsXml);
        }

        FactoryConfiguration fsProps = ConfigurationUtils.findInFileSystem("tools.properties");
        if(fsProps != null) {
            auto.addConfiguration(fsProps);
        }

        return auto;
    }

    public static void configureToolManager(ViewToolManager velocityToolManager, String toolboxConfigLocation, boolean autoConfig, boolean includeDefaults) {
        if(autoConfig) {
            FactoryConfiguration config = getAutoLoaded(includeDefaults);
            velocityToolManager.configure(config);
        }

        if(StringUtils.hasText(toolboxConfigLocation)) {
            velocityToolManager.configure(toolboxConfigLocation);
        }

    }

    public static ViewToolManager buildViewToolManager(ServletContext app, String toolboxConfigLocation, boolean autoConfig, boolean includeDefaults) {
        ViewToolManager viewToolManager = new ViewToolManager(app, false, false);
        configureToolManager(viewToolManager, toolboxConfigLocation, autoConfig, includeDefaults);
        return viewToolManager;
    }
}

