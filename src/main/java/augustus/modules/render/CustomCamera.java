package augustus.modules.render;

import client.event.EventStrafe;
import client.event.StrafeListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.NumberSetting;
import java.awt.Color;
import net.minecraft.util.Vec3;

public class CustomCamera extends Module implements StrafeListener, TickListener {
    public NumberSetting settingCustomHeight = new NumberSetting(this, "CustomHeight", 0.0, -5.0, 10.0, 2);
    public NumberSetting settingCustomHeightF5 = new NumberSetting(this, "CustomHeightF5", 0.0, -5.0, 10.0, 2);
    public NumberSetting settingCustomX = new NumberSetting(this, "CustomX", 0.0, -5.0, 5.0, 2);
    public NumberSetting settingCustomXF5 = new NumberSetting(this, "CustomXF5", 0.0, -5.0, 5.0, 2);
    public NumberSetting settingCustomZ = new NumberSetting(this, "CustomZ", 0.0, -5.0, 5.0, 2);
    public NumberSetting settingCustomZF5 = new NumberSetting(this, "CustomZF5", 0.0, -5.0, 5.0, 2);
    public NumberSetting settingCustomThirdPersonDistance = new NumberSetting(this, "CustomThirdPersonDistance", 4.0, 0.0, 30.0, 1);

    public CustomCamera() {
        super("CustomCamera", "EinstellbareKamera", new Color(105, 66, 245), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(24, 11);
        this.registerEvent(40);
    }

    @Override
    public void onDisable() {
        if (minecraft.entityRenderer != null) {
            minecraft.entityRenderer.scaleFactor = 4.0F;
        }
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onStrafe(EventStrafe event) {
        if (minecraft.gameSettings.indexOffset64 == 1) {
            event.processAction3((float)((double)event.getScaleFactor() + this.settingCustomHeightF5.getValue()));
            event.setPositionVec(new Vec3(this.settingCustomXF5.getValue(), 0.0, this.settingCustomZF5.getValue()));
        } else {
            if (minecraft.gameSettings.indexOffset64 == 0) {
                event.processAction3((float)((double)event.getScaleFactor() + this.settingCustomHeight.getValue()));
                event.setPositionVec(new Vec3(this.settingCustomX.getValue(), 0.0, this.settingCustomZ.getValue()));
            }
        }
    }

    @Override
    public void onRunTick() {
        minecraft.entityRenderer.scaleFactor = (float)this.settingCustomThirdPersonDistance.getValue();
    }
}
