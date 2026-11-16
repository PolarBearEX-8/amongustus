package augustus.modules.movement;

import client.ClientGlobals;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import java.awt.Color;

public class NoJumpDelay extends Module implements UpdateStateListener {
    public BooleanSetting settingWhileScaffold = new BooleanSetting(this, "WhileScaffold", true);
    public NumberSetting settingChance = new NumberSetting(this, "Chance", 100.0, 0.0, 100.0, 0);

    public NoJumpDelay() {
        super("NoJumpDelay", "KeineSprungVerzögerung", new Color(235, 64, 52), Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        this.registerEvent(43);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onPostUpdateState() {
        if ((ClientGlobals.moduleManager.scaffold.isToggled() ? 1 : 0) <= 0) {
            minecraft.thePlayer.setIndexOffset18(0);
        }
    }
}
