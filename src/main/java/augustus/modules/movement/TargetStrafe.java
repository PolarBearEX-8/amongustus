package augustus.modules.movement;

import client.Client;
import client.event.EventMove;
import client.event.EventRotation;
import client.event.MoveListener;
import client.event.RotationListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.TimeHelper;
import client.via.ViaProtocolHelper;
import java.awt.Color;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class TargetStrafe extends Module implements RotationListener, MoveListener {
    public ModeSetting settingConditionsToTargetStrafe;
    public NumberSetting settingRadius;
    public BooleanSetting settingKillAuraTarget;
    public BooleanSetting settingAutoStrafe;
    public BooleanSetting settingOnlyWhileSpace;
    public BooleanSetting settingThirdPersonMode;
    public TimeHelper timer;
    public float motionMultiplier;
    public EntityLivingBase targetEntity;
    public Object[] objectArray;
    public boolean enabled2;
    public boolean expanded2;
    public int tickDelay;

    public TargetStrafe() {
        super("TargetStrafe", "Zielbeschuss", new Color(52, 0, 194), Category.MOVEMENT);
        String[] textArray = new String[4];
        textArray[0] = "Everytime";
        textArray[1] = "OnlyWhileFlyEnabled";
        textArray[2] = "OnlyWhileSpeedEnabled";
        textArray[3] = "OnlyWhileSpeedOrFlyEnabled";
        this.settingConditionsToTargetStrafe = new ModeSetting(this, "ConditionsToTargetStrafe", "Everytime", textArray);
        this.settingRadius = new NumberSetting(this, "Radius", 2.5, 0.1, 6.0, 1);
        this.settingKillAuraTarget = new BooleanSetting(this, "KillAuraTarget", true);
        this.settingAutoStrafe = new BooleanSetting(this, "AutoStrafe", true);
        this.settingOnlyWhileSpace = new BooleanSetting(this, "OnlyWhileSpace", true);
        this.settingThirdPersonMode = new BooleanSetting(this, "ThirdPersonMode", false);
        this.timer = new TimeHelper();
        this.targetEntity = (EntityLivingBase)null;
        this.expanded2 = false;
        this.tickDelay = 0;
    }

    @Override
    public void onEnable() {
        this.registerEvent(28);
        this.registerEvent(21);
    }

    @Override
    public void onDisable() {
        this.objectArray = null;
        this.targetEntity = null;
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRotation(EventRotation event) {
        if (this.targetEntity != null && !BlockCache.canExecute()) {
            event.setScaleFactor(this.motionMultiplier);
        }
    }

    @Override
    public void onPostRotation(EventRotation event) {
        if (this.targetEntity != null && !BlockCache.canExecute()) {
            event.setScaleFactor(this.motionMultiplier);
        }
    }

    @Override
    public void onMove(EventMove event) {
        this.targetEntity = (EntityLivingBase)null;
        this.settingConditionsToTargetStrafe.getSelected().equals("Everytime");
        this.settingConditionsToTargetStrafe.getSelected().equals("OnlyWhileFlyEnabled");
        this.settingConditionsToTargetStrafe.getSelected().equals("OnlyWhileSpeedEnabled");
        this.settingConditionsToTargetStrafe.getSelected().equals("OnlyWhileSpeedOrFlyEnabled");
        if ((minecraft.gameSettings.keyBindJump.isKeyDown() ? 1 : 0) > 0) {
            this.targetEntity = (EntityLivingBase)(this.objectArray[0]);
        }

        this.motionMultiplier = Client.getClient().getViaProtocolHelper().scaleFactor1;
    }

    private static double getCoordinate(Entity entity) {
        return (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
    }

    private boolean checkEntity(Entity entity) {
        if (entity instanceof EntityLivingBase) {
            if (entity != minecraft.thePlayer) {
                float value;
                return (value = minecraft.thePlayer.getDistanceSqToEntity(entity) - 7.0F) == 0.0F ? false : (value < 0.0F ? true : true);
            }
        }

        return false;
    }

    private void updateState() {
        if (this.expanded2) {
            minecraft.gameSettings.indexOffset64 = this.tickDelay;
        }

        this.expanded2 = false;
    }
}
