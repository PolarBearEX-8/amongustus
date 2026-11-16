package augustus.modules.combat;

import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventStrafe;
import client.event.EventWorldLoad;
import client.event.MoveListener;
import client.event.PacketListener;
import client.event.StrafeListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.AnimationTimer;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.TrajectoryPredictor;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public class MoreKB extends Module implements WorldLoadListener, MoveListener, TickListener, StrafeListener, PacketListener, AttackEntityListener {
    public ModeSetting settingModes;
    public BooleanSetting settingHideSneakAnimation;
    public NumberSetting settingFOV;
    public BooleanSetting settingIgnoreEatingTargets;
    public BooleanSetting settingDontSprintResetIfYouAlreadyDidLegit;
    public SettingGroup settingTiming;
    public RangeSetting settingSprintResetTime;
    public SettingGroup settingDuration;
    public RangeSetting settingSprintResetTicks;
    public SettingGroup settingDoubleReset;
    public BooleanSetting settingDoubleSprintReset;
    public RangeSetting settingDoubleResetTiming;
    public RangeSetting settingDoubleResetDelay;
    public SettingGroup settingFail;
    public BooleanSetting settingFailSprintReset;
    public RangeSetting settingFailDelay;
    public RangeSetting settingFailDuration;
    public AnimationTimer animationTimer;
    public int keyCode2;
    public int tickDelay2;
    public int tickDelay3;
    public int tickDelay1;
    public boolean enabled2;
    public EntityLivingBase targetEntity;
    public boolean visible2;
    public int tickDelay;
    public boolean activeState4;
    public boolean expanded2;
    public boolean activeState5;
    public boolean activeState;

    public MoreKB() {
        super("MoreKB", "MehrRückstoß", new Color(252, 73, 3), Category.COMBAT);
        String[] textArray = new String[9];
        textArray[0] = "STap";
        textArray[1] = "LegitSneak";
        textArray[2] = "Legit";
        textArray[3] = "LegitFast";
        textArray[4] = "Fast";
        textArray[5] = "LessPacket";
        textArray[6] = "Packet";
        textArray[7] = "DoublePacket";
        textArray[8] = "One";
        this.settingModes = new ModeSetting(this, "Modes", "Legit", textArray);
        this.settingHideSneakAnimation = (BooleanSetting)new BooleanSetting(this, "HideSneakAnimation", true).getSetting(() -> this.isModesVisible());
        this.settingFOV = new NumberSetting(this, "FOV", 180.0, 20.0, 180.0, 0);
        this.settingIgnoreEatingTargets = new BooleanSetting(this, "IgnoreEatingTargets", false);
        this.settingDontSprintResetIfYouAlreadyDidLegit = new BooleanSetting(this, "DontSprintResetIfYouAlreadyDidLegit", false);
        this.settingTiming = new SettingGroup(this, "Timing", true);
        this.settingSprintResetTime = new RangeSetting(this, "SprintResetTime", 3.0, 5.0, 0.0, 10.0, 0, this.settingTiming).getRangeSetting5().getRangeSetting(1.0, 7.0, 0.0, 10.0, 15.0, 30.0).getRangeSetting4(1.0, 0.5, 5.0, false);
        this.settingDuration = new SettingGroup(this, "Duration", true);
        this.settingSprintResetTicks = new RangeSetting(this, "SprintResetTicks", 1.0, 1.0, 1.0, 5.0, 0, this.settingDuration).getRangeSetting5().getRangeSetting(2.0, 2.0, 1.0, 5.0, 0.0, 3.0).getRangeSetting("SprintResetDuration").getRangeSetting4(1.0, 0.5, 4.0, false);
        this.settingDoubleReset = new SettingGroup(this, "DoubleReset", true);
        this.settingDoubleSprintReset = new BooleanSetting(this, "DoubleSprintReset", true, this.settingDoubleReset);
        this.settingDoubleResetTiming = new RangeSetting(this, "DoubleResetTiming", 10.0, 14.0, 0.0, 20.0, 0, this.settingDoubleReset).getRangeSetting4(1.0, 0.5, 5.0, false).getRangeSetting(15343, () -> this.settingDoubleSprintReset.isEnabled());
        this.settingDoubleResetDelay = new RangeSetting(this, "DoubleResetDelay", 10.0, 15.0, 0.0, 30.0, 0, this.settingDoubleReset).getRangeSetting2().getRangeSetting(5.0, 20.0, 0.0, 30.0, 10.0, 15.0).getRangeSetting(15343, () -> this.settingDoubleSprintReset.isEnabled());
        this.settingFail = new SettingGroup(this, "Fail", true);
        this.settingFailSprintReset = new BooleanSetting(this, "FailSprintReset", true, this.settingFail);
        this.settingFailDelay = new RangeSetting(this, "FailDelay", 15.0, 18.0, 0.0, 150.0, 0, this.settingFail).getRangeSetting2().getRangeSetting(15343, () -> this.settingFailSprintReset.isEnabled());
        this.settingFailDuration = new RangeSetting(this, "FailDuration", 1.0, 2.0, 1.0, 10.0, 0, this.settingFail).getRangeSetting4(1.0, 0.5, 5.0, false).getRangeSetting(15343, () -> this.settingFailSprintReset.isEnabled());
        this.animationTimer = new AnimationTimer();
        this.keyCode2 = 0;
        this.tickDelay2 = 0;
        this.tickDelay3 = 0;
        this.tickDelay1 = 0;
        this.enabled2 = true;
        this.visible2 = false;
        this.tickDelay = 0;
        this.activeState4 = false;
        this.expanded2 = false;
        this.activeState5 = false;
        this.activeState = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(21);
        this.registerEvent(40);
        this.registerEvent(24);
        this.registerEvent(17);
        this.registerEvent(26);
        this.registerEvent(1);
    }

    @Override
    public void onDisable() {
        this.targetEntity = null;
    }

    @Override
    public void onPreDisable() {
        this.enabled2 = true;
        this.visible2 = false;
        this.activeState5 = false;
        this.expanded2 = false;
        this.settingDoubleResetDelay.recalculate();
        this.settingDoubleResetTiming.recalculate();
        this.activeState = false;
    }

    @Override
    public void onStrafe(EventStrafe event) {
        if (this.tickDelay < 3) {
            if (this.settingHideSneakAnimation.isEnabled()) {
                event.processAction3(1.62F);
            }
        }
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityLivingBase) {
            if (((EntityLivingBase)entity).hurtTime < 3) {
                this.activeState5 = this.expanded2;
                this.expanded2 = false;
            }
        }
    }

    @Override
    public void onPacketSend(EventPacket packet) {
        Packet packet2 = packet.getPacket();
        if (packet2 instanceof C0BPacketEntityAction) {
            if (((C0BPacketEntityAction)packet2).getEntityPlayerAction() == EntityPlayerAction.START_SPRINTING) {
                this.expanded2 = true;
            }
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
        this.targetEntity = null;
    }

    @Override
    public void onMove(EventMove event) {
        if (event.isState4() || event.getScaleFactor7() != 1.0F) {
            this.activeState = true;
        }

        this.tickDelay++;
        if (this.settingModes.getSelected().equals("LegitSneak")) {
            if (this.tickDelay2 > 0) {
                this.tickDelay2--;
                event.setState4(true);
                this.visible2 = true;
                this.tickDelay = 0;
            }
        }
    }

    @Override
    public void onRunTick() {
        this.applyString4(this.settingModes.getSelected());
        EntityLivingBase entityLivingBase = this.getEntityLivingBase();
        if (entityLivingBase != null) {
            this.targetEntity = entityLivingBase;
        }

        if (this.targetEntity != null && this.targetEntity.hurtTime == 10) {
            this.activeState = false;
        }

        if (minecraft.thePlayer.isBlocking()) {
            this.activeState = true;
        }

        if (this.activeState && this.settingDontSprintResetIfYouAlreadyDidLegit.isEnabled()) {
            this.activeState4 = false;
            this.enabled2 = false;
            this.tickDelay2 = 0;
        }

        if (this.targetEntity != null && this.targetEntity.hurtTime == 10 && !this.canExecute6() && this.canExecute5()) {
            this.expanded2 = false;
            double offset = 180.0
            - TrajectoryPredictor.getDouble4(
            (double)MathHelper.sqrt_float(minecraft.thePlayer.rotationYaw), (double)MathHelper.sqrt_float(this.targetEntity.scaleFactor12)
            )
            - this.settingFOV.getValue();
            if (offset <= 0.0) {
                boolean enabled = this.canExecute7();
                boolean enabled3 = !enabled;
                MoreKB moreKB2;
                boolean enabled4;
                if (enabled3) {
                    moreKB2 = this;
                    enabled4 = true;
                } else {
                    moreKB2 = this;
                    enabled4 = false;
                }

                moreKB2.enabled2 = enabled4;
            }

            this.tickDelay1 = 0;
            this.targetEntity = null;
            this.animationTimer.processAction();
            if (this.keyCode2 <= 0) {
                this.tickDelay3++;
            }

            this.activeState4 = false;
        }

        if (this.enabled2) {
            int tickDelay12 = this.tickDelay1;
            int indexOffset2 = this.settingSprintResetTime.getIndexOffset2();
            if (tickDelay12 == indexOffset2) {
                this.settingSprintResetTime.getDouble4();
                this.tickDelay2 = (int)this.settingSprintResetTicks.getDouble4();
                this.enabled2 = false;
                if (ClientGlobals.moduleManager.debug.isToggled()
                && ClientGlobals.moduleManager.debug.settingDecimalPlaces2 != null
                && ClientGlobals.moduleManager.debug.settingDecimalPlaces2.isEnabled()) {
                    RayTraceUtil.applyString("SprintReset Count: " + this.tickDelay1 + " " + this.tickDelay2);
                }
            }
        }

        boolean enabled5 = this.tickDelay2 > 0;
        MoreKB moreKB4;
        boolean enabled6;
        if (enabled5) {
            moreKB4 = this;
            enabled6 = true;
        } else {
            moreKB4 = this;
            enabled6 = false;
        }

        moreKB4.visible2 = enabled6;
        if (this.tickDelay2 > 0 && !this.settingModes.getSelected().equals("LegitSneak")) {
            this.tickDelay2--;
            this.processAction(false);
        }

        if ((!this.activeState || !this.settingDontSprintResetIfYouAlreadyDidLegit.isEnabled()) && this.settingDoubleSprintReset.isEnabled() && !this.visible2) {
            int tickDelay13 = this.tickDelay1;
            int indexOffset22 = this.settingDoubleResetTiming.getIndexOffset2();
            if (tickDelay13 == indexOffset22 && this.animationTimer.checkCondition(this.settingDoubleResetDelay.getIndexOffset2()) && !this.activeState4) {
                this.processAction(true);
                this.settingDoubleResetDelay.recalculate();
                this.settingDoubleResetTiming.recalculate();
                this.animationTimer.processAction3();
                this.activeState4 = true;
            }
        }

        this.tickDelay1++;
    }

    public static void updateState() {
    }

    private void processAction(boolean enabled) {
        String selected = this.settingModes.getSelected();
switch (selected) {
            case "Packet":
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SPRINTING));
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.START_SPRINTING));
                minecraft.thePlayer.setIsInWeb2(true);
                this.tickDelay2 = 0;
                return;
            case "DoublePacket":
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SPRINTING));
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.START_SPRINTING));
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.STOP_SPRINTING));
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.START_SPRINTING));
                minecraft.thePlayer.setIsInWeb2(true);
                this.tickDelay2 = 0;
                return;
            case "LegitFast":
                minecraft.thePlayer.state47 = true;
                return;
            case "Legit":
                minecraft.thePlayer.inWater3 = true;
                return;
            case "LessPacket":
                if (minecraft.thePlayer.isRiding()) {
                    minecraft.thePlayer.setState3(false);
                }
                minecraft.thePlayer.netHandlerPlayClient.handlePacket(new C0BPacketEntityAction(minecraft.thePlayer, EntityPlayerAction.START_SPRINTING));
                minecraft.thePlayer.setIsInWeb2(true);
                this.tickDelay2 = 0;
                return;
            case "One":
                minecraft.thePlayer.setIsInWeb2(false);
                return;
            case "Sneak":
                if (!enabled) {
                    return;
                }
                minecraft.thePlayer.inWater3 = true;
                return;
            case "STap":
                minecraft.thePlayer.velocityChanged = true;
                return;
            case "Fast":
                minecraft.thePlayer.setState6(true);
                return;
        }
    }

    private boolean canExecute5() {
        return !this.settingIgnoreEatingTargets.isEnabled() || !this.targetEntity.canExecute6();
    }

    private boolean canExecute6() {
        if (!ClientGlobals.moduleManager.backTrack.isToggled()) {
            return false;
        } else if (!ClientGlobals.moduleManager.backTrack.settingImprove.isEnabled()) {
            return false;
        } else {
            return !ClientGlobals.moduleManager.backTrack.isExpanded2()
            ? false
            : minecraft.thePlayer
            .getDouble4(this.targetEntity.coordOffset5, this.targetEntity.coordOffset20, this.targetEntity.coordOffset2) > 3.0;
        }
    }

    private EntityLivingBase getEntityLivingBase() {
        Object object = null;
        if (minecraft.objectMouseOver == null) {
            return ChatUtil.getEntityLivingBase2((EntityLivingBase)object);
        } else if (minecraft.objectMouseOver.typeOfHit != MovingObjectType.ENTITY) {
            return ChatUtil.getEntityLivingBase2((EntityLivingBase)object);
        } else {
            MovingObjectPosition objectMouseOver = minecraft.objectMouseOver;
            return !(objectMouseOver.entityHit instanceof EntityLivingBase)
            ? ChatUtil.getEntityLivingBase2((EntityLivingBase)object)
            : ChatUtil.getEntityLivingBase2((EntityLivingBase)minecraft.objectMouseOver.entityHit);
        }
    }

    private boolean isModesVisible() {
        return this.settingModes.getSelected().equals("LegitSneak");
    }

    private boolean canExecute7() {
        if (this.keyCode2 > 0 && this.activeState5) {
            this.keyCode2++;
        }

        this.keyCode2--;
        boolean enabled;
        if (this.settingFailSprintReset.isEnabled() && this.tickDelay3 >= this.settingFailDelay.getIndexOffset2()) {
            enabled = true;
        } else {
            enabled = false;
        }

        if (enabled) {
            this.keyCode2 = (int)this.settingFailDuration.recalculate();
            this.tickDelay3 = 0;
            this.settingFailDelay.getDouble4();
        }

        return this.keyCode2 > 0;
    }
}
