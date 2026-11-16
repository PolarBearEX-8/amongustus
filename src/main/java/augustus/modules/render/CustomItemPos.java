package augustus.modules.render;

import client.event.EventBoundingBox;
import client.event.EventBoundingBoxCallback;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import java.awt.Color;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;

public class CustomItemPos extends Module implements EventBoundingBoxCallback {
    public NumberSetting settingX = new NumberSetting(this, "X", 0.13, -1.0, 1.0, 2);
    public NumberSetting settingY = new NumberSetting(this, "Y", 0.13, -1.0, 1.0, 2);
    public NumberSetting settingZ = new NumberSetting(this, "Z", 0.04, -1.0, 1.0, 2);
    public NumberSetting settingBlockX = new NumberSetting(this, "BlockX", -0.27, -1.0, 1.0, 2);
    public NumberSetting settingBlockY = new NumberSetting(this, "BlockY", -0.04, -1.0, 1.0, 2);
    public NumberSetting settingBlockZ = new NumberSetting(this, "BlockZ", 0.0, -1.0, 1.0, 2);
    public NumberSetting settingScale = new NumberSetting(this, "Scale", 0.2, 0.0, 2.0, 2);
    public BooleanSetting settingLeftHand = new BooleanSetting(this, "LeftHand", false);

    public CustomItemPos() {
        super("CustomItemPos", "KundenspeziefischeObjektPos", new Color(42, 145, 95), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(13);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onBoundingBox(EventBoundingBox event) {
        ItemStack itemStack4 = minecraft.thePlayer.inventoryPlayer.getCurrentItem();
        if (itemStack4 == null || !(itemStack4.getItem() instanceof ItemMapBase)) {
            event.processAction5(this.settingX.getValue());
            event.processAction7(this.settingY.getValue());
            event.processAction2(this.settingZ.getValue());
            event.processAction4(this.settingBlockX.getValue());
            event.processAction6(this.settingBlockY.getValue());
            event.processAction(this.settingBlockZ.getValue());
            event.processAction3(this.settingScale.getValue());
        }
    }
}
