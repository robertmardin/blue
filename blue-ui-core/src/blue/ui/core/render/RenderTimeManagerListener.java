/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blue.ui.core.render;

/**
 *
 * @author stevenyi
 */
public interface RenderTimeManagerListener {
    public void renderInitiated();
    public void renderEnded();
    public void renderTimeUpdated(float timePointer);
}
