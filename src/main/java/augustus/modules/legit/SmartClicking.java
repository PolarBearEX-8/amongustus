package augustus.modules.legit;

import augustus.custom.RuntimeBridge;
import augustus.modes.movement.StepLagMode;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.MotionListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.util.ArrayListData;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.MovingObjectType;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class SmartClicking extends Module implements ClickListener, MotionListener {
    public RangeSetting settingSmartHurttimeStart;
    public RangeSetting settingSmartHurttimeEnd;
    public RangeSetting settingMaxDuration;
    public NumberSetting settingChance;
    public BooleanSetting settingSmartHurttime;
    public BooleanSetting settingReduceClickingInAir;
    public BooleanSetting settingFakeAttacks;
    public BooleanSetting settingRatioBypass;
    public NumberSetting settingRatioStrength;
    public boolean activeState2;
    public boolean activeState3;
    public boolean enabled2;
    public EntityLivingBase targetEntity;
    public TimeHelper timer;
    public boolean expanded2;
    public int tickDelay1;
    public int keyCode2;
    public boolean visible2;
    public long timestampMillis4;
    public boolean activeState;
    public long timestampMillis;
    public int tickDelay;
    public ArrayListData tertiaryArrayListEntry;
    public static int[] intValueArray;

    public SmartClicking() {
        super("SmartClicking", "IntelligentesClicken", new Color(235, 125, 52), Category.LEGIT);
        this.settingSmartHurttimeStart = new RangeSetting(this, "Smart hurttime start", 2.0, 3.0, 0.0, 10.0, 0);
        this.settingSmartHurttimeEnd = new RangeSetting(this, "Smart hurttime end", 9.0, 10.0, 0.0, 10.0, 0);
        this.settingMaxDuration = new RangeSetting(this, "MaxDuration", 10.0, 10.0, 0.0, 10.0, 0)
            .getRangeSetting2();
        this.settingChance = new NumberSetting(this, "Chance", 100.0, 0.0, 100.0, 0);
        this.settingSmartHurttime = new BooleanSetting(this, "SmartHurttime", false);
        this.settingReduceClickingInAir = new BooleanSetting(this, "ReduceClickingInAir", false);
        this.settingFakeAttacks = new BooleanSetting(this, "FakeAttacks", false);
        this.settingRatioBypass = (BooleanSetting)new BooleanSetting(this, "RatioBypass", false).getSetting(() -> this.isChanceVisible());
        this.settingRatioStrength = new NumberSetting(this, "RatioStrength", 1.0, 0.0, 1.0, 2).getNumberSetting(() -> this.isRatioStrengthVisible());
        this.activeState2 = false;
        this.activeState3 = false;
        this.enabled2 = false;
        this.targetEntity = (EntityLivingBase)null;
        this.timer = new TimeHelper();
        this.expanded2 = false;
        this.tickDelay1 = 0;
        this.keyCode2 = 0;
        this.visible2 = true;
        this.tertiaryArrayListEntry = new ArrayListData(40);
    }

    @Override
    public void onEnable() {
        this.registerEvent(7, 100000);
        this.registerEvent(34, -1);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.activeState2 = false;
        this.enabled2 = false;
        this.targetEntity = null;
    }

    @Override
    public void onClick(EventClick event) {
        event.isState();
        event.isState3();
        event.isState3();
        this.keyCode2++;
        if ((this.canExecute5() ? 1 : 0) > 0 && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
            this.timestampMillis4 = System.currentTimeMillis();
            this.tickDelay = minecraft.objectMouseOver.entityHit.hurtResistantTime;
            this.activeState = false;
        }
    }

    @Override
    public void onPostMotion() {
        MovingObjectPosition hitResult;
        EntityLivingBase livingEntity;
        if (this.targetEntity != null) {
            if (this.timer.hasReached(1500L)) {
                this.targetEntity = null;
            }

            this.activeState3 = this.activeState2;
            this.activeState2 = false;
            this.enabled2 = false;
            hitResult = minecraft.objectMouseOver;
            livingEntity = ChatUtil.getEntityLivingBase2(
                hitResult != null && hitResult.entityHit instanceof EntityLivingBase ? (EntityLivingBase)hitResult.entityHit : null
            );
        } else {
            this.activeState3 = this.activeState2;
            this.activeState2 = false;
            this.enabled2 = false;
            hitResult = minecraft.objectMouseOver;
            livingEntity = ChatUtil.getEntityLivingBase2(
                hitResult != null && hitResult.entityHit instanceof EntityLivingBase ? (EntityLivingBase)hitResult.entityHit : null
            );
        }

        if (livingEntity != null) {
            this.targetEntity = livingEntity;
            this.timer.reset();
        }

        if (this.targetEntity != null && this.targetEntity.hurtTime == 10) {
            this.visible2 = Math.random() <= this.settingChance.getValue() / 100.0;
        }

        if (hitResult != null
            && (hitResult.typeOfHit == MovingObjectType.ENTITY || this.settingReduceClickingInAir.isEnabled())
            && hitResult.entityHit instanceof EntityLivingBase) {
            if (this.targetEntity != null && this.targetEntity.hurtTime == 10) {
                this.settingSmartHurttimeStart.recalculate();
                this.settingSmartHurttimeEnd.recalculate();
            }

            if (this.visible2) {
                if (this.targetEntity != null) {
                    boolean enabled = this.targetEntity.hurtTime > this.settingSmartHurttimeStart.getIndexOffset2()
                        && this.targetEntity.hurtTime <= this.settingSmartHurttimeEnd.getIndexOffset2();
                    boolean enabled3 = RuntimeBridge.raytraceEntity(this.targetEntity, minecraft.thePlayer.rotationYaw);
                    if (this.settingSmartHurttime.isEnabled()) {
                        if (System.currentTimeMillis() - this.timestampMillis4 > 600L && this.tickDelay > 4) {
                            this.activeState = true;
                        }

                        long time2;
                        if (!enabled
                            && this.targetEntity.hurtTime > 0
                            && enabled3
                            && !this.activeState
                            && ((time2 = System.currentTimeMillis() - this.timestampMillis - 500L) == 0L ? 0 : (time2 < 0L ? -1 : 1)) < 0) {
                            enabled = true;
                        }
                    }

                    if (enabled && enabled3) {
                        this.activeState2 = true;
                    }

                    if (!ClientGlobals.moduleManager.criticals.isToggled()
                        || !ClientGlobals.moduleManager.criticals.settingMode.checkClass(StepLagMode.class)) {
                        this.expanded2 = false;
                    } else if (StepLagMode.canExecute() && this.tickDelay1 == 0) {
                        this.activeState2 = false;
                        this.expanded2 = true;
                    } else {
                        this.expanded2 = false;
                        this.tickDelay1 = 0;
                    }
                }
            }
        } else {
            this.enabled2 = false;
        }
    }

    public static int[] getIntValueArray() {
        return intValueArray;
    }

    private boolean isChanceVisible() {
        return this.settingFakeAttacks.isEnabled();
    }

    private boolean isRatioStrengthVisible() {
        return this.settingRatioBypass.isEnabled();
    }

    public static void setIntValueArray(int[] countArray) {
        intValueArray = countArray;
    }

    public boolean canExecute5() {
        return this.isToggled();
    }
}
