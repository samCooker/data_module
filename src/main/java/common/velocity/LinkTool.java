package common.velocity;

/**
 * 2017/1/16
 *
 * @author Shicx
 */
public class LinkTool extends org.apache.velocity.tools.view.LinkTool {
    public LinkTool() {
    }

    public String relativeContext() {
        return this.getContextURL() + "/";
    }
}
