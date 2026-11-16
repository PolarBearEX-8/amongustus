package augustus.modules.render;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.util.MouseUtils;
import client.util.MovingObjectType;
import java.awt.Color;
import net.minecraft.client.Minecraft;

public class MoreSwing extends Module implements ClickListener {
    public BooleanSetting settingWhileKillAura = new BooleanSetting(this, "WhileKillAura", true);
    public BooleanSetting settingWhileAutoClicker = new BooleanSetting(this, "WhileAutoClicker", true);
    public BooleanSetting settingWhileLeftClick = new BooleanSetting(this, "WhileLeftClick", false);

    public MoreSwing() {
        super("MoreSwing", "MehrSchwingen", new Color(50, 168, 164), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(7, 1000000);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onClick(EventClick event) {
        if (this.canExecute5()) {
            if (minecraft.thePlayer.state2) {
                if (minecraft.thePlayer.indexOffset20 < minecraft.thePlayer.getInt3() / 2) {
                    if (minecraft.thePlayer.indexOffset20 >= 0) {
                        return;
                    }
                }
            }

            minecraft.thePlayer.indexOffset20 = -1;
            minecraft.thePlayer.state2 = true;
        }
    }

    private boolean canExecute5() {
        if (!minecraft.isIntegratedServerRunning()) {
            return false;
        } else if (minecraft.thePlayer.isBlocking()) {
            return false;
        } else {
            if (this.settingWhileKillAura.isEnabled()
                && ClientGlobals.moduleManager.killAura.isToggled()
                && !ClientGlobals.moduleManager.killAura.settingNoSwing.isEnabled()
                && ClientGlobals.moduleManager.killAura.target != null) {
                double offset;
                if ((double)minecraft.thePlayer.getDistanceToEntity(ClientGlobals.moduleManager.killAura.target)
                        < ClientGlobals.moduleManager.killAura.settingPreAimRange.getDouble3()
                    && (
                            (
                                        offset = (double)minecraft.thePlayer.getDistanceToEntity(ClientGlobals.moduleManager.killAura.target)
                                            - ClientGlobals.moduleManager.killAura.settingPreHitRange.getDouble3()
                                    )
                                    == 0.0
                                ? 0
                                : (offset < 0.0 ? -1 : 1)
                        )
                        < 0
                    && ClientGlobals.moduleManager.killAura.settingPreHit.isEnabled()) {
                    return true;
                }

                if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
                    return true;
                }
            }

            if (this.settingWhileAutoClicker.isEnabled() && ClientGlobals.moduleManager.autoClicker.isToggled()) {
                if (!ClientGlobals.moduleManager.autoClicker.settingOnlyOnClick.isEnabled()) {
                    return true;
                }

                if (MouseUtils.checkCondition(0)) {
                    return true;
                }
            }

            return !this.settingWhileLeftClick.isEnabled() ? false : MouseUtils.checkCondition(0);
        }
    }
}
