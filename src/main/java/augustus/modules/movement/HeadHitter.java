package augustus.modules.movement;

import client.ClientGlobals;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.UpdateStateListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.util.MoveUtil;
import java.awt.Color;

public class HeadHitter extends Module implements MoveListener, UpdateStateListener {
    public BooleanSetting settingOnlyWhileMoving = new BooleanSetting(this, "OnlyWhileMoving", true);
    public BooleanSetting settingOnlyWhileJumping = new BooleanSetting(this, "OnlyWhileJumping", true);
    public BooleanSetting settingNotWhileSneaking = new BooleanSetting(this, "NotWhileSneaking", true);
    public BooleanSetting settingNotWhileScaffold = new BooleanSetting(this, "NotWhileScaffold", false);
    public BooleanSetting settingInInventory = new BooleanSetting(this, "InInventory", false);

    public HeadHitter() {
        super("HeadHitter", "KopfStoßer", new Color(230, 250, 175), Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        this.registerEvent(21);
        this.registerEvent(43);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onMove(EventMove event) {
        event.setState(this.canExecute5());
    }

    @Override
    public void onPostUpdateState() {
        if (this.canExecute5()) {
            minecraft.thePlayer.setIndexOffset18(0);
        }
    }

    private boolean canExecute5() {
        if (minecraft.currentScreen != null && !this.settingInInventory.isEnabled()) {
            return false;
        } else if (ClientGlobals.moduleManager.scaffold.isToggled() && this.settingNotWhileScaffold.isEnabled()) {
            return false;
        } else if (minecraft.gameSettings.keyBindSneak.isKeyDown() && this.settingNotWhileSneaking.isEnabled()) {
            return false;
        } else if (!minecraft.gameSettings.keyBindJump.isKeyDown() && this.settingOnlyWhileJumping.isEnabled()) {
            return false;
        } else {
            return !MoveUtil.canExecute() && this.settingOnlyWhileMoving.isEnabled()
                ? false
                : minecraft.theWorld
                    .getIBlockState2(minecraft.thePlayer.posX, minecraft.thePlayer.coordOffset9 + 2.0, minecraft.thePlayer.posZ)
                    .getBlock()
                    .isState18();
        }
    }
}
