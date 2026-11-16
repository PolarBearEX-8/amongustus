package augustus.modules.render;

import client.Client;
import client.ClientGlobals;
import client.event.EventSilentMove;
import client.event.EventStrafe;
import client.event.SilentMoveListener;
import client.event.StrafeListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import java.awt.Color;
import net.minecraft.util.MathHelper;

public class Freelook extends Module implements StrafeListener, SilentMoveListener {
    public BooleanSetting settingThirdPerson = new BooleanSetting(this, "ThirdPerson", true);
    public BooleanSetting settingDisableItInFistPerson = new BooleanSetting(this, "DisableItInFistPerson", true);
    public float motionMultiplier;
    public float motionMultiplier1;
    public int keyCode2;

    public Freelook() {
        super("Freelook", "FreiesSchauen", new Color(152, 245, 167), Category.RENDER);
    }

    @Override
    public void onEnable() {
        this.registerEvent(24);
        this.registerEvent(19);
    }

    @Override
    public void onDisable() {
        if (this.settingThirdPerson.isEnabled()) {
            minecraft.gameSettings.indexOffset64 = this.keyCode2;
        }
    }

    @Override
    public void onPreDisable() {
        if (minecraft.thePlayer != null) {
            this.motionMultiplier = Client.getClient().getViaProtocolHelper().scaleFactor1;
            this.motionMultiplier1 = Client.getClient().getViaProtocolHelper().scaleFactor2;
            this.keyCode2 = minecraft.gameSettings.indexOffset64;
            if (this.settingThirdPerson.isEnabled()) {
                minecraft.gameSettings.indexOffset64 = 1;
            }
        }
    }

    @Override
    public void onStrafe(EventStrafe event) {
        if (minecraft.gameSettings.indexOffset64 == 0 && this.settingDisableItInFistPerson.isEnabled()) {
            this.motionMultiplier = Client.getClient().getViaProtocolHelper().scaleFactor1;
            this.motionMultiplier1 = Client.getClient().getViaProtocolHelper().scaleFactor2;
        } else {
            event.processAction2(this.motionMultiplier);
            event.processAction(this.motionMultiplier1);
            event.setActive(true);
        }
    }

    @Override
    public void onSilentMove(EventSilentMove event) {
        if (minecraft.gameSettings.indexOffset64 != 0 || !this.settingDisableItInFistPerson.isEnabled()) {
            if (!ClientGlobals.moduleManager.aimAssist.isToggled() && !ClientGlobals.moduleManager.aimBot.isToggled()) {
                event.processAction(0);
                event.processAction2(0);
            }

            float value = minecraft.gameSettings.scaleFactor * 0.6F + 0.2F;
            float value2 = value * value * value * 8.0F;
            float value3 = (float)minecraft.mouseHelper.indexOffset * value2;
            float value4 = (float)minecraft.mouseHelper.indexOffset1 * value2;
            if (minecraft.gameSettings.state20) {
                value4 *= -1.0F;
            }

            this.motionMultiplier = (float)((double)this.motionMultiplier + (double)value3 * 0.15);
            this.motionMultiplier1 = (float)((double)this.motionMultiplier1 - (double)value4 * 0.15);
            this.motionMultiplier1 = MathHelper.clamp_float(this.motionMultiplier1, -90.0F, 90.0F);
        }
    }
}
