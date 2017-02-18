package common.velocity;

import cn.com.chaochuang.velocity.toolbox2.VelocityLayout2View;
import cn.com.chaochuang.velocity.toolbox2.VelocityToolbox2ViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

/**
 * 2017/1/16
 *
 * @author Shicx
 */
public class VelocityLayout2ViewResolver extends VelocityToolbox2ViewResolver {
    private String layoutUrl;
    private String layoutKey;
    private String screenContentKey;

    public VelocityLayout2ViewResolver() {
    }

    protected Class<?> requiredViewClass() {
        return VelocityLayout2View.class;
    }

    public void setLayoutUrl(String layoutUrl) {
        this.layoutUrl = layoutUrl;
    }

    public void setLayoutKey(String layoutKey) {
        this.layoutKey = layoutKey;
    }

    public void setScreenContentKey(String screenContentKey) {
        this.screenContentKey = screenContentKey;
    }

    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        VelocityLayout2View view = (VelocityLayout2View)super.buildView(viewName);
        if(this.layoutUrl != null) {
            view.setLayoutUrl(this.layoutUrl);
        }

        if(this.layoutKey != null) {
            view.setLayoutKey(this.layoutKey);
        }

        if(this.screenContentKey != null) {
            view.setScreenContentKey(this.screenContentKey);
        }

        return view;
    }
}

