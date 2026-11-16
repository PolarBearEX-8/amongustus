package augustus.modules.legit;

import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.ClickListener;
import client.event.EventAttackEntity;
import client.event.EventClick;
import client.event.EventSendPacket;
import client.event.NetworkPacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.util.BooleanSupplier;
import client.util.CombatRayTrace;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;

public class AutoBlock extends Module implements ClickListener, NetworkPacketListener, AttackEntityListener {
    public ModeSetting settingMode;
    public NumberSetting settingStartBlockingAdditionalRange;
    public NumberSetting settingMaximumPlayerHurtTime;
    public NumberSetting settingMaximumHurtTime;
    public NumberSetting settingTicksToTryHitAfterUnblock;
    public RangeSetting settingMaximumLagDuration;
    public BooleanSetting settingOnlyOnRightClick;
    public BooleanSetting settingIgnoreDeadTargets;
    public BooleanSetting settingForceHit;
    public BooleanSetting settingInteractAutoBlock;
    public AtomicBoolean atomicBoolean;
    public EntityLivingBase targetEntity;
    public TimeHelper timer;
    public TimeHelper timer2;
    public int tickDelay;
    public int keyCode2;
    public AtomicBoolean atomicBoolean2;
    public boolean expanded2;

    public AutoBlock() {
        super("AutoBlock", "AutomatisiertesBlockieren", Color.green, Category.LEGIT);
        String[] textArray = new String[2];
        textArray[0] = "Default";
        textArray[1] = "Lag";
        this.settingMode = new ModeSetting(this, "Mode", "Default", textArray);
        this.settingStartBlockingAdditionalRange = new NumberSetting(this, "StartBlockingAdditionalRange", 0.5, 0.0, 2.0, 2);
        this.settingMaximumPlayerHurtTime = new NumberSetting(this, "MaximumPlayerHurtTime", 4.0, 3.0, 10.0, 0);
        this.settingMaximumHurtTime = new NumberSetting(this, "MaximumHurtTime", 1.0, 4.0, 0.0, 0);
        this.settingTicksToTryHitAfterUnblock = new NumberSetting(this, "TicksToTryHitAfterUnblock", 2.0, 0.0, 4.0, 0);
        this.settingMaximumLagDuration = new RangeSetting(this, "MaximumLagDuration", 250.0, 350.0, 0.0, 1000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isMaximumLagDurationVisible());
        this.settingOnlyOnRightClick = new BooleanSetting(this, "OnlyOnRightClick", true);
        this.settingIgnoreDeadTargets = new BooleanSetting(this, "IgnoreDeadTargets", true);
        this.settingForceHit = new BooleanSetting(this, "ForceHit", true);
        this.settingInteractAutoBlock = new BooleanSetting(this, "InteractAutoBlock", true);
        this.atomicBoolean = new AtomicBoolean(false);
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        this.tickDelay = 0;
        this.keyCode2 = 0;
        this.atomicBoolean2 = new AtomicBoolean(false);
        this.expanded2 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(7, 90000);
        this.registerEvent(25);
        this.registerEvent(1);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        this.targetEntity = (EntityLivingBase)event.getEntity();
        this.timer.reset();
    }

    @Override
    public void onClick(EventClick event) {
        this.updateState2();
        this.atomicBoolean.set(this.canExecute9());
        this.keyCode2--;
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        if (this.atomicBoolean.get()) {
            packet.getPacket();
            if (this.timer2.hasReached((long)this.settingMaximumLagDuration.getIndexOffset2())) {
                return;
            }

            this.expanded2 = true;
            this.timer2.reset();
            this.settingMaximumLagDuration.recalculate();
        }
    }

    private static void updateState() {
        if (minecraft.thePlayer.inventoryPlayer.getCurrentItem() != null) {
            minecraft.entityRenderer.itemRenderer.updateState2();
        }
    }

    private boolean canExecute5() {
        return (double)this.targetEntity.maxHurtTime != this.settingMaximumHurtTime.getValue();
    }

    private boolean checkCondition(int index) {
        return CombatRayTrace.getMovingObjectPosition(index, ClientGlobals.rotationManager.getRotation2(), this.getDouble()).entityHit instanceof EntityLivingBase;
    }

    private double getDouble() {
        return ClientGlobals.moduleManager.range.isToggled() ? 4.5 : 3.0;
    }

    private boolean canExecute6() {
        return this.settingOnlyOnRightClick.isEnabled();
    }

    private void updateState2() {
        if (this.targetEntity != null) {
            double distance = (double)minecraft.thePlayer.getDistanceToEntity(this.targetEntity);
            double doubleEntry = this.getDouble();
            if (distance > doubleEntry + this.settingStartBlockingAdditionalRange.getValue()) {
                this.targetEntity = null;
            }
        }
    }

    private void onClick2(EventClick event) {
        event.setRunnable2(() -> updateState());
    }

    private boolean canExecute7() {
        return ClientGlobals.moduleManager.killAura.isToggled();
    }

    private boolean canExecute8() {
        return minecraft.objectMouseOver.entityHit instanceof EntityLivingBase;
    }

    private boolean canExecute9() {
        if (!this.canExecute6()) {
            return false;
        } else if (!this.checkCondition()) {
            return false;
        } else {
            return this.targetEntity == null ? false : this.canExecute7();
        }
    }

    private boolean checkCondition() {
        return minecraft.thePlayer.getItemStack().getItem() instanceof ItemSword;
    }

    private boolean checkCondition5() {
        return (double)minecraft.thePlayer.hurtTime != this.settingMaximumPlayerHurtTime.getValue();
    }

    private boolean isMaximumLagDurationVisible() {
        return this.settingMode.getSelected().equals("Lag");
    }
}
