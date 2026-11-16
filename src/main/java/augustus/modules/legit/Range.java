package augustus.modules.legit;

import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventWorldLoad;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.util.BooleanSupplier;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import java.awt.Color;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class Range extends Module implements WorldLoadListener, TickListener, AttackEntityListener {
    public ModeSetting settingRangeMode = new ModeSetting(this, "RangeMode", "Default", new String[]{"Default", "Dual"});
    public NumberSetting settingRangeInternal;
    public ActionSetting settingBaseRange;
    public NumberSetting settingChanceOfExtendedRange;
    public BooleanSetting settingOnlyWhileSprinting;
    public BooleanSetting settingThroughWalls;
    public NumberSetting settingBuildRange;
    public double reachDistance;
    public boolean expanded2;
    public EntityLivingBase targetEntity;
    public TimeHelper timer;
    public SmoothDamper smoothDamper;
    public static boolean activeState;

    public Range() {
        super("Range", "Distanz", new Color(65, 140, 35), Category.LEGIT);
        this.settingRangeInternal = new NumberSetting(this, "RangeInternal", 3.0, 3.0, 4.5, 2).getNumberSetting("Range").getNumberSetting(() -> this.isRangeInternalVisible());
        this.settingBaseRange = new ActionSetting(this, "BaseRange", "ExtendedRange", 3.0, 3.7, 3.0, 6.0, 2).getActionSetting(() -> this.isBaseRangeVisible());
        this.settingChanceOfExtendedRange = new NumberSetting(this, "Chance of Extended Range", 50.0, 0.0, 100.0, 0).getNumberSetting(() -> this.isChanceOfExtendedRangeVisible());
        this.settingOnlyWhileSprinting = new BooleanSetting(this, "OnlyWhileSprinting", false);
        this.settingThroughWalls = new BooleanSetting(this, "ThroughWalls", false);
        this.settingBuildRange = new NumberSetting(this, "BuildRange", 4.5, 4.5, 6.0, 2);
        this.reachDistance = 3.0;
        this.expanded2 = false;
        this.targetEntity = null;
        this.timer = new TimeHelper();
        this.smoothDamper = new SmoothDamper(100, 0.0);
    }

    @Override
    public void onEnable() {
        this.registerEvent(40);
        this.registerEvent(1);
        this.registerEvent(17);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
    }

    @Override
    public void onPreDisable() {
        this.reachDistance = 3.0;
        this.expanded2 = false;
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        if (event.getEntity() instanceof EntityLivingBase) {
            this.targetEntity = (EntityLivingBase)event.getEntity();
            this.timer.reset();
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity = null;
    }

    @Override
    public void onRunTick() {
        this.applyString4("" + this.reachDistance);
        if (this.settingRangeMode.getSelected().equals("Default")) {
            this.reachDistance = this.settingRangeInternal.getValue();
        } else {
            if (this.timer.hasReached(3000L)) {
                this.targetEntity = null;
            }

            if (this.targetEntity != null) {
                double offset;
                if (this.expanded2) {
                    offset = this.settingBaseRange.getDouble6();
                } else {
                    offset = this.settingBaseRange.getDouble3();
                }

                this.reachDistance = offset;
                if (this.targetEntity.hurtTime == 9) {
                    int int12 = MathHelper.getInt12((int)this.settingChanceOfExtendedRange.getValue(), 0, 100);
                    this.expanded2 = this.smoothDamper.checkCondition(int12);
                }
            }
        }
    }

    public double getCoordinate() {
        return this.reachDistance;
    }

    public static boolean canExecute5() {
        return !canExecute6();
    }

    public static void setActive4(boolean enabled) {
        activeState = enabled;
    }

    public static boolean canExecute6() {
        return activeState;
    }

    private boolean isChanceOfExtendedRangeVisible() {
        return this.settingRangeMode.getSelected().equals("Dual");
    }

    private boolean isBaseRangeVisible() {
        return this.settingRangeMode.getSelected().equals("Dual");
    }

    private boolean isRangeInternalVisible() {
        return this.settingRangeMode.getSelected().equals("Default");
    }

    public boolean checkCondition() {
        return ClientGlobals.moduleManager.range.isToggled();
    }
}
