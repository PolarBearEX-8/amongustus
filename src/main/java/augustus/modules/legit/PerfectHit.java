package augustus.modules.legit;

import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.util.AnimationTimer;
import client.util.MovingObjectType;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.entity.EntityLivingBase;

public class PerfectHit extends Module implements ClickListener {
    public NumberSetting settingMaxDelaySinceLastHit;
    public RangeSetting settingCPS;
    public RangeSetting settingCPSRecalculateDelay;
    public RangeSetting settingDCPS;
    public RangeSetting settingDCPSRecalculateDelay;
    public BooleanSetting settingReduceCPSIfYouCanNotHitATarget;
    public BooleanSetting settingForcePerfectHitClicks;
    public TimeHelper timer;
    public SmoothDamper smoothDamper;
    public SmoothDamper smoothDamper2;
    public AnimationTimer animationTimer;
    public AnimationTimer animationTimer2;

    public PerfectHit() {
        super("PerfectHit", "PerfekterSchlag", new Color(240, 115, 186), Category.LEGIT);
        this.settingMaxDelaySinceLastHit = new NumberSetting(this, "MaxDelaySinceLastHit", 300.0, 0.0, 1000.0, 0);
        this.settingCPS = new RangeSetting(this, "CPS", 20.0, 20.0, 0.0, 20.0, 0);
        this.settingCPSRecalculateDelay = new RangeSetting(this, "CPSRecalculateDelay", 3.0, 8.0, 0.0, 60.0, 0)
            .getRangeSetting2();
        this.settingDCPS = new RangeSetting(this, "DCPS", 0.0, 0.0, 0.0, 20.0, 0);
        this.settingDCPSRecalculateDelay = new RangeSetting(this, "DCPSRecalculateDelay", 3.0, 8.0, 0.0, 60.0, 0)
            .getRangeSetting2();
        this.settingReduceCPSIfYouCanNotHitATarget = new BooleanSetting(this, "ReduceCPSIfYouCanNotHitATarget", true);
        this.settingForcePerfectHitClicks = new BooleanSetting(this, "ForcePerfectHitClicks", false);
        this.timer = new TimeHelper();
        this.smoothDamper = new SmoothDamper(20, 0.0);
        this.smoothDamper2 = new SmoothDamper(20, 0.0);
        this.animationTimer = new AnimationTimer();
        this.animationTimer2 = new AnimationTimer();
    }

    @Override
    public void onEnable() {
        this.registerEvent(7, 99999);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onClick(EventClick event) {
        if (minecraft.thePlayer != null
            && !minecraft.thePlayer.isUsingItem()
            && (minecraft.gameSettings.keyBindAttack.getPressTime() > 0 || event.getIndexOffset() > 0)) {
            this.timer.reset();
        }

        if (this.canExecute5()
            && (
                event.getIndexOffset() <= 0 && (minecraft.gameSettings.keyBindAttack.getPressTime() <= 0 || event.isState3())
                    || this.settingForcePerfectHitClicks.isEnabled()
            )) {
            if (this.settingForcePerfectHitClicks.isEnabled()) {
                event.updateState();

                while (minecraft.gameSettings.keyBindAttack.isPressed()) {
                }
            }

            int indexOffset2 = this.settingCPSRecalculateDelay.getIndexOffset2();
            if (this.animationTimer.checkCondition(indexOffset2)) {
                this.animationTimer.processAction3();
                this.settingCPSRecalculateDelay.getDouble4();
                this.settingCPS.getDouble4();
            }

            int indexOffset22 = this.settingDCPSRecalculateDelay.getIndexOffset2();
            if (this.animationTimer2.checkCondition(indexOffset22)) {
                this.animationTimer2.processAction3();
                this.settingDCPSRecalculateDelay.getDouble4();
                this.settingDCPS.getDouble4();
            }

            int indexOffset23 = this.settingCPS.getIndexOffset2();
            int int2 = this.getInt2(indexOffset23);
            int index;
            if ((index = this.smoothDamper.getInt(int2)) > 0) {
                int indexOffset24 = this.settingDCPS.getIndexOffset2();
                int int22 = this.getInt2(indexOffset24);
                SmoothDamper smoothDamper22 = this.smoothDamper2;
                index += smoothDamper22.getInt(int22);
            }

            event.updateInt(index);
        }

        this.animationTimer.processAction();
        this.animationTimer2.processAction();
    }

    public boolean canExecute5() {
        if (!ClientGlobals.moduleManager.perfectHit.isToggled()) {
            return false;
        } else if (!minecraft.isIntegratedServerRunning()) {
            return false;
        } else {
            if (!this.timer.hasReachedDouble(this.settingMaxDelaySinceLastHit.getValue())) {
                if (minecraft.objectMouseOver == null) {
                    return false;
                }

                if (minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
                    return false;
                }

                if (!(minecraft.objectMouseOver.entityHit instanceof EntityLivingBase)) {
                    return false;
                }

                if (((EntityLivingBase)minecraft.objectMouseOver.entityHit).hurtTime <= 1) {
                    return true;
                }
            }

            return false;
        }
    }

    private boolean checkCondition() {
        return this.settingReduceCPSIfYouCanNotHitATarget.isEnabled();
    }

    private int getInt2(int index) {
        return (this.checkCondition() ? 1 : 0);
    }
}
