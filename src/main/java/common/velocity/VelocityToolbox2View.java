package common.velocity;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.ViewToolContext;
import org.apache.velocity.tools.view.ViewToolManager;
import org.springframework.web.servlet.view.velocity.VelocityView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * 2017/1/16
 *
 * @author Shicx
 */
public class VelocityToolbox2View extends VelocityView {
    private String toolboxConfigLocation;
    private boolean autoConfig = true;
    private boolean includeDefaults = true;
    private ViewToolManager viewToolManager;

    public VelocityToolbox2View() {
    }

    public String getToolboxConfigLocation() {
        return this.toolboxConfigLocation;
    }

    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

    public ViewToolManager getViewToolManager() {
        return this.viewToolManager;
    }

    public void setViewToolManager(ViewToolManager viewToolManager) {
        this.viewToolManager = viewToolManager;
    }

    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViewToolContext velocityContext = null;
        if(this.viewToolManager == null && this.getToolboxConfigLocation() == null && !this.autoConfig) {
            velocityContext = new ViewToolContext(this.getVelocityEngine(), request, response, this.getServletContext());
        } else {
            ViewToolManager i$ = null;
            if(this.viewToolManager != null) {
                i$ = this.viewToolManager;
            } else {
                i$ = VelocityToolbox2Utils.buildViewToolManager(this.getServletContext(), this.toolboxConfigLocation, this.autoConfig, this.includeDefaults);
            }

            i$.setVelocityEngine(this.getVelocityEngine());
            velocityContext = i$.createContext(request, response);
        }

        if(model != null) {
            Iterator i$1 = model.entrySet().iterator();

            while(i$1.hasNext()) {
                Map.Entry entry = (Map.Entry)i$1.next();
                velocityContext.put((String)entry.getKey(), entry.getValue());
            }
        }

        return velocityContext;
    }

    protected void initTool(Object tool, Context velocityContext) throws Exception {
    }

    public boolean isAutoConfig() {
        return this.autoConfig;
    }

    public void setAutoConfig(boolean autoConfig) {
        this.autoConfig = autoConfig;
    }

    public boolean isIncludeDefaults() {
        return this.includeDefaults;
    }

    public void setIncludeDefaults(boolean includeDefaults) {
        this.includeDefaults = includeDefaults;
    }
}
