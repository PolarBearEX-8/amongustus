package augustus.modules.render;

import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import java.awt.Color;
import net.minecraft.client.entity.EntityPlayerSP;

public class LegacyAnimations extends Module {
    public ModeSetting settingHeartAnimationMode = new ModeSetting(
        this, "HeartAnimationMode", "Default", new String[]{"Default", "Legacy", "Minimal"}
    );
    public BooleanSetting settingDamageAnimation = new BooleanSetting(this, "DamageAnimation", true);
    public BooleanSetting settingItemHeld = new BooleanSetting(this, "ItemHeld", false);
    public BooleanSetting settingSwing = new BooleanSetting(this, "Swing", true);
    public BooleanSetting settingDebugHitBox = new BooleanSetting(this, "DebugHitBox", true);
    public BooleanSetting settingRod = new BooleanSetting(this, "Rod", true);
    public static String suffix2;

    public LegacyAnimations() {
        super("LegacyAnimations", "AlteAnimationen", new Color(50, 168, 104), Category.RENDER);
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
        return !this.isToggled() ? false : this.settingHeartAnimationMode.getSelected().equals("Legacy");
    }

    public boolean canExecute6() {
        if (!this.isToggled()) {
            return false;
        } else {
            if (this.settingSwing.isEnabled()) {
                if (minecraft.thePlayer.getHeldItem() == null) {
                    return false;
                }

                if (!minecraft.thePlayer.state2) {
                    return false;
                }

                if (minecraft.thePlayer.canExecute6()) {
                    return false;
                }

                if (!minecraft.thePlayer.isBlocking()) {
                    return true;
                }
            }

            return false;
        }
    }

    public static void setSuffix(String text) {
        suffix2 = text;
    }

    public boolean canExecute7() {
        return !this.isToggled() ? false : this.settingDebugHitBox.isEnabled();
    }

    public boolean canExecute8() {
        return !this.isToggled() ? false : this.settingItemHeld.isEnabled();
    }

    public static String getSuffix2() {
        return suffix2;
    }

    public boolean checkCondition() {
        return !this.isToggled() ? false : this.settingDamageAnimation.isEnabled();
    }

    public boolean checkCondition2() {
        return !this.isToggled() ? false : this.settingHeartAnimationMode.getSelected().equals("Minimal");
    }

    public boolean canExecute9() {
        return !this.isToggled() ? false : this.settingRod.isEnabled();
    }
}
