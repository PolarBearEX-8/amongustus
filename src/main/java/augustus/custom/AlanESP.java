package augustus.custom;

import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import java.awt.Color;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Editable Render module. Every living target receives the Alan portrait at its body dimensions.
 */
public final class AlanESP extends Module implements RenderListener {

    public AlanESP() {
        super("AlanESP", "AlanESP", new Color(91, 184, 255), Category.RENDER);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onPreDisable() {}

    @Override
    public void onRender3D(float partialTicks) {
        if (ClientAccess.enabled(this)) {
            AlanVisuals.entities(partialTicks);
        }
    }

    @Override
    public void onRender2D(float partialTicks, ScaledResolution resolution) {}

    @Override
    public void onRenderOverlay(float partialTicks, ScaledResolution resolution) {}
}
