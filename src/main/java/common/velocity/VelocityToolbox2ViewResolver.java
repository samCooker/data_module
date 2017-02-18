package common.velocity;

import org.apache.velocity.tools.view.ViewToolManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityView;

/**
 * 2017/1/16
 *
 * @author Shicx
 */
public class VelocityToolbox2ViewResolver extends AbstractTemplateViewResolver {
    private String dateToolAttribute;
    private String numberToolAttribute;
    private String toolboxConfigLocation;
    private boolean autoConfig = true;
    private boolean includeDefaults = true;
    private ViewToolManager viewToolManager;

    public VelocityToolbox2ViewResolver() {
        this.setViewClass(this.requiredViewClass());
    }

    protected Class<?> requiredViewClass() {
        return VelocityToolbox2View.class;
    }

    public String getDateToolAttribute() {
        return this.dateToolAttribute;
    }

    public void setDateToolAttribute(String dateToolAttribute) {
        this.dateToolAttribute = dateToolAttribute;
    }

    public String getNumberToolAttribute() {
        return this.numberToolAttribute;
    }

    public void setNumberToolAttribute(String numberToolAttribute) {
        this.numberToolAttribute = numberToolAttribute;
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

    protected void initApplicationContext(ApplicationContext context) {
        super.initApplicationContext(context);
        if(this.viewToolManager != null || this.toolboxConfigLocation != null) {
            if(VelocityView.class.equals(this.getViewClass())) {
                this.logger.info("Using " + VelocityToolbox2View.class.getName() + " instead of default VelocityView " + "due to specified toolboxConfigLocation");
                this.setViewClass(VelocityToolbox2View.class);
            } else if(!VelocityToolbox2View.class.isAssignableFrom(this.getViewClass())) {
                throw new IllegalArgumentException("Given view class [" + this.getViewClass().getName() + "] is not of type [" + VelocityToolbox2View.class.getName() + "], which it needs to be in case of a specified toolboxConfigLocation");
            }

            if(this.viewToolManager == null && this.toolboxConfigLocation != null) {
                this.viewToolManager = VelocityToolbox2Utils.buildViewToolManager(this.getServletContext(), this.toolboxConfigLocation, this.autoConfig, this.includeDefaults);
            }
        }

    }

    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        VelocityToolbox2View view = (VelocityToolbox2View)super.buildView(viewName);
        view.setDateToolAttribute(this.dateToolAttribute);
        view.setNumberToolAttribute(this.numberToolAttribute);
        if(this.toolboxConfigLocation != null) {
            view.setToolboxConfigLocation(this.toolboxConfigLocation);
        }

        if(this.viewToolManager != null) {
            view.setViewToolManager(this.viewToolManager);
        }

        view.setAutoConfig(this.autoConfig);
        view.setIncludeDefaults(this.includeDefaults);
        return view;
    }
}
