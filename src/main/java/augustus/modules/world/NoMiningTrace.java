package augustus.modules.world;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.TargetFilterSetting;
import java.awt.Color;
import net.minecraft.item.ItemGoldenPickaxe;

public class NoMiningTrace extends Module {
    public BooleanSetting settingAlwaysAtRightClicking = new BooleanSetting(this, "AlwaysAtRightClicking", true);
    public BooleanSetting settingAlwaysAfterBlockBreakStarted = new BooleanSetting(this, "AlwaysAfterBlockBreakStarted", true);
    public BooleanSetting settingOnlyPickaxesWhenLeftclicking = new BooleanSetting(this, "OnlyPickaxes when leftclicking", false);
    public TargetFilterSetting settingExcludedWeapons = new TargetFilterSetting(this, "Excluded weapons");

    public NoMiningTrace() {
        super("NoMiningTrace", "KeineBergbauspuren", new Color(66, 135, 245), Category.WORLD);
        this.settingExcludedWeapons.applyString3("Rest", false);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    public boolean canExecute5() {
        if (!this.isToggled()) {
            return false;
        } else {
            if (this.settingOnlyPickaxesWhenLeftclicking.isEnabled()) {
                if (minecraft.thePlayer.getHeldItem() == null) {
                    return false;
                }

                if (!(minecraft.thePlayer.getHeldItem().getItem() instanceof ItemGoldenPickaxe)) {
                    return false;
                }
            }

            if (!this.settingExcludedWeapons.checkItemStack(minecraft.thePlayer.getHeldItem())) {
                return true;
            } else {
                return !minecraft.playerController.state ? false : this.settingAlwaysAfterBlockBreakStarted.isEnabled();
            }
        }
    }
}
