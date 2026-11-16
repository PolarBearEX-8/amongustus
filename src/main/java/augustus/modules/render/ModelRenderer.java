package augustus.modules.render;

import augustus.custom.RuntimeBridge;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModelRendererMeType;
import client.setting.MultiSelectSetting;
import java.awt.Color;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ModelRenderer extends Module implements TickListener {
    public BooleanSetting settingRenderArmor = new BooleanSetting(this, "RenderArmor", false);
    public MultiSelectSetting settingTargets = new MultiSelectSetting(this, "Targets", ModelRendererMeType.values());
    public boolean expanded2 = false;

    public ModelRenderer() {
        super("ModelRenderer", "ModellZeichner", new Color(255, 234, 140), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        this.expanded2 = false;
    }

    @Override
    public void onPreDisable() {
        GlStateManager.setActiveTexture(GL11.glGenTextures());
        RuntimeBridge.loadCustomModel("amongus.obj", "amongus.mtl");
        this.expanded2 = true;
    }

    @Override
    public void onRunTick() {
        if (this.expanded2) {
            this.expanded2 = false;
            RuntimeBridge.renderCustomModel();
        }
    }

    public boolean checkEntity(Entity entity) {
        if (!this.isToggled()) {
            return false;
        } else if (!(entity instanceof EntityPlayer)) {
            return false;
        } else {
            return entity instanceof EntityPlayerSP && ModelRendererMeType.ME.isSelected() ? true : ModelRendererMeType.PLAYERS.isSelected();
        }
    }
}
