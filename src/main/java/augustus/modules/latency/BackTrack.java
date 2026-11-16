package augustus.modules.latency;

import augustus.custom.RuntimeBridge;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMouseClick;
import client.event.EventRenderEntity;
import client.event.EventSendPacket;
import client.event.EventWorldLoad;
import client.event.MouseClickListener;
import client.event.NetworkPacketListener;
import client.event.RenderEntityListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.event.WorldLoadListener;
import client.module.Category;
import client.module.Module;
import client.network.LatencyPacket;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.ActionSetting;
import client.setting.AutoSoupPerformRespawnType;
import client.setting.BackTrackTargetEntity;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.setting.ShaderOptions;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.CombatTargetFilter;
import client.util.CombatUtil;
import client.util.MouseUtils;
import client.util.MovementHandler;
import client.util.Packet;
import client.util.PlayerDiggingActionType;
import client.util.RunnableTask;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import java.awt.Color;
import net.minecraft.block.BlockCache;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.CAnimateHandPacket;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class BackTrack
extends Module
implements WorldLoadListener,
NetworkPacketListener,
RenderListener,
RenderEntityListener,
MouseClickListener,
TickListener,
ClickListener {
    public ModeSetting settingMode;
    public RangeSetting settingMaxHitRange;
    public NumberSetting settingThreshold;
    public BooleanSetting settingCancelHitsIfServerCancelsHit;
    public RangeSetting settingClassicBackTrackDelay;
    public BooleanSetting settingOnlyWhenNeed;
    public BooleanSetting settingDynamicClassic;
    public ActionSetting settingBackTrackTime;
    public RangeSetting settingDelayBetweenBacktrack;
    public BooleanSetting settingCalculateWithOwnPing;
    public BooleanSetting settingForceDelay;
    public BooleanSetting settingPredict;
    public BooleanSetting settingDynamic;
    public RangeSetting settingMaxLagPackets;
    public NumberSetting settingDelayBetweenLags;
    public ModeSetting settingCountMode;
    public BooleanSetting settingCalculateWithOwnPingLagTrack;
    public NumberSetting settingLagPackets;
    public RangeSetting settingPingDelay;
    public NumberSetting settingDelayBetweenBacktrackPing;
    public BooleanSetting settingCalculateWithOwnPingPing;
    public BooleanSetting settingDynamicPing;
    public BooleanSetting settingCheckStartDistance;
    public NumberSetting settingStartDistance;
    public ModeSetting settingAbortCriterion;
    public NumberSetting settingStopOnAttackRange;
    public BooleanSetting settingFixPacketOrder;
    public NumberSetting settingStopOnAttackMaxHurtTime;
    public BooleanSetting settingStopBackTrackAtHurtTime;
    public NumberSetting settingContinueWithBackTrackAtHurtTime;
    public BooleanSetting settingNotWhileScaffold;
    public BooleanSetting settingImprove;
    public BooleanSetting settingOnlyKillAura;
    public MultiSelectSetting settingTargets;
    public NumberSetting settingPreAimRange;
    public BooleanSetting settingClickCheck;
    public SettingGroup settingTargetESPs;
    public ModeSetting settingTargetESP;
    public ColorSetting settingClassicColorTarget;
    public NumberSetting settingLineWidthTarget;
    public BooleanSetting settingSmoothRendering;
    public ShaderOptions settingTarget;
    public SettingGroup settingPlayerESPs;
    public ModeSetting settingPlayerESP;
    public BooleanSetting settingOnlyInThirdPerson;
    public ColorSetting settingClassicColorMe;
    public NumberSetting settingLineWidthMe;
    public ShaderOptions settingPlayer;
    public EntityLivingBase targetEntity;
    public boolean enabled2;
    public boolean visible2;
    public boolean expanded2;
    public boolean activeState14;
    public boolean activeState12;
    public boolean activeState6;
    public TimeHelper timer;
    public TimeHelper timer2;
    public int tickDelay;
    public double[] coordOffsetArray;
    public TimeHelper timer3;
    public int tickDelay3;
    public int tickDelay1;
    public boolean activeState4;
    public boolean activeState;
    public boolean activeState11;
    public boolean activeState13;
    public boolean activeState7;
    public int tickDelay2;
    public boolean activeState8;
    public boolean activeState5;
    public boolean activeState9;
    public boolean activeState10;

    public BackTrack() {
        super("BackTrack", "Zurückverfolgung", new Color(110, 186, 9), Category.LATENCY);
        String[] textArray = new String[5];
        textArray[0] = "BackTrack";
        textArray[1] = "LagTrack";
        textArray[2] = "Gomme";
        textArray[3] = "Ping";
        textArray[4] = "ClassicBackTrack";
        this.settingMode = new ModeSetting(this, "Mode", "BackTrack", textArray);
        this.settingMaxHitRange = new RangeSetting(this, "MaxHitRange", 6.0, 6.0, 3.0, 10.0, 2).getRangeSetting("MaxBackTrackRangeMin", "MaxBackTrackRangeMax").getRangeSetting2();
        this.settingThreshold = new NumberSetting(this, "Threshold", 0.125, 0.0, 0.2, 3);
        this.settingCancelHitsIfServerCancelsHit = new BooleanSetting(this, "CancelHitsIfServerCancelsHit", true);
        this.settingClassicBackTrackDelay = new RangeSetting(this, "ClassicBackTrackDelay", 200.0, 250.0, 0.0, 5000.0, 0)
        .getRangeSetting2().getRangeSetting("MinBackTrackDelay", "MaxBackTrackDelay").getRangeSetting(15343, () -> this.isClassicBackTrackDelayVisible3());
        Setting setting = new BooleanSetting(this, "OnlyWhenNeed", false).getSetting(() -> this.isClassicBackTrackDelayVisible2());
        this.settingOnlyWhenNeed = (BooleanSetting)setting;
        Setting setting2 = new BooleanSetting(this, "DynamicClassic", false).getSetting("Dynamic");
        this.settingDynamicClassic = (BooleanSetting)(((BooleanSetting)setting2).getSetting(() -> this.isClassicBackTrackDelayVisible()));
        this.settingBackTrackTime = new ActionSetting(this, "BackTrackTime", 200.0, 250.0, 0.0, 5000.0, 0).getActionSetting("MinBackTrackDelay", "MaxBackTrackDelay").getActionSetting(() -> this.isBackTrackTimeVisible());
        this.settingDelayBetweenBacktrack = new RangeSetting(this, "DelayBetweenBacktrack", 50.0, 150.0, 0.0, 5000.0, 0)
        .getRangeSetting2().getRangeSetting("DelayBetweenBacktracks").getRangeSetting(15343, () -> this.isDelayBetweenBacktrackVisible5());
        Setting setting4 = new BooleanSetting(this, "CalculateWithOwnPing", false).getSetting("Subtract ping from Delay");
        this.settingCalculateWithOwnPing = (BooleanSetting)(((BooleanSetting)setting4).getSetting(() -> this.isDelayBetweenBacktrackVisible2()));
        this.settingForceDelay = (BooleanSetting)new BooleanSetting(this, "ForceDelay", false).getSetting(() -> this.isDelayBetweenBacktrackVisible4());
        this.settingPredict = (BooleanSetting)new BooleanSetting(this, "Predict", true).getSetting(() -> this.isDelayBetweenBacktrackVisible());
        this.settingDynamic = (BooleanSetting)new BooleanSetting(this, "Dynamic", false).getSetting(() -> this.isDelayBetweenBacktrackVisible3());
        this.settingMaxLagPackets = new RangeSetting(this, "MaxLagPackets", 2.0, 2.0, 0.0, 10.0, 0).getRangeSetting(15343, () -> this.isMaxLagPacketsVisible());
        this.settingDelayBetweenLags = new NumberSetting(this, "DelayBetweenLags", 0.0, 0.0, 4000.0, 0).getNumberSetting(() -> this.isDelayBetweenLagsVisible());
        String[] textArray2 = new String[2];
        textArray2[0] = "Tick";
        textArray2[1] = "MovePacket";
        this.settingCountMode = new ModeSetting(this, "CountMode", "Tick", textArray2).getModeSetting(() -> this.isCountModeVisible());
        Setting setting9 = new BooleanSetting(this, "CalculateWithOwnPingLagTrack", false).getSetting("Subtract ping from Delay");
        this.settingCalculateWithOwnPingLagTrack = (BooleanSetting)(((BooleanSetting)setting9).getSetting(() -> this.isCountModeVisible2()));
        this.settingLagPackets = new NumberSetting(this, "LagPackets", 10.0, 1.0, 10.0, 0).getNumberSetting("MaxLagPackets").getNumberSetting(() -> this.isLagPacketsVisible());
        this.settingPingDelay = new RangeSetting(this, "PingDelay", 150.0, 150.0, 0.0, 2000.0, 0)
        .getRangeSetting2().getRangeSetting(15343, () -> this.isPingDelayVisible());
        this.settingDelayBetweenBacktrackPing = new NumberSetting(this, "DelayBetweenBacktrackPing", 1200.0, 0.0, 5000.0, 0).getNumberSetting("DelayBetweenBacktracks").getNumberSetting(() -> this.isDelayBetweenBacktrackPingVisible3());
        Setting setting11 = new BooleanSetting(this, "CalculateWithOwnPingPing", false).getSetting("Subtract own ping from Delay");
        this.settingCalculateWithOwnPingPing = (BooleanSetting)(((BooleanSetting)setting11).getSetting(() -> this.isDelayBetweenBacktrackPingVisible2()));
        Setting setting13 = new BooleanSetting(this, "DynamicPing", false).getSetting("Dynamic");
        this.settingDynamicPing = (BooleanSetting)(((BooleanSetting)setting13).getSetting(() -> this.isDelayBetweenBacktrackPingVisible()));
        this.settingCheckStartDistance = (BooleanSetting)new BooleanSetting(this, "CheckStartDistance", false).getSetting(() -> this.isDelayBetweenBacktrackPingVisible4());
        this.settingStartDistance = new NumberSetting(this, "StartDistance", 3.0, 0.0, 6.0, 2).getNumberSetting(() -> this.isStartDistanceVisible());
        String[] textArray3 = new String[3];
        textArray3[0] = "None";
        textArray3[1] = "OnAttack";
        textArray3[2] = "OnRange";
        this.settingAbortCriterion = new ModeSetting(this, "AbortCriterion", "None", textArray3).getModeSetting(() -> this.isAbortCriterionVisible());
        this.settingStopOnAttackRange = new NumberSetting(this, "StopOnAttackRange", 2.95, 1.0, 6.0, 2).getNumberSetting(() -> this.isStopOnAttackRangeVisible2());
        this.settingFixPacketOrder = (BooleanSetting)new BooleanSetting(this, "FixPacketOrder", true).getSetting(() -> this.isStopOnAttackRangeVisible());
        this.settingStopOnAttackMaxHurtTime = new NumberSetting(this, "StopOnAttackMaxHurtTime", 3.0, 2.0, 10.0, 0).getNumberSetting(() -> this.isStopOnAttackMaxHurtTimeVisible());
        this.settingStopBackTrackAtHurtTime = (BooleanSetting)new BooleanSetting(this, "StopBackTrackAtHurtTime", false).getSetting(() -> this.isStopOnAttackMaxHurtTimeVisible2());
        this.settingContinueWithBackTrackAtHurtTime = new NumberSetting(this, "ContinueWithBackTrackAtHurtTime", 0.0, 0.0, 10.0, 0).getNumberSetting(() -> this.settingStopBackTrackAtHurtTime.isEnabled()).getNumberSetting(() -> this.isContinueWithBackTrackAtHurtTimeVisible());
        this.settingNotWhileScaffold = new BooleanSetting(this, "NotWhileScaffold", true);
        this.settingImprove = new BooleanSetting(this, "Improve", false);
        this.settingOnlyKillAura = new BooleanSetting(this, "OnlyKillAura", true);
        BackTrackTargetEntity[] backTrackTargetEntityArray = BackTrackTargetEntity.values();
        this.settingTargets = new MultiSelectSetting(this, "Targets", backTrackTargetEntityArray).getMultiSelectSetting(() -> this.isTargetsVisible());
        this.settingPreAimRange = new NumberSetting(this, "PreAimRange", 4.0, 0.0, 15.0, 1).getNumberSetting(() -> this.isPreAimRangeVisible());
        this.settingClickCheck = new BooleanSetting(this, "ClickCheck", false);
        this.settingTargetESPs = new SettingGroup(this, "TargetESPs");
        String[] textArray4 = new String[4];
        textArray4[0] = "None";
        textArray4[1] = "Classic";
        textArray4[2] = "PlayerModel";
        textArray4[3] = "PlayerModelArmor";
        this.settingTargetESP = new ModeSetting(this, "TargetESP", "Classic", textArray4, this.settingTargetESPs);
        Color color = new Color(0, 255, 0, 0);
        Setting setting18 = new ColorSetting(this, "ClassicColorTarget", color, this.settingTargetESPs).getSetting("ClassicColor");
        this.settingClassicColorTarget = (ColorSetting)(((ColorSetting)setting18).getSetting(() -> this.isClassicColorTargetVisible()));
        this.settingLineWidthTarget = new NumberSetting(this, "LineWidthTarget", 3.0, 0.0, 10.0, 0, this.settingTargetESPs).getNumberSetting("LineWidth").getNumberSetting(() -> this.isLineWidthTargetVisible());
        this.settingSmoothRendering = (BooleanSetting)new BooleanSetting(this, "SmoothRendering", false, this.settingTargetESPs).getSetting(() -> this.isSmoothRenderingVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this, "Target", this.settingTargetESPs);
        this.settingTarget = ((ShaderOptions)shaderOptions.getSetting(() -> this.isTargetVisible())).getShaderOptions5();
        this.settingPlayerESPs = new SettingGroup(this, "PlayerESPs").getSettingGroup(() -> this.isPlayerESPsVisible());
        String[] textArray5 = new String[4];
        textArray5[0] = "None";
        textArray5[1] = "Classic";
        textArray5[2] = "PlayerModel";
        textArray5[3] = "PlayerModelArmor";
        this.settingPlayerESP = new ModeSetting(this, "PlayerESP", "Classic", textArray5, this.settingPlayerESPs);
        this.settingOnlyInThirdPerson = new BooleanSetting(this, "OnlyInThirdPerson", true, this.settingPlayerESPs);
        Color color2 = new Color(0, 255, 0, 0);
        Setting setting21 = new ColorSetting(this, "ClassicColorMe", color2, this.settingPlayerESPs).getSetting("ClassicColor");
        this.settingClassicColorMe = (ColorSetting)(((ColorSetting)setting21).getSetting(() -> this.isClassicColorMeVisible()));
        this.settingLineWidthMe = new NumberSetting(this, "LineWidthMe", 3.0, 0.0, 10.0, 0, this.settingPlayerESPs).getNumberSetting("LineWidth").getNumberSetting(() -> this.isLineWidthMeVisible());
        ShaderOptions shaderOptions2 = new ShaderOptions(this, "Player", this.settingPlayerESPs);
        this.settingPlayer = ((ShaderOptions)shaderOptions2.getSetting(() -> this.isPlayerVisible())).getShaderOptions5();
        this.targetEntity = (EntityLivingBase)null;
        this.enabled2 = false;
        this.visible2 = false;
        this.expanded2 = false;
        this.activeState6 = false;
        this.timer = new TimeHelper();
        this.timer2 = new TimeHelper();
        double[] coordArray = new double[3];
        this.coordOffsetArray = coordArray;
        this.timer3 = new TimeHelper();
        this.tickDelay3 = 0;
        this.tickDelay1 = 0;
        this.activeState11 = false;
        this.activeState13 = false;
        this.activeState7 = false;
        this.tickDelay2 = 0;
        this.activeState8 = true;
        this.activeState5 = false;
        this.activeState9 = true;
        this.activeState10 = false;
    }

    @Override
    public void onEnable() {
        this.registerEvent(25);
        this.registerEvent(32);
        this.registerEvent(31);
        this.registerEvent(16);
        this.registerEvent(40);
        this.registerEvent(17);
        this.registerEvent(7, 1000000);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
        this.visible2 = false;
        this.expanded2 = false;
        this.settingDelayBetweenBacktrack.recalculate();
        this.activeState4 = true;
        this.activeState = true;
        this.activeState7 = false;
        this.tickDelay2 = 0;
        this.activeState8 = false;
        this.tickDelay = SettingContainer.settingProvider
        .getInt(this.settingBackTrackTime.getDouble3(), this.settingBackTrackTime.getDouble6());
        this.settingMaxHitRange.recalculate();
        this.settingMaxLagPackets.recalculate();
        this.settingPingDelay.recalculate();
        this.activeState9 = true;
    }

    @Override
    public void onMouseClick(EventMouseClick event) {
        String selected = this.settingMode.getSelected();
        switch (selected) {
            case "BackTrack":
                long time2 = (long)this.tickDelay;
                boolean reached = this.timer.hasReached(time2);
                if (!reached) {
                    return;
                } else if (!this.settingForceDelay.isEnabled()) {
                    return;
                } else {
                    if (!ClientGlobals.moduleManager.packetHandler.isEnabled2()) {
                        return;
                    }

                    minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket8(new LatencyPacket());
                    return;
                }
            case "LagTrack":
                if (minecraft.theWorld == null) {
                    return;
                } else {
                    if (minecraft.thePlayer == null) {
                        return;
                    } else if (minecraft.thePlayer.ticksExisted < 10) {
                        return;
                    } else if (minecraft.theWorld.loadedEntityList == null) {
                        return;
                    } else {
                        if (!this.settingCountMode.getSelected().equals("Tick")) {
                            return;
                        }

                        if (minecraft.getTimer().indexOffset >= 1) {
                            int tickDelay12 = this.tickDelay1;
                            int indexOffset2 = this.settingMaxLagPackets.getIndexOffset2();
                            if (tickDelay12 > indexOffset2) {
                                minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket8(new LatencyPacket());
                            }
                        }

                        this.tickDelay3 = this.tickDelay3 + minecraft.getTimer().indexOffset;
                        return;
                    }
                }
            case "Gomme":
                event.processBoolean(true);
                if (!this.activeState8) {
                    return;
                }

                event.setActive(true);
                return;
        }
    }

    @Override
    public void onNetworkPacket(EventSendPacket packet) {
        Packet packet2 = packet.getPacket();
        if (minecraft.thePlayer != null && packet2 instanceof S12PacketEntityVelocity) {
            int entityId = ((S12PacketEntityVelocity)packet2).getEntityId();
            if (entityId == minecraft.thePlayer.getEntityId()) {
                this.setActive5(true);
                this.setActive4(true);
            }
        }

        if (this.enabled2 && !ClientGlobals.moduleManager.packetHandler.isEnabled2()) {
            this.settingMaxHitRange.recalculate();
        }

        this.enabled2 = ClientGlobals.moduleManager.packetHandler.isEnabled2();
        String selected = this.settingMode.getSelected();
        switch (selected) {
            case "ClassicBackTrack":
                boolean enabled = this.settingDynamicClassic.isEnabled();
                if (enabled && this.activeState10) {
                    EntityLivingBase targetEntity2 = this.targetEntity;
                    packet.applyEntityLivingBase2(true, 21312, targetEntity2, 3.0, 49527);
                    return;
                }

                boolean activeState102 = this.activeState10;
                packet.setCancelled(activeState102);
                return;
            case "BackTrack":
                EntityLivingBase targetEntity3 = this.targetEntity;
                String text = Math.max(0, this.tickDelay) + " ms";
                this.applyString4(text);
                if (minecraft.theWorld == null) {
                    return;
                } else {
                    if (minecraft.thePlayer == null) {
                        return;
                    } else if (minecraft.thePlayer.ticksExisted < 10) {
                        return;
                    } else if (minecraft.theWorld.loadedEntityList == null) {
                        return;
                    } else {
                        if (targetEntity3 != null) {
                            Vec3 vec3 = this.getVec3(targetEntity3);
                            double[] coordArray;
                            if (this.settingPredict.isEnabled()
                            && (
                            targetEntity3.coordOffset5 != vec3.xCoord
                            || targetEntity3.coordOffset20 != vec3.yCoord
                            || targetEntity3.coordOffset2 != vec3.zCoord
                            )) {
                                synchronized (this.coordOffsetArray) {
                                    coordArray = this.coordOffsetArray;
                                }
                            } else {
                                double[] coordArray2 = new double[]{targetEntity3.coordOffset5, targetEntity3.coordOffset20, targetEntity3.coordOffset2};
                                coordArray = coordArray2;
                            }

                            AxisAlignedBB axisAlignedBB = SimulatedPlayer.getAxisAlignedBB(targetEntity3, vec3);
                            double offset = coordArray[0];
                            double offset2 = coordArray[1];
                            double offset3 = coordArray[2];
                            AxisAlignedBB boundingBox = SimulatedPlayer.processAction(targetEntity3, offset, offset2, offset3);
                            Vec3 vec4 = SimulatedPlayer.getVec3(axisAlignedBB);
                            Vec3 vec5 = SimulatedPlayer.getVec3(boundingBox);
                            Vec3 vec6 = minecraft.thePlayer.getVec35(1.0F);
                            if (this.canExecute7() && this.canExecute5()) {
                                TimeHelper timer22 = this.timer2;
                                long time2 = (long)this.settingDelayBetweenBacktrack.getIndexOffset2();
                                if (timer22.hasReached(time2)) {
                                    boolean enabled22 = ClientGlobals.moduleManager.packetHandler.isEnabled2();
                                    if (this.checkEntityLivingBase(enabled22, targetEntity3)) {
                                        double coordinate4 = vec6.getCoordinate4(vec5);
                                        double coordinate42 = vec6.getCoordinate4(vec4);
                                        if (this.checkCondition(coordinate4, coordinate42)
                                        && this.checkEntityLivingBase3(targetEntity3)) {
                                            long time3 = (long)this.tickDelay;
                                            if (!this.timer.hasReached(time3)) {
                                                if (this.settingDynamic.isEnabled()) {
                                                    if (ClientGlobals.moduleManager.packetHandler.isEnabled2()
                                                    || vec6.getCoordinate4(vec5) > 3.0) {
                                                        packet.applyEntityLivingBase2(true, 21312, targetEntity3, 3.0, 49527);
                                                    }
                                                } else {
                                                    packet.setCancelled(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        this.expanded2 = packet.isState();
                        if (packet.isState() && !this.activeState6) {
                            this.settingDelayBetweenBacktrack.recalculate();
                        }

                        if (!packet.isState()) {
                            this.visible2 = false;
                            if (this.activeState6 || this.tickDelay <= 0) {
                                if (this.settingCalculateWithOwnPing.isEnabled()) {
                                    double double3 = this.settingBackTrackTime.getDouble3();
                                    double double6 = this.settingBackTrackTime.getDouble6();
                                    this.tickDelay = Math.max(0, SettingContainer.settingProvider.getInt(double3, double6) - RuntimeBridge.calculatePingDelta());
                                } else {
                                    double double32 = this.settingBackTrackTime.getDouble3();
                                    double double62 = this.settingBackTrackTime.getDouble6();
                                    this.tickDelay = SettingContainer.settingProvider.getInt(double32, double62);
                                }

                                this.timer2.reset();
                            }

                            this.timer.reset();
                        }

                        this.activeState6 = packet.isState();
                        return;
                    }
                }
            case "LagTrack":
                if (minecraft.theWorld == null) {
                    return;
                } else {
                    if (minecraft.thePlayer == null) {
                        return;
                    } else if (minecraft.thePlayer.ticksExisted < 10) {
                        return;
                    } else {
                        if (minecraft.theWorld.loadedEntityList == null) {
                            return;
                        }

                        this.processAction();
                        boolean activeState72 = this.activeState7;
                        packet.setCancelled(activeState72);
                        this.expanded2 = this.activeState7;
                        boolean enabled3 = !packet.isState();
                        this.activeState4 = enabled3;
                        return;
                    }
                }
            case "Ping":
                EntityLivingBase targetEntity4 = this.targetEntity;
                StringBuilder stringBuilder = new StringBuilder();
                String text2 = stringBuilder.append(this.settingPingDelay.getIndexOffset2()).append(" ms").toString();
                this.applyString4(text2);
                if (targetEntity4 != null) {
                    if (minecraft.theWorld == null) {
                        return;
                    }

                    if (minecraft.thePlayer == null) {
                        return;
                    }

                    if (minecraft.thePlayer.ticksExisted < 10) {
                        return;
                    }

                    if (minecraft.theWorld.loadedEntityList != null) {
                        if (targetEntity4.positionVec3 == null) {
                            return;
                        }

                        Vec3 vec7 = minecraft.thePlayer.getVec35(1.0F);
                        Vec3 vec8 = this.getVec3(targetEntity4);
                        AxisAlignedBB axisAlignedBB2 = SimulatedPlayer.getAxisAlignedBB(targetEntity4, vec8);
                        Vec3 vec = SimulatedPlayer.processAction(vec7, axisAlignedBB2);
                        double coordOffset2 = targetEntity4.coordOffset2;
                        double coordOffset20 = targetEntity4.coordOffset20;
                        double coordOffset5 = targetEntity4.coordOffset5;
                        AxisAlignedBB boundingBox2 = SimulatedPlayer.processAction(targetEntity4, coordOffset5, coordOffset20, coordOffset2);
                        Vec3 vec2 = SimulatedPlayer.processAction(vec7, boundingBox2);
                        double coordinate43 = vec7.getCoordinate4(vec2);
                        double coordinate44 = vec7.getCoordinate4(vec);
                        if (this.checkCondition(coordinate43, coordinate44)) {
                            TimeHelper timer23 = this.timer2;
                            double value = this.settingDelayBetweenBacktrackPing.getValue();
                            if (timer23.hasReachedDouble(value) && this.canExecute5()) {
                                boolean enabled23 = ClientGlobals.moduleManager.packetHandler.isEnabled2();
                                if (this.checkEntityLivingBase(enabled23, targetEntity4) && this.checkEntityLivingBase3(targetEntity4)
                                )
                                {
                                    if (this.settingDynamicPing.isEnabled()) {
                                        if (ClientGlobals.moduleManager.packetHandler.isEnabled2()
                                        || vec7.getCoordinate4(vec2) > 3.0) {
                                            int indexOffset2 = this.settingPingDelay.getIndexOffset2();
                                            packet.applyEntityLivingBase(indexOffset2, targetEntity4, 3.0);
                                        }
                                    } else {
                                        int indexOffset22 = this.settingPingDelay.getIndexOffset2();
                                        packet.setIndexOffset(indexOffset22);
                                    }

                                    this.activeState9 = true;
                                    this.expanded2 = true;
                                    return;
                                }
                            }
                        }

                        this.visible2 = false;
                        if (this.activeState9) {
                            this.timer2.reset();
                            this.settingPingDelay.recalculate();
                            if (this.settingCalculateWithOwnPingPing.isEnabled()) {
                                double offset4 = (double)Math.max(0, this.settingPingDelay.getIndexOffset2() - RuntimeBridge.calculatePingDelta());
                                this.settingPingDelay.processAction(offset4);
                            }
                        }

                        this.activeState9 = false;
                        this.expanded2 = false;
                    }
                }

                return;
        }
    }

    @Override
    public void onClick(EventClick event) {
        if (this.targetEntity != null) {
            if (this.settingCancelHitsIfServerCancelsHit.isEnabled()) {
                EntityLivingBase targetEntity2 = this.targetEntity;
                if (!CombatTargetFilter.checkEntityLivingBase(1222936913, 29201, targetEntity2)) {
                    EntityLivingBase targetEntity3 = this.targetEntity;
                    if (!this.checkEntityLivingBase2(targetEntity3)) {
                        event.updateState();
                    }
                }
            }
        }
    }

    @Override
    public void onRenderEntity(EventRenderEntity event) {
        String selected = this.settingMode.getSelected();
        int index = selected.hashCode();
        byte state = -1;
        switch (index) {
            case -1766985991:
                if (selected.equals("LagTrack")) {
                    state = 3;
                }
                break;
            case -966060924:
                if (selected.equals("BackTrack")) {
                    state = 2;
                }
                break;
            case 2487698:
                if (selected.equals("Ping")) {
                    state = 0;
                }
                break;
            case 68985021:
                if (selected.equals("Gomme")) {
                    state = 4;
                }
                break;
            case 1896850514:
                if (selected.equals("ClassicBackTrack")) {
                    state = 1;
                }
        }

        if (state >= 0 && state <= 2) {
            if (this.targetEntity != null) {
                if (ClientGlobals.moduleManager.packetHandler.isEnabled2()) {
                    if (this.targetEntity.equals(event.getTargetEntity())) {
                        if (this.checkModeSetting(this.settingTargetESP)) {
                            boolean enabled = this.settingTarget.canExecute();
                            boolean enabled3 = this.settingTarget.canExecute2();
                            MovementHandler.processBoolean(enabled, enabled3);
                            RendererLivingEntity rendererLivingEntity = event.getRendererLivingEntity();
                            RunnableTask runnableTask = renderPass -> this.onRenderEntity5(event, (RendererLivingEntity)rendererLivingEntity, renderPass);
                            MovementHandler.applyRunnableTask(false, runnableTask);
                        }
                    }
                }
            }
        } else if (state == 3 || state == 4) {
            if (ClientGlobals.moduleManager.packetHandler.canExecute5()) {
                if (this.targetEntity != null && this.targetEntity.equals(event.getTargetEntity())) {
                    if (this.checkModeSetting(this.settingTargetESP)) {
                        boolean enabled4 = this.settingTarget.canExecute();
                        boolean enabled5 = this.settingTarget.canExecute2();
                        MovementHandler.processBoolean(enabled4, enabled5);
                        RendererLivingEntity rendererLivingEntity2 = event.getRendererLivingEntity();
                        RunnableTask runnableTask2 = renderPass2 -> this.onRenderEntity2(event, (RendererLivingEntity)rendererLivingEntity2, renderPass2);
                        MovementHandler.applyRunnableTask(false, runnableTask2);
                    }
                }

                if (this.targetEntity != null) {
                    if (minecraft.thePlayer != null) {
                        if (minecraft.thePlayer.equals(event.getTargetEntity())) {
                            if (this.checkModeSetting(this.settingPlayerESP)) {
                                if (minecraft.gameSettings.indexOffset64 != 0 || !this.settingOnlyInThirdPerson.isEnabled()) {
                                    boolean enabled6 = this.settingPlayer.canExecute();
                                    boolean enabled7 = this.settingPlayer.canExecute2();
                                    MovementHandler.processBoolean(enabled6, enabled7);
                                    RendererLivingEntity rendererLivingEntity3 = event.getRendererLivingEntity();
                                    MovementHandler.applyRunnableTask(false, renderPass3 -> this.onRenderEntity3(event, (RendererLivingEntity)rendererLivingEntity3, renderPass3));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPostNetworkPacket(EventSendPacket packet) {
        Packet packet2 = packet.getPacket();
        if (this.settingAbortCriterion.getSelected().equals("OnAttack") && this.targetEntity != null) {
            if (this.settingFixPacketOrder.isEnabled()) {
                boolean completed = false;
                if (packet2 instanceof C07PacketPlayerDigging) {
                    PlayerDiggingActionType playerDiggingActionTypeKind = ((C07PacketPlayerDigging)packet2).getPlayerDiggingActionType();
                    if (playerDiggingActionTypeKind == PlayerDiggingActionType.DROP_ALL_ITEMS) {
                        completed = true;
                    }
                    if (!completed) {
                        PlayerDiggingActionType playerDiggingActionTypeKind2 = ((C07PacketPlayerDigging)packet2).getPlayerDiggingActionType();
                        PlayerDiggingActionType playerDiggingActionTypeKind3 = PlayerDiggingActionType.DROP_ITEM;
                        if (playerDiggingActionTypeKind2 == playerDiggingActionTypeKind3) {
                            completed = true;
                        }
                        if (!completed) {
                            PlayerDiggingActionType playerDiggingActionTypeKind4 = ((C07PacketPlayerDigging)packet2).getPlayerDiggingActionType();
                            PlayerDiggingActionType playerDiggingActionTypeKind5 = PlayerDiggingActionType.RELEASE_USE_ITEM;
                            if (playerDiggingActionTypeKind4 == playerDiggingActionTypeKind5) {
                                completed = true;
                            }
                        }
                    }
                }
                if (!((!completed) && ((
                !(packet2 instanceof C16PacketClientStatus)
                || ((C16PacketClientStatus)packet2).getAutoSoupPerformRespawnType() != AutoSoupPerformRespawnType.OPEN_INVENTORY_ACHIEVEMENT
                )
                && (
                !(packet2 instanceof C0BPacketEntityAction)
                || ((C0BPacketEntityAction)packet2).getEntityPlayerAction() != EntityPlayerAction.OPEN_INVENTORY
                )
                && (
                !(packet2 instanceof C08PacketPlayerBlockPlacement)
                || ((C08PacketPlayerBlockPlacement)packet2).getEntityId() != 255
                || ((C08PacketPlayerBlockPlacement)packet2).getYawRotation2() != 0.0F
                || ((C08PacketPlayerBlockPlacement)packet2).getYawRotation() != 0.0F
                || ((C08PacketPlayerBlockPlacement)packet2).getYawRotation1() != 0.0F
                )
                && !(packet2 instanceof C0APacketAnimation)
                && !(packet2 instanceof CAnimateHandPacket)))) {
                    packet.setCancelled(true);
                }

                if (packet2 instanceof C02PacketUseEntity
                && ((C02PacketUseEntity)packet2).getUseEntityAction() == UseEntityAction.ATTACK
                && this.settingStopOnAttackMaxHurtTime.getValue() >= (double)this.targetEntity.hurtTime) {
                    this.visible2 = true;
                }

                String selected = this.settingMode.getSelected();
                switch (selected) {
                    case "LagTrack":
                        StringBuilder stringBuilder = new StringBuilder();
                        String text = stringBuilder.append(this.settingMaxLagPackets.getIndexOffset2()).append("").toString();
                        this.applyString4(text);
                        if (minecraft.theWorld != null
                        && minecraft.thePlayer != null
                        && minecraft.thePlayer.ticksExisted >= 10
                        && minecraft.theWorld.loadedEntityList != null) {
                            if (packet2 instanceof C02PacketUseEntity) {
                                this.activeState11 = true;
                            }

                            if (packet2 instanceof C03PacketPlayer && this.activeState11) {
                                this.activeState13 = true;
                            }

                            if (this.activeState7 && this.settingCountMode.getSelected().equals("MovePacket") && packet2 instanceof C03PacketPlayer
                            )
                            {
                                this.tickDelay3++;
                            }

                            boolean activeState72 = this.activeState7;
                            packet.setCancelled(activeState72);
                            this.activeState = !packet.isState();
                            return;
                        }

                        return;
                    case "Gomme":
                        if (packet2 instanceof C02PacketUseEntity) {
                            this.activeState5 = true;
                        }

                        if (this.activeState8) {
                            packet.setCancelled(true);
                        }

                        return;
                    default:
                        return;
                }
            }

            if (packet2 instanceof C02PacketUseEntity
            && ((C02PacketUseEntity)packet2).getUseEntityAction() == UseEntityAction.ATTACK
            && this.settingStopOnAttackMaxHurtTime.getValue() >= (double)this.targetEntity.hurtTime) {
                this.visible2 = true;
            }
        }

        String selected2 = this.settingMode.getSelected();
switch (selected2) {
            case "MovePacket":
                String text2 = new StringBuilder().append(this.settingMaxLagPackets.getIndexOffset2()).append("").toString();
                this.applyString4(text2);
                if (minecraft.theWorld != null
                            && minecraft.thePlayer != null
                            && minecraft.thePlayer.ticksExisted >= 10
                            && minecraft.theWorld.loadedEntityList != null) {
                                if (packet2 instanceof C02PacketUseEntity) {
                                    this.activeState11 = true;
                                }

                                if (packet2 instanceof C03PacketPlayer && this.activeState11) {
                                    this.activeState13 = true;
                                }

                                if (this.activeState7) {
                                    if (this.settingCountMode.getSelected().equals("MovePacket") && packet2 instanceof C03PacketPlayer) {
                                        this.tickDelay3++;
                                    }

                                    boolean activeState74 = this.activeState7;
                                    packet.setCancelled(activeState74);
                                    this.activeState = !packet.isState();
                                    return;
                                }

                                boolean activeState75 = this.activeState7;
                                packet.setCancelled(activeState75);
                                this.activeState = !packet.isState();
                                return;
                            }
                return;
            case "Gomme":
                if (packet2 instanceof C02PacketUseEntity) {
                                this.activeState5 = true;
                            }
                if (this.activeState8) {
                                packet.setCancelled(true);
                            }
                return;
            default:
                return;
        }
    }

    @Override
    public void onWorldEvent(EventWorldLoad event) {
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.targetEntity != null) {
            RenderUtils.updateState();
            boolean enabled = this.settingMode.getSelected().equals("Ping");
            if (!enabled) {
                enabled = this.settingMode.getSelected().equals("BackTrack");
            }

            boolean enabled3;
            if (enabled) {
                enabled3 = ClientGlobals.moduleManager.packetHandler.isEnabled2();
            } else {
                enabled3 = this.settingMode.getSelected().equals("ClassicBackTrack");
                if (enabled3) {
                    enabled3 = ClientGlobals.moduleManager.packetHandler.isEnabled2();
                }
            }

            boolean enabled4;
            if (!this.settingMode.getSelected().equals("LagTrack")) {
                enabled4 = this.settingMode.getSelected().equals("Gomme");
                if (enabled4) {
                    enabled4 = ClientGlobals.moduleManager.packetHandler.canExecute5();
                }
            } else {
                enabled4 = ClientGlobals.moduleManager.packetHandler.canExecute5();
            }

            boolean enabled5;
            if (!enabled3 && !enabled4) {
                enabled5 = false;
            } else {
                enabled5 = this.settingTargetESP.getSelected().equals("Classic");
            }

            if (enabled5) {
                boolean enabled6 = this.settingTarget.canExecute();
                boolean enabled7 = this.settingTarget.canExecute2();
                MovementHandler.processBoolean(enabled6, enabled7);
                RunnableTask runnableTask = renderPass -> this.applyRenderPass2(renderPass);
                MovementHandler.applyRunnableTask(false, runnableTask);
            }

            if (enabled4 && this.settingPlayerESP.getSelected().equals("Classic") && minecraft.thePlayer != null) {
                boolean enabled8 = minecraft.gameSettings.indexOffset64 != 0;
                if (!enabled8) {
                    enabled8 = !this.settingOnlyInThirdPerson.isEnabled();
                }

                if (enabled8) {
                    boolean enabled9 = this.settingPlayer.canExecute();
                    boolean enabled10 = this.settingPlayer.canExecute2();
                    MovementHandler.processBoolean(enabled9, enabled10);
                    RunnableTask runnableTask2 = renderPass2 -> this.applyRenderPass(renderPass2);
                    MovementHandler.applyRunnableTask(false, runnableTask2);
                }
            }

            RenderUtils.prepareScissor();
        }
    }

    @Override
    public void onPreTick() {
        this.targetEntity = this.getEntityLivingBase();
        if (this.settingStopBackTrackAtHurtTime.isEnabled()) {
            if (minecraft.thePlayer.hurtTime == 10) {
                this.setActive4(false);
            } else if (minecraft.thePlayer.hurtTime == 0) {
                this.setActive5(false);
            }
        }

        if (this.settingMode.getSelected().equals("ClassicBackTrack") && this.targetEntity != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String text = stringBuilder.append(Math.max(0, this.settingClassicBackTrackDelay.getIndexOffset2())).append(" ms").toString();
            this.applyString4(text);
            double posZ = this.targetEntity.posZ;
            double posY = this.targetEntity.posY;
            double posX = this.targetEntity.posX;
            AxisAlignedBB boundingBox = SimulatedPlayer.processAction(this.targetEntity, posX, posY, posZ);
            double coordOffset2 = this.targetEntity.coordOffset2;
            double coordOffset20 = this.targetEntity.coordOffset20;
            double coordOffset5 = this.targetEntity.coordOffset5;
            AxisAlignedBB boundingBox2 = SimulatedPlayer.processAction(this.targetEntity, coordOffset5, coordOffset20, coordOffset2);
            Vec3 vec2 = SimulatedPlayer.getVec3(boundingBox);
            Vec3 vec3 = SimulatedPlayer.getVec3(boundingBox2);
            Vec3 vec4 = minecraft.thePlayer.getVec35(1.0F);
            double offset = minecraft.thePlayer.getPositionVec12().xCoord
            + (minecraft.thePlayer.getPositionVec8().xCoord - minecraft.thePlayer.getPositionVec12().xCoord) / 3.0;
            double offset2 = minecraft.thePlayer.getPositionVec12().yCoord
            + (minecraft.thePlayer.getPositionVec8().yCoord - minecraft.thePlayer.getPositionVec12().yCoord) / 3.0;
            double offset3 = minecraft.thePlayer.getPositionVec12().zCoord
            + (minecraft.thePlayer.getPositionVec8().zCoord - minecraft.thePlayer.getPositionVec12().zCoord) / 3.0;
            AxisAlignedBB boundingBox3 = SimulatedPlayer.processAction(minecraft.thePlayer, offset, offset2, offset3);
            Vec3 vec5 = this.targetEntity.getVec35(1.0F);
            Vec3 vec = SimulatedPlayer.processAction(vec5, boundingBox3);
            boolean enabled;
            if (!this.settingOnlyWhenNeed.isEnabled()
            || vec5.getCoordinate4(vec) > 3.0
            || minecraft.thePlayer.hurtTime < 8 && minecraft.thePlayer.hurtTime > 3) {
                enabled = true;
            } else {
                enabled = false;
            }

            if (enabled && this.canExecute7()) {
                double coordinate4 = vec4.getCoordinate4(vec3);
                double coordinate42 = vec4.getCoordinate4(vec2);
                if (this.checkCondition(coordinate4, coordinate42)) {
                    EntityLivingBase targetEntity2 = this.targetEntity;
                    if (this.checkEntityLivingBase3(targetEntity2)) {
                        TimeHelper timer4 = this.timer;
                        long time = (long)this.settingClassicBackTrackDelay.getIndexOffset2();
                        if (!timer4.hasReached(time)) {
                            if (this.settingDynamicClassic.isEnabled()
                            && !ClientGlobals.moduleManager.packetHandler.isEnabled2()
                            && !(vec4.getCoordinate4(vec3) > 3.0)) {
                                return;
                            }

                            this.activeState10 = true;
                            return;
                        }
                    }
                }
            }

            if (ClientGlobals.moduleManager.packetHandler.isEnabled2()) {
                minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket8(new LatencyPacket());
            }

            this.activeState10 = false;
            this.timer.reset();
            this.settingClassicBackTrackDelay.recalculate();
        } else {
            this.activeState10 = false;
        }
    }

    @Override
    public void onPostTick() {
        this.targetEntity = this.getEntityLivingBase();
        String selected = this.settingMode.getSelected();
        switch (selected) {
            case "BackTrack":
                if (this.targetEntity != null) {
                    synchronized (this.coordOffsetArray) {
                        double[] coordOffsetArray2 = CombatUtil.getCoordOffsetArray(this.targetEntity, 0);
                        this.applyDoubleArray(coordOffsetArray2);
                    }
                }
                break;
            case "Gomme":
                StringBuilder stringBuilder = new StringBuilder();
                String text = stringBuilder.append(this.settingLagPackets.getValue()).append("").toString();
                this.applyString4(text);
                if (minecraft.getTimer().indexOffset < 1) {
                    return;
                }

                if (minecraft.theWorld != null) {
                    this.tickDelay2 = this.tickDelay2 + minecraft.getTimer().indexOffset;
                    boolean enabled = false;
                    if (this.targetEntity != null && minecraft.thePlayer != null && minecraft.theWorld != null) {
                        double posZ = this.targetEntity.posZ;
                        double posY = this.targetEntity.posY;
                        double posX = this.targetEntity.posX;
                        AxisAlignedBB boundingBox = SimulatedPlayer.processAction(this.targetEntity, posX, posY, posZ);
                        Vec3 vec = SimulatedPlayer.getVec3(boundingBox);
                        double coordOffset2 = this.targetEntity.coordOffset2;
                        double coordOffset20 = this.targetEntity.coordOffset20;
                        double coordOffset5 = this.targetEntity.coordOffset5;
                        AxisAlignedBB boundingBox2 = SimulatedPlayer.processAction(this.targetEntity, coordOffset5, coordOffset20, coordOffset2);
                        Vec3 vec2 = SimulatedPlayer.getVec3(boundingBox2);
                        Vec3 vec3 = minecraft.thePlayer.getVec35(1.0F);
                        EntityLivingBase targetEntity2 = this.targetEntity;
                        boolean activeState82 = this.activeState8;
                        boolean enabled3 = this.checkEntityLivingBase(activeState82, targetEntity2);
                        if (this.canExecute7() && this.canExecute5()) {
                            double coordinate4 = vec3.getCoordinate4(vec2);
                            double coordinate42 = vec3.getCoordinate4(vec);
                            if (this.checkCondition(coordinate4, coordinate42) && enabled3) {
                                EntityLivingBase targetEntity3 = this.targetEntity;
                                if (this.checkEntityLivingBase3(targetEntity3)) {
                                    double distance = (double)minecraft.thePlayer.getDistanceSqToEntity(this.targetEntity);
                                    if (distance < new Vec3(minecraft.thePlayer.prevPosX, minecraft.thePlayer.prevPosY, minecraft.thePlayer.prevPosZ)
                                    .getCoordinate4(
                                    new Vec3(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ)
                                    )) {
                                        enabled = true;
                                    }
                                }
                            }
                        }
                    }

                    if (enabled && !this.activeState5) {
                        double tickDelay22 = (double)this.tickDelay2;
                        if (!(tickDelay22 > this.settingLagPackets.getValue())) {
                            this.expanded2 = false;
                            this.activeState8 = true;
                            return;
                        }
                    }

                    this.visible2 = false;
                    if (minecraft.getNetHandlerPlayClient() != null && minecraft.getNetHandlerPlayClient().getNetworkManager() != null) {
                        minecraft.getNetHandlerPlayClient().getNetworkManager().handlePacket6(new LatencyPacket());
                    }

                    this.expanded2 = true;
                    this.activeState8 = false;
                    this.tickDelay2 = 0;
                    this.activeState5 = false;
                    return;
                }
        }
    }

    private void onRenderEntity2(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, RenderPass renderPass) {
        EntityLivingBase targetEntity2 = this.targetEntity;
        boolean enabled = this.settingTargetESP.getSelected().equals("PlayerModelArmor");
        this.onRenderEntity6(event, targetEntity2, rendererLivingEntity, enabled);
    }

    private void applyRenderPass(RenderPass renderPass) {
        RenderUtils.applyEntityLivingBase3(minecraft.thePlayer, this.settingClassicColorMe, (float)this.settingLineWidthMe.getValue(), 255, false);
    }

    private void onRenderEntity3(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, RenderPass renderPass) {
        boolean enabled = this.settingPlayerESP.getSelected().equals("PlayerModelArmor");
        this.onRenderEntity4(event, rendererLivingEntity, enabled);
    }

    private boolean checkModeSetting(ModeSetting setting) {
        return setting.getSelected().equals("PlayerModel") || setting.getSelected().equals("PlayerModelArmor");
    }

    private double getDouble() {
        if (ClientGlobals.moduleManager.killAura.isToggled()) {
            return ClientGlobals.moduleManager.killAura.reachDistance;
        } else {
            return !ClientGlobals.moduleManager.range.isToggled() ? 3.0 : ClientGlobals.moduleManager.range.getCoordinate();
        }
    }

    private boolean checkEntityLivingBase(boolean enabled, EntityLivingBase livingEntity) {
        Vec3 vec2 = minecraft.thePlayer.getVec35(1.0F);
        if (enabled) {
            if (this.settingAbortCriterion.getSelected().equals("None")) {
                return true;
            } else if (this.settingAbortCriterion.getSelected().equals("OnAttack") && !this.visible2) {
                return true;
            } else if (this.settingAbortCriterion.getSelected().equals("OnRange")) {
                Vec3 vec3 = SimulatedPlayer.getVec3(livingEntity, (short)7363, -1103504885);
                double coordinate4 = vec2.getCoordinate4(vec3);
                if (coordinate4 <= this.settingStopOnAttackRange.getValue()) {
                    double hurtTime = (double)livingEntity.hurtTime;
                    if (hurtTime <= this.settingStopOnAttackMaxHurtTime.getValue()) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            boolean enabled3 = false;
            enabled3 = true;
            boolean completed = false;
            if (this.settingAbortCriterion.getSelected().equals("OnRange")) {
                Vec3 vec4 = SimulatedPlayer.getVec3(livingEntity, (short)7363, -1103504885);
                double coordinate42 = vec2.getCoordinate4(vec4);
                if (coordinate42 <= this.settingStopOnAttackRange.getValue()) {
                    double hurtTime2 = (double)livingEntity.hurtTime;
                    if (hurtTime2 <= this.settingStopOnAttackMaxHurtTime.getValue()) {
                        enabled3 = false;
                        completed = true;
                    }
                }
                if (!completed) {
                    enabled3 = true;
                }
            }


            double coordOffset2 = livingEntity.coordOffset2;
            double coordOffset20 = livingEntity.coordOffset20;
            double coordOffset5 = livingEntity.coordOffset5;
            AxisAlignedBB boundingBox = SimulatedPlayer.processAction(livingEntity, coordOffset5, coordOffset20, coordOffset2);
            Vec3 vec = SimulatedPlayer.processAction(vec2, boundingBox);
            if (this.settingCheckStartDistance.isEnabled()) {
                double coordinate43 = vec2.getCoordinate4(vec);
                if (coordinate43 <= this.settingStartDistance.getValue()) {
                    return false;
                }
            }

            return enabled3;
        }
    }

    public boolean checkEntityLivingBase2(EntityLivingBase livingEntity) {
        double offset = minecraft.thePlayer.checkEntity4(livingEntity) ? 6.0 : Math.min(6.0, this.getDouble());
        return minecraft.thePlayer.getPositionVec8().getDouble2(livingEntity.coordOffset5, livingEntity.coordOffset20, livingEntity.coordOffset2) < offset;
    }

    private synchronized void setActive4(boolean enabled) {
        this.activeState12 = enabled;
    }

    public void onRenderEntity4(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, boolean enabled) {
        Vec3 positionVec8 = minecraft.thePlayer.getPositionVec8();
        event.applyEntityLivingBase(minecraft.thePlayer, rendererLivingEntity, positionVec8.xCoord, positionVec8.yCoord, positionVec8.zCoord, enabled);
    }

    private boolean isAbortCriterionVisible() {
        return !this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean checkEntityLivingBase3(EntityLivingBase livingEntity) {
        return minecraft.thePlayer.getPositionVec8().getDouble2(livingEntity.coordOffset5, livingEntity.coordOffset20, livingEntity.coordOffset2) < this.settingMaxHitRange.getDouble3();
    }

    private void onRenderEntity5(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, RenderPass renderPass) {
        EntityLivingBase targetEntity2 = this.targetEntity;
        boolean enabled = this.settingTargetESP.getSelected().equals("PlayerModelArmor");
        this.onRenderEntity6(event, targetEntity2, rendererLivingEntity, enabled);
    }

    private boolean checkCondition(double offset, double offset2) {
        return offset > offset2 + this.settingThreshold.getValue();
    }

    private boolean canExecute5() {
        if (!this.settingStopBackTrackAtHurtTime.isEnabled()) {
            this.setActive4(false);
            this.setActive5(false);
            return true;
        } else if (this.canExecute9() && this.canExecute8()) {
            return false;
        } else if (!this.canExecute9()) {
            return true;
        } else {
            double hurtTime = (double)minecraft.thePlayer.hurtTime;
            byte state = (byte)(hurtTime <= this.settingContinueWithBackTrackAtHurtTime.getValue() ? 1 : 0);
            if (state != 0) {
                this.setActive5(false);
            }

            return state != 0;
        }
    }

    public void applyDoubleArray(double[] coordArray) {
        this.coordOffsetArray[0] = coordArray[0];
        this.coordOffsetArray[1] = coordArray[1];
        this.coordOffsetArray[2] = coordArray[2];
    }

    private void processAction() {
        boolean activeState72 = this.activeState7;
        this.activeState7 = this.canExecute6();
        if (this.activeState13 && this.activeState11) {
            this.activeState11 = false;
            this.activeState13 = false;
            this.activeState7 = false;
        }

        if (!this.activeState7 && activeState72) {
            this.timer3.reset();
        }

        if (!this.activeState7) {
            this.tickDelay3 = 0;
        }
    }

    public void onRenderEntity6(EventRenderEntity event, EntityLivingBase livingEntity, RendererLivingEntity rendererLivingEntity, boolean enabled) {
        double offset;
        double offset2;
        double offset3;
        if (this.settingSmoothRendering.isEnabled() && livingEntity.positionVec3 != null && livingEntity.positionVec != null) {
            offset = livingEntity.positionVec.xCoord + (livingEntity.positionVec3.xCoord - livingEntity.positionVec.xCoord) * (double)minecraft.getTimer().scaleFactor6;
            offset2 = livingEntity.positionVec.yCoord + (livingEntity.positionVec3.yCoord - livingEntity.positionVec.yCoord) * (double)minecraft.getTimer().scaleFactor6;
            offset3 = livingEntity.positionVec.zCoord + (livingEntity.positionVec3.zCoord - livingEntity.positionVec.zCoord) * (double)minecraft.getTimer().scaleFactor6;
        } else {
            offset = livingEntity.positionVec5.xCoord + (livingEntity.positionVec4.xCoord - livingEntity.positionVec5.xCoord) * (double)minecraft.getTimer().scaleFactor6;
            offset2 = livingEntity.positionVec5.yCoord + (livingEntity.positionVec4.yCoord - livingEntity.positionVec5.yCoord) * (double)minecraft.getTimer().scaleFactor6;
            offset3 = livingEntity.positionVec5.zCoord + (livingEntity.positionVec4.zCoord - livingEntity.positionVec5.zCoord) * (double)minecraft.getTimer().scaleFactor6;
        }

        event.applyEntityLivingBase(livingEntity, rendererLivingEntity, offset, offset2, offset3, enabled);
    }

    private Vec3 getVec3(EntityLivingBase livingEntity) {
        return new Vec3((double)livingEntity.indexOffset2 / 32.0, (double)livingEntity.indexOffset3 / 32.0, (double)livingEntity.indexOffset / 32.0);
    }

    private boolean checkEntity(Entity entity) {
        if (entity instanceof EntityPlayerSP) {
            return false;
        } else {
            return !ChatUtil.checkEntity3(
            entity,
            BackTrackTargetEntity.PLAYER.isSelected(),
            BackTrackTargetEntity.MOB.isSelected(),
            BackTrackTargetEntity.ANIMAL.isSelected(),
            BackTrackTargetEntity.VILLAGER.isSelected(),
            BackTrackTargetEntity.ARMOR_STAND.isSelected(),
            true,
            true,
            true,
            
            true,
            true,
            true
            )
            ? false
            : entity != minecraft.thePlayer
            && (double)minecraft.thePlayer.getDistanceSqToEntity(entity) < this.settingPreAimRange.getValue();
        }
    }

    private EntityLivingBase getEntityLivingBase() {
        if (minecraft.thePlayer == null) {
            return null;
        } else if (minecraft.theWorld == null) {
            return null;
        } else {
            EntityLivingBase entityLivingBase = ChatUtil.getEntityLivingBase();
            if (!this.settingOnlyKillAura.isEnabled() && entityLivingBase == null) {
                double offset = 10000.0;

                for (Entity entity : (Iterable<Entity>) minecraft.theWorld.loadedEntityList) {
                    if (this.checkEntity(entity)
                    && offset > (double)minecraft.thePlayer.getDistanceSqToEntity(entity)) {
                        entityLivingBase = (EntityLivingBase)entity;
                        offset = (double)minecraft.thePlayer.getDistanceSqToEntity(entity);
                    }
                }
            }

            if (!this.settingNotWhileScaffold.isEnabled()) {
                return entityLivingBase;
            } else {
                return BlockCache.canExecute() ? null : entityLivingBase;
            }
        }
    }

    private void applyRenderPass2(RenderPass renderPass) {
        EntityLivingBase targetEntity2 = this.targetEntity;
        float lineWidthTarget = (float)this.settingLineWidthTarget.getValue();
        RenderUtils.applyEntityLivingBase3(targetEntity2, this.settingClassicColorTarget, lineWidthTarget, 255, this.settingSmoothRendering.isEnabled());
    }

    private synchronized void setActive5(boolean enabled) {
        this.activeState14 = enabled;
    }

    private boolean isCountModeVisible() {
        return this.settingMode.getSelected().equals("LagTrack");
    }

    private boolean isDelayBetweenBacktrackPingVisible() {
        return this.settingMode.getSelected().equals("Ping");
    }

    private boolean isLineWidthMeVisible() {
        return this.settingPlayerESP.getSelected().equals("Classic");
    }

    private boolean isDelayBetweenLagsVisible() {
        return this.settingMode.getSelected().equals("LagTrack");
    }

    private boolean isStopOnAttackRangeVisible() {
        return !this.settingAbortCriterion.getSelected().equals("OnAttack")
        ? false
        : !this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean isClassicBackTrackDelayVisible() {
        return this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean isStopOnAttackMaxHurtTimeVisible() {
        return this.settingAbortCriterion.getSelected().equals("None")
        ? false
        : !this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean isClassicColorMeVisible() {
        return this.settingPlayerESP.getSelected().equals("Classic");
    }

    private boolean isStopOnAttackRangeVisible2() {
        return !this.settingAbortCriterion.getSelected().equals("OnRange")
        ? false
        : !this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean isPlayerESPsVisible() {
        return !this.settingMode.getSelected().equals("BackTrack");
    }

    private boolean isPingDelayVisible() {
        return this.settingMode.getSelected().equals("Ping");
    }

    private boolean isMaxLagPacketsVisible() {
        return this.settingMode.getSelected().equals("LagTrack");
    }

    private boolean isPreAimRangeVisible() {
        return !this.settingOnlyKillAura.isEnabled();
    }

    private boolean isDelayBetweenBacktrackVisible() {
        return this.settingMode.getSelected().equals("BackTrack");
    }

    private boolean canExecute6() {
        EntityLivingBase targetEntity2 = this.targetEntity;
        if (targetEntity2 != null && minecraft.thePlayer != null && minecraft.theWorld != null) {
            double coordOffset5 = targetEntity2.coordOffset5;
            double coordOffset20 = targetEntity2.coordOffset20;
            double coordOffset2 = targetEntity2.coordOffset2;
            AxisAlignedBB axisAlignedBB = SimulatedPlayer.getAxisAlignedBB(targetEntity2, this.getVec3(targetEntity2));
            AxisAlignedBB boundingBox = SimulatedPlayer.processAction(targetEntity2, coordOffset5, coordOffset20, coordOffset2);
            Vec3 vec = SimulatedPlayer.getVec3(axisAlignedBB);
            Vec3 vec2 = SimulatedPlayer.getVec3(boundingBox);
            Vec3 vec3 = minecraft.thePlayer.getVec35(1.0F);
            boolean enabled = this.checkEntityLivingBase(this.activeState7, targetEntity2);
            if (this.settingCalculateWithOwnPingLagTrack.isEnabled()) {
                int index = Math.max(0, this.tickDelay3 * 50 - RuntimeBridge.calculatePingDelta());
                this.tickDelay1 = (int)((double)index / 50.0);
            } else {
                this.tickDelay1 = this.tickDelay3;
            }

            if (this.canExecute7()
            && this.canExecute5()
            && this.timer3.hasReachedDouble(this.settingDelayBetweenLags.getValue())
            && (this.activeState && this.activeState4 || this.activeState7)
            && enabled
            && this.checkCondition(vec3.getCoordinate4(vec2), vec3.getCoordinate4(vec))
            && this.checkEntityLivingBase3(targetEntity2)
            && this.tickDelay1 <= this.settingMaxLagPackets.getIndexOffset2()) {
                this.settingMaxLagPackets.recalculate();
                if (!this.activeState) {
                    return true;
                }

                if (!this.activeState4) {
                    return true;
                }

                this.tickDelay3 = 0;
                return true;
            }
        }

        this.visible2 = false;
        return false;
    }

    private boolean canExecute7() {
        return MouseUtils.checkCondition(0) || !this.settingClickCheck.isEnabled();
    }

    private boolean isLagPacketsVisible() {
        return this.settingMode.getSelected().equals("Gomme");
    }

    private boolean isClassicBackTrackDelayVisible2() {
        return this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean isLineWidthTargetVisible() {
        return this.settingTargetESP.getSelected().equals("Classic");
    }

    private synchronized boolean canExecute8() {
        return this.activeState12;
    }

    private boolean isTargetsVisible() {
        return !this.settingOnlyKillAura.isEnabled();
    }

    private boolean isPlayerVisible() {
        return !this.settingPlayerESP.getSelected().equals("None");
    }

    private boolean isDelayBetweenBacktrackVisible2() {
        return this.settingMode.getSelected().equals("BackTrack");
    }

    private boolean isDelayBetweenBacktrackPingVisible2() {
        return this.settingMode.getSelected().equals("Ping");
    }

    private boolean isStopOnAttackMaxHurtTimeVisible2() {
        return !this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean isContinueWithBackTrackAtHurtTimeVisible() {
        return this.settingMode.getSelected().equals("ClassicBackTrack") ? false : this.settingStopBackTrackAtHurtTime.isEnabled();
    }

    private boolean isDelayBetweenBacktrackVisible3() {
        return this.settingMode.getSelected().equals("BackTrack");
    }

    private boolean isDelayBetweenBacktrackVisible4() {
        return this.settingMode.getSelected().equals("BackTrack");
    }

    public boolean isExpanded2() {
        return this.expanded2;
    }

    private boolean isClassicBackTrackDelayVisible3() {
        return this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean isCountModeVisible2() {
        return this.settingMode.getSelected().equals("LagTrack");
    }

    private boolean isDelayBetweenBacktrackPingVisible3() {
        return this.settingMode.getSelected().equals("Ping");
    }

    private boolean isClassicColorTargetVisible() {
        return this.settingTargetESP.getSelected().equals("Classic");
    }

    private boolean isTargetVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private boolean isBackTrackTimeVisible() {
        return this.settingMode.getSelected().equals("BackTrack");
    }

    private boolean isSmoothRenderingVisible() {
        return !this.settingTargetESP.getSelected().equals("None");
    }

    private boolean isDelayBetweenBacktrackPingVisible4() {
        return !this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private boolean isStartDistanceVisible() {
        return !this.settingCheckStartDistance.isEnabled() ? false : !this.settingMode.getSelected().equals("ClassicBackTrack");
    }

    private synchronized boolean canExecute9() {
        return this.activeState14;
    }

    private boolean isDelayBetweenBacktrackVisible5() {
        return this.settingMode.getSelected().equals("BackTrack");
    }
}
