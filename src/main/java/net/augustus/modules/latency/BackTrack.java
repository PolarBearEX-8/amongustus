package net.augustus.modules.latency;

import java.awt.Color;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventGameLoop;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.events.EventRenderEntity;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.render.RenderPassTask;
import net.augustus.utils.network.PacketFlushMarker;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.events.EventClick;
import net.augustus.utils.input.MouseInputUtil;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.utils.network.ServerPingUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class BackTrack extends Module implements LoadWorldListener, PacketDelayListener, RenderListener, RenderEntityListener, GameLoopListener, TickListener, ClickListener {
   private final StringValue mode;
   private final RandomizedRangeSettings maxHitRange;
   private final DoubleValue threshold;
   private final BooleanValue cancelHitsIfServerCancelsHit;
   private final RandomizedRangeSettings classicBackTrackDelay;
   private final BooleanValue onlyWhenNeed;
   private final BooleanValue dynamicClassic;
   private final RangeSetting backTrackTime;
   private final RandomizedRangeSettings delayBetweenBacktrack;
   private final BooleanValue calculateWithOwnPing;
   private final BooleanValue forceDelay;
   private final BooleanValue predict;
   private final BooleanValue dynamic;
   private final RandomizedRangeSettings maxLagPackets;
   private final DoubleValue delayBetweenLags;
   public final StringValue countMode;
   private final BooleanValue calculateWithOwnPingLagTrack;
   private final DoubleValue lagPackets;
   private final RandomizedRangeSettings pingDelay;
   private final DoubleValue delayBetweenBacktrackPing;
   private final BooleanValue calculateWithOwnPingPing;
   private final BooleanValue dynamicPing;
   private final BooleanValue checkStartDistance;
   private final DoubleValue startDistance;
   private final StringValue abortCriterion;
   private final DoubleValue stopOnAttackRange;
   private final BooleanValue fixPacketOrder;
   private final DoubleValue stopOnAttackMaxHurtTime;
   private final BooleanValue stopBackTrackAtHurtTime;
   private final DoubleValue continueWithBackTrackAtHurtTime;
   private final BooleanValue notWhileScaffold;
   public final BooleanValue improve;
   private final BooleanValue onlyKillAura;
   private final TargetSelectionSettings targetSelection;
   private final DoubleValue preAimRange;
   private final BooleanValue clickCheck;
   private final SettingGroup targetESPs;
   public final StringValue targetESP;
   private final ColorSetting classicColor;
   private final DoubleValue lineWidthTarget;
   private final BooleanValue smoothRendering;
   public final BlurShadowSettings target;
   private final SettingGroup playerESPs;
   public final StringValue playerESP;
   private final BooleanValue onlyInThirdPerson;
   private final ColorSetting classicColor2;
   private final DoubleValue lineWidthMe;
   public final BlurShadowSettings player;
   private EntityLivingBase currentTarget;
   private boolean wasDelayingIncoming;
   private boolean attackedTarget;
   private boolean backtracking;
   private boolean receivedVelocity;
   private boolean waitingForHurtTime;
   private boolean wasBacktracking;
   private final TimeHelper backtrackDurationTimer;
   private final TimeHelper betweenBacktracksTimer;
   private int currentDelayMs;
   private final double[] predictedTargetPos;
   private final TimeHelper betweenLagsTimer;
   private int lagPacketCount;
   private int effectiveLagPackets;
   private boolean incomingReady;
   private boolean outgoingReady;
   private boolean attackSentInLag;
   private boolean movePacketSentAfterAttack;
   private boolean lagTrackActive;
   private int gommeLagTicks;
   private boolean gommeDelaying;
   private boolean gommeAttacked;
   private boolean pingModeActive;
   private boolean classicBacktrackActive;

   public BackTrack() {
       super("BackTrack", "Zurückverfolgung", new Color(110, 186, 9), Categorys.LATENCY);
      this.mode = new StringValue(this, "Mode", "BackTrack", new String[]{"BackTrack", "LagTrack", "Gomme", "Ping", "ClassicBackTrack"});
      RandomizedRangeSettings randomizedRangeSettings = new RandomizedRangeSettings(this, "MaxHitRange", 6.0, 6.0, 3.0, 10.0, 2);
      String str = "MaxBackTrackRangeMax";
      this.maxHitRange = randomizedRangeSettings.withRangeNames("MaxBackTrackRangeMin", str).withoutNotInARow();
      this.threshold = new DoubleValue(this, "Threshold", 0.125, 0.0, 0.2, 3);
      this.cancelHitsIfServerCancelsHit = new BooleanValue(this, "CancelHitsIfServerCancelsHit", true);
      RandomizedRangeSettings randomizedRangeSettings2 = (new RandomizedRangeSettings(this, "ClassicBackTrackDelay", 200.0, 250.0, 0.0, 5000.0, 0)).withoutNotInARow();
      String str3 = "MaxBackTrackDelay";
      this.classicBackTrackDelay = (RandomizedRangeSettings)randomizedRangeSettings2.withRangeNames("MinBackTrackDelay", str3).withVisibility(() -> this.isClassicDelayVisible());
      BooleanValue booleanValue = new BooleanValue(this, "OnlyWhenNeed", false);
      SettingVisibilityCondition condition2 = () -> {
         return this.mode.getSelectedValue().equals("ClassicBackTrack");
      };
      this.onlyWhenNeed = (BooleanValue)booleanValue.withVisibility(condition2);
      this.dynamicClassic = (BooleanValue)((BooleanValue)new BooleanValue(this, "DynamicClassic", false).withDisplayName("Dynamic")).withVisibility(() -> this.isClassicDynamicVisible());
      RangeSetting rangeSetting = new RangeSetting(this, "BackTrackTime", 200.0, 250.0, 0.0, 5000.0, 0);
      String str6 = "MaxBackTrackDelay";
      this.backTrackTime = rangeSetting.withBoundNames("MinBackTrackDelay", str6).visibleWhen(() -> this.isBackTrackTimeVisible());
      this.delayBetweenBacktrack = (RandomizedRangeSettings)((RandomizedRangeSettings)(new RandomizedRangeSettings(this, "DelayBetweenBacktrack", 50.0, 150.0, 0.0, 5000.0, 0)).withoutNotInARow().withDisplayNamePrefix("DelayBetweenBacktracks")).withVisibility(() -> this.isDelayBetweenBacktracksVisible());
      this.calculateWithOwnPing = (BooleanValue)((BooleanValue)new BooleanValue(this, "CalculateWithOwnPing", false).withDisplayName("Subtract ping from Delay")).withVisibility(() -> this.isSubtractPingVisible());
      this.forceDelay = (BooleanValue)new BooleanValue(this, "ForceDelay", false).withVisibility(() -> this.isForceDelayVisible());
      this.predict = (BooleanValue)new BooleanValue(this, "Predict", true).withVisibility(() -> this.isBackTrackPredictVisible());
      this.dynamic = (BooleanValue)new BooleanValue(this, "Dynamic", false).withVisibility(() -> this.isBackTrackDynamicVisible());
      this.maxLagPackets = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "MaxLagPackets", 2.0, 2.0, 0.0, 10.0, 0).withVisibility(() -> this.isLagTrackMaxPacketsVisible());
      this.delayBetweenLags = new DoubleValue(this, "DelayBetweenLags", 0.0, 0.0, 4000.0, 0).visibleWhen(() -> this.isDelayBetweenLagsVisible());
      this.countMode = (StringValue)new StringValue(this, "CountMode", "Tick", new String[]{"Tick", "MovePacket"}).withVisibility(() -> this.isLagTrackCountModeVisible());
      this.calculateWithOwnPingLagTrack = (BooleanValue)((BooleanValue)new BooleanValue(this, "CalculateWithOwnPingLagTrack", false).withDisplayName("Subtract ping from Delay")).withVisibility(() -> this.isLagTrackSubtractPingVisible());
      this.lagPackets = new DoubleValue(this, "LagPackets", 10.0, 1.0, 10.0, 0).named("MaxLagPackets").visibleWhen(() -> this.isGommeLagPacketsVisible());
      this.pingDelay = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "PingDelay", 150.0, 150.0, 0.0, 2000.0, 0)).withoutNotInARow().withVisibility(() -> this.isPingDelayVisible());
      this.delayBetweenBacktrackPing = new DoubleValue(this, "DelayBetweenBacktrackPing", 1200.0, 0.0, 5000.0, 0).named("DelayBetweenBacktracks").visibleWhen(() -> this.isDelayBetweenPingBacktracksVisible());
      this.calculateWithOwnPingPing = (BooleanValue)((BooleanValue)new BooleanValue(this, "CalculateWithOwnPingPing", false).withDisplayName("Subtract own ping from Delay")).withVisibility(() -> this.isSubtractPingInPingModeVisible());
      this.dynamicPing = (BooleanValue)((BooleanValue)new BooleanValue(this, "DynamicPing", false).withDisplayName("Dynamic")).withVisibility(() -> this.isDynamicPingVisible());
      this.checkStartDistance = (BooleanValue)new BooleanValue(this, "CheckStartDistance", false).withVisibility(() -> this.isCheckStartDistanceVisible());
      DoubleValue startDistanceSetting = new DoubleValue(this, "StartDistance", 3.0, 0.0, 6.0, 2);
      SettingVisibilityCondition condition20 = () -> {
         return this.checkStartDistance.getValue() && !this.mode.getSelectedValue().equals("ClassicBackTrack");
      };
      this.startDistance = startDistanceSetting.visibleWhen(condition20);
      StringValue setting = new StringValue(this, "AbortCriterion", "None", new String[]{"None", "OnAttack", "OnRange"});
      SettingVisibilityCondition condition21 = () -> {
         return !this.mode.getSelectedValue().equals("ClassicBackTrack");
      };
      this.abortCriterion = (StringValue)setting.withVisibility(condition21);
      this.stopOnAttackRange = new DoubleValue(this, "StopOnAttackRange", 2.95, 1.0, 6.0, 2).visibleWhen(() -> this.isStopOnAttackRangeVisible());
      this.fixPacketOrder = (BooleanValue)new BooleanValue(this, "FixPacketOrder", true).withVisibility(() -> this.isFixPacketOrderVisible());
      this.stopOnAttackMaxHurtTime = new DoubleValue(this, "StopOnAttackMaxHurtTime", 3.0, 2.0, 10.0, 0).visibleWhen(() -> this.isStopOnAttackMaxHurtTimeVisible());
      this.stopBackTrackAtHurtTime = (BooleanValue)new BooleanValue(this, "StopBackTrackAtHurtTime", false).withVisibility(() -> this.isStopAtHurtTimeVisible());
      DoubleValue doubleSetting = new DoubleValue(this, "ContinueWithBackTrackAtHurtTime", 0.0, 0.0, 10.0, 0);
      BooleanValue booleanValue18 = this.stopBackTrackAtHurtTime;
      DoubleValue doubleSetting2 = doubleSetting.visibleWhen(() -> booleanValue18.getValue());
      SettingVisibilityCondition condition27 = () -> {
         return !this.mode.getSelectedValue().equals("ClassicBackTrack") && this.stopBackTrackAtHurtTime.getValue();
      };
      this.continueWithBackTrackAtHurtTime = doubleSetting2.visibleWhen(condition27);
      this.notWhileScaffold = new BooleanValue(this, "NotWhileScaffold", true);
      this.improve = new BooleanValue(this, "Improve", false);
      this.onlyKillAura = (BooleanValue)(new BooleanValue(this, "OnlyKillAura", true)).markVisualOnly();
      TargetSelectionSettings targetSelectionSettings = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter())).withSorting();
      SettingVisibilityCondition condition28 = () -> {
         return !this.onlyKillAura.getValue();
      };
      this.targetSelection = targetSelectionSettings.withVisibility(condition28);
      this.preAimRange = (DoubleValue)new DoubleValue(this, "PreAimRange", 4.0, 0.0, 15.0, 1).visibleWhen(() -> this.isPreAimRangeVisible()).markVisualOnly();
      this.clickCheck = (BooleanValue)(new BooleanValue(this, "ClickCheck", false)).markVisualOnly();
      this.targetESPs = (SettingGroup)(new SettingGroup(this, "TargetESPs")).markVisualOnly();
      this.targetESP = (StringValue)(new StringValue(
            this, "TargetESP", "Classic", new String[]{"None", "Classic", "PlayerModel", "PlayerModelArmor"}, this.targetESPs
         ))
         .markVisualOnly();
      Color color2 = new Color(0, 255, 0, 0);
      SettingGroup targetESPs2 = this.targetESPs;
      Color color3 = color2;
      String str15 = "ClassicColorTarget";
      this.classicColor = (ColorSetting)((ColorSetting)((ColorSetting)new ColorSetting(this, str15, color3, targetESPs2).withDisplayName("ClassicColor")).withVisibility(() -> this.isTargetClassicColorVisible())).markVisualOnly();
      this.lineWidthTarget = (DoubleValue)new DoubleValue(this, "LineWidthTarget", 3.0, 0.0, 10.0, 0, this.targetESPs).named("LineWidth").visibleWhen(() -> this.isTargetLineWidthVisible()).markVisualOnly();
      BooleanValue booleanValue20 = new BooleanValue(this, "SmoothRendering", false, this.targetESPs);
      SettingVisibilityCondition condition32 = () -> {
         return !this.targetESP.getSelectedValue().equals("None");
      };
      this.smoothRendering = (BooleanValue)((BooleanValue)booleanValue20.withVisibility(condition32)).markVisualOnly();
      BlurShadowSettings blurShadowSettings = new BlurShadowSettings(this, "Target", this.targetESPs);
      SettingVisibilityCondition condition33 = () -> {
         return !this.targetESP.getSelectedValue().equals("None");
      };
      this.target = ((BlurShadowSettings)blurShadowSettings.withVisibility(condition33)).disableBlurByDefault();
      this.playerESPs = (SettingGroup)new SettingGroup(this, "PlayerESPs").visibleWhen(() -> this.isPlayerEspGroupVisible()).markVisualOnly();
      this.playerESP = (StringValue)(new StringValue(
            this, "PlayerESP", "Classic", new String[]{"None", "Classic", "PlayerModel", "PlayerModelArmor"}, this.playerESPs
         ))
         .markVisualOnly();
      this.onlyInThirdPerson = (BooleanValue)(new BooleanValue(this, "OnlyInThirdPerson", true, this.playerESPs)).markVisualOnly();
      Color color4 = new Color(0, 255, 0, 0);
      targetESPs2 = this.playerESPs;
      color3 = color4;
      str15 = "ClassicColorMe";
      this.classicColor2 = (ColorSetting)((ColorSetting)((ColorSetting)new ColorSetting(this, str15, color3, targetESPs2).withDisplayName("ClassicColor")).withVisibility(() -> this.isPlayerClassicColorVisible())).markVisualOnly();
      DoubleValue lineWidthMeSetting = new DoubleValue(this, "LineWidthMe", 3.0, 0.0, 10.0, 0, this.playerESPs).named("LineWidth");
      SettingVisibilityCondition condition36 = () -> {
         return this.playerESP.getSelectedValue().equals("Classic");
      };
      this.lineWidthMe = (DoubleValue)lineWidthMeSetting.visibleWhen(condition36).markVisualOnly();
      BlurShadowSettings blurShadowSettings2 = new BlurShadowSettings(this, "Player", this.playerESPs);
      SettingVisibilityCondition condition37 = () -> {
         return !this.playerESP.getSelectedValue().equals("None");
      };
      this.player = ((BlurShadowSettings)((BlurShadowSettings)blurShadowSettings2.withVisibility(condition37)).markAllVisualOnly()).disableBlurByDefault();
      this.currentTarget = null;
      this.wasDelayingIncoming = false;
      this.attackedTarget = false;
      this.backtracking = false;
      this.wasBacktracking = false;
      this.backtrackDurationTimer = new TimeHelper();
      this.betweenBacktracksTimer = new TimeHelper();
      this.predictedTargetPos = new double[3];
      this.betweenLagsTimer = new TimeHelper();
      this.lagPacketCount = 0;
      this.effectiveLagPackets = 0;
      this.attackSentInLag = false;
      this.movePacketSentAfterAttack = false;
      this.lagTrackActive = false;
      this.gommeLagTicks = 0;
      this.gommeDelaying = true;
      this.gommeAttacked = false;
      this.pingModeActive = true;
      this.classicBacktrackActive = false;
   }

   @Override
   public void onEnable() {
      this.attackedTarget = false;
      this.backtracking = false;
      this.delayBetweenBacktrack.nextValue();
      this.incomingReady = true;
      this.outgoingReady = true;
      this.lagTrackActive = false;
      this.gommeLagTicks = 0;
      this.gommeDelaying = false;
      this.currentDelayMs = DefaultRandomGenerator.instance.nextInt(this.backTrackTime.getMinValue(), this.backTrackTime.getMaxValue());
      this.maxHitRange.nextValue();
      this.maxLagPackets.nextValue();
      this.pingDelay.nextValue();
      this.pingModeActive = true;
   }

   @Override
   public void onDisable() {
      this.targetSelection.setCurrentTarget((Entity)null);
      this.currentTarget = null;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(31);
      this.toggleEventSubscription(16);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
      this.toggleEventSubscription(7, 1000000);
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.currentTarget = null;
      this.targetSelection.setCurrentTarget((Entity)null);
   }

   @Override
   public void onClick(EventClick event) {
      if (this.currentTarget != null) {
         if (this.cancelHitsIfServerCancelsHit.getValue()) {
            if (!EntityMovementPredictor.hasInvalidPositionHistory(this.currentTarget)) {
               if (!this.isWithinServerHitRange(this.currentTarget)) {
                  event.clearLeftClicks();
               }
            }
         }
      }
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      Packet packet = event.getPacket();
      EntityPlayerSP thePlayer = mc.thePlayer;
      WorldClient theWorld = mc.theWorld;
      if (thePlayer != null && packet instanceof S12PacketEntityVelocity) {
         if (((S12PacketEntityVelocity)packet).getEntityID() == thePlayer.getEntityId()) {
            this.setReceivedVelocity(true);
            this.setWaitingForHurtTime(true);
         }
      }

      if (this.wasDelayingIncoming && !ClientManagers.moduleManager.packetHandler.isDelayingIncoming()) {
         this.maxHitRange.nextValue();
      }

      this.wasDelayingIncoming = ClientManagers.moduleManager.packetHandler.isDelayingIncoming();
      switch (this.mode.getSelectedValue()) {
         case "ClassicBackTrack":
            if (this.dynamicClassic.getValue() && this.classicBacktrackActive) {
               double d = 3.0;
               event.setDelayedWithTarget(true, this.currentTarget, d);
            } else {
               event.setDelayed(this.classicBacktrackActive);
            }
            break;
         case "BackTrack":
            EntityLivingBase currentTarget3 = this.currentTarget;
            this.setSuffix(Math.max(0, this.currentDelayMs) + " ms");
            if (theWorld == null || thePlayer == null || thePlayer.ticksExisted < 10 || theWorld.loadedEntityList == null) {
               return;
            }

            if (currentTarget3 != null) {
               Vec3 serverEntityPos = this.getServerEntityPos(currentTarget3);
               double[] doubles;
               if (this.predict.getValue() && (currentTarget3.realPosX != serverEntityPos.xCoord || currentTarget3.realPosY != serverEntityPos.yCoord || currentTarget3.realPosZ != serverEntityPos.zCoord)) {
                  synchronized (this.predictedTargetPos) {
                     doubles = this.predictedTargetPos;
                  }
               } else {
                  doubles = new double[]{currentTarget3.realPosX, currentTarget3.realPosY, currentTarget3.realPosZ};
               }

               AxisAlignedBB box = RotationUtil.createEntityBoxAt(currentTarget3, serverEntityPos);
               double d2 = doubles[0];
               double d3 = doubles[1];
               AxisAlignedBB box2 = RotationUtil.createEntityBoxAt(currentTarget3, d2, d3, doubles[2]);
               Vec3 nearestPointInBox = RotationUtil.getNearestPointInBox(thePlayer, box);
               Vec3 nearestPointInBox2 = RotationUtil.getNearestPointInBox(thePlayer, box2);
               Vec3 positionEyes = thePlayer.getPositionEyes(1.0F);
               if (this.passesClickCheck() && this.checkHurtTimeCondition(thePlayer)) {
                  if (this.betweenBacktracksTimer.reached((long)this.delayBetweenBacktrack.getLastIntValue())) {
                     if (this.checkAbortAndStartDistance(thePlayer, ClientManagers.moduleManager.packetHandler.isDelayingIncoming(), currentTarget3)) {
                        d3 = positionEyes.distanceTo(nearestPointInBox2);
                        if (this.exceedsDistanceThreshold(d3, positionEyes.distanceTo(nearestPointInBox))) {
                           if (this.isWithinMaxHitRange(thePlayer, currentTarget3)) {
                              long currentDelayMs2 = (long)this.currentDelayMs;
                              if (!this.backtrackDurationTimer.reached(currentDelayMs2)) {
                                 if (this.dynamic.getValue()) {
                                    if (ClientManagers.moduleManager.packetHandler.isDelayingIncoming() || positionEyes.distanceTo(nearestPointInBox2) > 3.0) {
                                       event.setDelayedWithTarget(true, currentTarget3, 3.0);
                                    }
                                 } else {
                                    event.setDelayed(true);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            this.backtracking = event.isDelayed();
            if (event.isDelayed() && !this.wasBacktracking) {
               this.delayBetweenBacktrack.nextValue();
            }

            if (!event.isDelayed()) {
               this.attackedTarget = false;
               if (this.wasBacktracking || this.currentDelayMs <= 0) {
                  if (this.calculateWithOwnPing.getValue()) {
                     this.currentDelayMs = Math.max(0, DefaultRandomGenerator.instance.nextInt(this.backTrackTime.getMinValue(), this.backTrackTime.getMaxValue()) - ServerPingUtil.getPing());
                  } else {
                     this.currentDelayMs = DefaultRandomGenerator.instance.nextInt(this.backTrackTime.getMinValue(), this.backTrackTime.getMaxValue());
                  }

                  this.betweenBacktracksTimer.reset();
               }

               this.backtrackDurationTimer.reset();
            }

            this.wasBacktracking = event.isDelayed();
            break;
         case "LagTrack":
            if (theWorld == null || thePlayer == null || thePlayer.ticksExisted < 10 || theWorld.loadedEntityList == null) {
               return;
            }

            this.updateLagTrackState();
            event.setDelayed(this.lagTrackActive);
            this.backtracking = this.lagTrackActive;
            this.incomingReady = !event.isDelayed();
            break;
         case "Ping":
            EntityLivingBase currentTarget4 = this.currentTarget;
            this.setSuffix(this.pingDelay.getLastIntValue() + " ms");
            if (currentTarget4 != null && theWorld != null && thePlayer != null && thePlayer.ticksExisted >= 10 && theWorld.loadedEntityList != null && currentTarget4.interpolatedServerPos != null) {
               Vec3 positionEyes2 = thePlayer.getPositionEyes(1.0F);
               Vec3 vec = RotationUtil.clampPointToBox(positionEyes2, RotationUtil.createEntityBoxAt(currentTarget4, this.getServerEntityPos(currentTarget4)));
               double realPosZ = currentTarget4.realPosZ;
               double realPosY = currentTarget4.realPosY;
               Vec3 vec2 = RotationUtil.clampPointToBox(positionEyes2, RotationUtil.createEntityBoxAt(currentTarget4, currentTarget4.realPosX, realPosY, realPosZ));
               if (this.exceedsDistanceThreshold(positionEyes2.distanceTo(vec2), positionEyes2.distanceTo(vec))) {
                  if (this.betweenBacktracksTimer.reached(this.delayBetweenBacktrackPing.getValue()) && this.checkHurtTimeCondition(thePlayer)) {
                     if (this.checkAbortAndStartDistance(thePlayer, ClientManagers.moduleManager.packetHandler.isDelayingIncoming(), currentTarget4)) {
                        if (this.isWithinMaxHitRange(thePlayer, currentTarget4)) {
                           if (this.dynamicPing.getValue()) {
                              if (ClientManagers.moduleManager.packetHandler.isDelayingIncoming() || positionEyes2.distanceTo(vec2) > 3.0) {
                                 event.setIncomingDelayWithTarget(this.pingDelay.getLastIntValue(), currentTarget4, 3.0);
                              }
                           } else {
                              event.setIncomingDelayMs(this.pingDelay.getLastIntValue());
                           }

                           this.pingModeActive = true;
                           this.backtracking = true;
                           return;
                        }
                     }
                  }
               }

               this.attackedTarget = false;
               if (this.pingModeActive) {
                  this.betweenBacktracksTimer.reset();
                  this.pingDelay.nextValue();
                  if (this.calculateWithOwnPingPing.getValue()) {
                     this.pingDelay.setLastValue((double)Math.max(0, this.pingDelay.getLastIntValue() - ServerPingUtil.getPing()));
                  }
               }

               this.pingModeActive = false;
               this.backtracking = false;
            }
      }
   }

   private boolean checkAbortAndStartDistance(EntityPlayerSP player2, boolean flag, EntityLivingBase entity) {
      Vec3 positionEyes = player2.getPositionEyes(1.0F);
      if (flag) {
         if (this.abortCriterion.getSelectedValue().equals("None")) {
            return true;
         } else if (this.abortCriterion.getSelectedValue().equals("OnAttack") && !this.attackedTarget) {
            return true;
         } else if (!this.abortCriterion.getSelectedValue().equals("OnRange")) {
            return false;
         } else {
            return positionEyes.distanceTo(RotationUtil.getNearestPointFromEntity(player2, entity)) > this.stopOnAttackRange.getValue() || (double)entity.hurtTime > this.stopOnAttackMaxHurtTime.getValue();
         }
      } else {
         boolean flag2 = true;
         if (this.abortCriterion.getSelectedValue().equals("OnRange")) {
            flag2 = positionEyes.distanceTo(RotationUtil.getNearestPointFromEntity(player2, entity)) > this.stopOnAttackRange.getValue() || (double)entity.hurtTime > this.stopOnAttackMaxHurtTime.getValue();
         }

         double realPosZ = entity.realPosZ;
         double realPosY = entity.realPosY;
         Vec3 vec = RotationUtil.clampPointToBox(positionEyes, RotationUtil.createEntityBoxAt(entity, entity.realPosX, realPosY, realPosZ));
         return (!this.checkStartDistance.getValue() || positionEyes.distanceTo(vec) > this.startDistance.getValue()) && flag2;
      }
   }

   private boolean isWithinMaxHitRange(EntityPlayerSP player2, EntityLivingBase entity) {
      return player2.getServerPos().distanceTo(entity.realPosX, entity.realPosY, entity.realPosZ) < this.maxHitRange.getLastValue();
   }

   public boolean isWithinServerHitRange(EntityLivingBase entity) {
      double d = mc.thePlayer.canEntityBeSeen(entity) ? 6.0 : Math.min(6.0, this.getEffectiveReach());
      return mc.thePlayer.getServerPos().distanceTo(entity.realPosX, entity.realPosY, entity.realPosZ) < d;
   }

   @Override
   public void onEarlyTick() {
      if (this.stopBackTrackAtHurtTime.getValue()) {
         if (mc.thePlayer.hurtTime == 10) {
            this.setWaitingForHurtTime(false);
         } else if (mc.thePlayer.hurtTime == 0) {
            this.setReceivedVelocity(false);
         }
      }

      if (this.mode.getSelectedValue().equals("ClassicBackTrack") && this.currentTarget != null) {
         this.setSuffix(Math.max(0, this.classicBackTrackDelay.getLastIntValue()) + " ms");
         double posZ = this.currentTarget.posZ;
         double posY = this.currentTarget.posY;
         double posX = this.currentTarget.posX;
         AxisAlignedBB box = RotationUtil.createEntityBoxAt(this.currentTarget, posX, posY, posZ);
         double realPosZ = this.currentTarget.realPosZ;
         double realPosY = this.currentTarget.realPosY;
         double realPosX = this.currentTarget.realPosX;
         AxisAlignedBB box2 = RotationUtil.createEntityBoxAt(this.currentTarget, realPosX, realPosY, realPosZ);
         Vec3 nearestPointInBox = RotationUtil.getNearestPointInBox(box);
         Vec3 nearestPointInBox2 = RotationUtil.getNearestPointInBox(box2);
         Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
         double d = mc.thePlayer.getPrevServerPos().xCoord + (mc.thePlayer.getServerPos().xCoord - mc.thePlayer.getPrevServerPos().xCoord) / 3.0;
         double d2 = mc.thePlayer.getPrevServerPos().yCoord + (mc.thePlayer.getServerPos().yCoord - mc.thePlayer.getPrevServerPos().yCoord) / 3.0;
         double d3 = mc.thePlayer.getPrevServerPos().zCoord + (mc.thePlayer.getServerPos().zCoord - mc.thePlayer.getPrevServerPos().zCoord) / 3.0;
         AxisAlignedBB box3 = RotationUtil.createEntityBoxAt(mc.thePlayer, d, d2, d3);
         Vec3 positionEyes2 = this.currentTarget.getPositionEyes(1.0F);
         Vec3 vec = RotationUtil.clampPointToBox(positionEyes2, box3);
         if ((!this.onlyWhenNeed.getValue() || positionEyes2.distanceTo(vec) > 3.0 || mc.thePlayer.hurtTime < 8 && mc.thePlayer.hurtTime > 3) && this.passesClickCheck()) {
            if (this.exceedsDistanceThreshold(positionEyes.distanceTo(nearestPointInBox2), positionEyes.distanceTo(nearestPointInBox))) {
               EntityLivingBase currentTarget2 = this.currentTarget;
               if (this.isWithinMaxHitRange(mc.thePlayer, currentTarget2)) {
                  if (!this.backtrackDurationTimer.reached((long)this.classicBackTrackDelay.getLastIntValue())) {
                     if (!this.dynamicClassic.getValue() || ClientManagers.moduleManager.packetHandler.isDelayingIncoming() || positionEyes.distanceTo(nearestPointInBox2) > 3.0) {
                        this.classicBacktrackActive = true;
                     }

                     return;
                  }
               }
            }
         }

         if (ClientManagers.moduleManager.packetHandler.isDelayingIncoming()) {
            mc.getNetHandler().getNetworkManager().receivePacketAsync(new PacketFlushMarker());
         }

         this.classicBacktrackActive = false;
         this.backtrackDurationTimer.reset();
         this.classicBackTrackDelay.nextValue();
      } else {
         this.classicBacktrackActive = false;
      }
   }

   @Override
   public void onPreTick() {
      EntityLivingBase currentTarget2 = this.currentTarget;
      this.targetSelection.setCurrentTarget(currentTarget2);
      this.currentTarget = this.findTarget();
      switch (this.mode.getSelectedValue()) {
         case "BackTrack":
            if (this.currentTarget != null) {
               synchronized (this.predictedTargetPos) {
                  this.setPredictedTargetPos(PlayerUtil.predictTargetPosition(this.currentTarget, (byte)0));
               }
            }
            break;
         case "Gomme":
            this.setSuffix(this.lagPackets.getValue() + "");
            if (mc.getTimer().elapsedTicks >= 1 && mc.theWorld != null) {
               this.gommeLagTicks = this.gommeLagTicks + mc.getTimer().elapsedTicks;
               boolean flag = false;
               if (this.currentTarget != null && mc.thePlayer != null && mc.theWorld != null) {
                  double posZ = this.currentTarget.posZ;
                  double posY = this.currentTarget.posY;
                  double posX = this.currentTarget.posX;
                  Vec3 nearestPointInBox = RotationUtil.getNearestPointInBox(RotationUtil.createEntityBoxAt(this.currentTarget, posX, posY, posZ));
                  double realPosZ = this.currentTarget.realPosZ;
                  double realPosY = this.currentTarget.realPosY;
                  double realPosX = this.currentTarget.realPosX;
                  Vec3 nearestPointInBox2 = RotationUtil.getNearestPointInBox(RotationUtil.createEntityBoxAt(this.currentTarget, realPosX, realPosY, realPosZ));
                  Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
                  EntityLivingBase currentTarget3 = this.currentTarget;
                  boolean gommeDelaying2 = this.gommeDelaying;
                  boolean flag2 = this.checkAbortAndStartDistance(mc.thePlayer, gommeDelaying2, currentTarget3);
                  if (this.passesClickCheck()) {
                     if (this.checkHurtTimeCondition(mc.thePlayer)) {
                        if (this.exceedsDistanceThreshold(positionEyes.distanceTo(nearestPointInBox2), positionEyes.distanceTo(nearestPointInBox)) && flag2) {
                           EntityLivingBase currentTarget4 = this.currentTarget;
                           if (this.isWithinMaxHitRange(mc.thePlayer, currentTarget4)
                              && (double)mc.thePlayer.getDistanceToEntity(this.currentTarget)
                                 < new Vec3(mc.thePlayer.prevPosX, mc.thePlayer.prevPosY, mc.thePlayer.prevPosZ)
                                    .distanceTo(new Vec3(this.currentTarget.posX, this.currentTarget.posY, this.currentTarget.posZ))) {
                              flag = true;
                           }
                        }
                     }
                  }
               }

               if (flag && !this.gommeAttacked && !((double)this.gommeLagTicks > this.lagPackets.getValue())) {
                  this.backtracking = false;
                  this.gommeDelaying = true;
               } else {
                  this.attackedTarget = false;
                  if (mc.getNetHandler() != null && mc.getNetHandler().getNetworkManager() != null) {
                     mc.getNetHandler().getNetworkManager().sendPacket(new PacketFlushMarker());
                  }

                  this.backtracking = true;
                  this.gommeDelaying = false;
                  this.gommeLagTicks = 0;
                  this.gommeAttacked = false;
               }
            }
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      Packet packet = event.getPacket();
      EntityLivingBase currentTarget2 = this.currentTarget;
      if (this.abortCriterion.getSelectedValue().equals("OnAttack") && currentTarget2 != null) {
         if (this.fixPacketOrder.getValue()
            && (
               packet instanceof C07PacketPlayerDigging
                     && (
                        ((C07PacketPlayerDigging)packet).getStatus() == C07PacketPlayerDigging.Action.DROP_ALL_ITEMS
                           || ((C07PacketPlayerDigging)packet).getStatus() == C07PacketPlayerDigging.Action.DROP_ITEM
                           || ((C07PacketPlayerDigging)packet).getStatus() == C07PacketPlayerDigging.Action.RELEASE_USE_ITEM
                     )
                  || packet instanceof C16PacketClientStatus && ((C16PacketClientStatus)packet).getStatus() == C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT
                  || packet instanceof C0BPacketEntityAction
                     && ((C0BPacketEntityAction)packet).getAction() == net.minecraft.network.play.client.C0BPacketEntityAction.Action.OPEN_INVENTORY
                  || packet instanceof C08PacketPlayerBlockPlacement
                     && ((C08PacketPlayerBlockPlacement)packet).getPlacedBlockDirection() == 255
                     && ((C08PacketPlayerBlockPlacement)packet).getPlacedBlockOffsetX() == 0.0F
                     && ((C08PacketPlayerBlockPlacement)packet).getPlacedBlockOffsetY() == 0.0F
                     && ((C08PacketPlayerBlockPlacement)packet).getPlacedBlockOffsetZ() == 0.0F
                  || packet instanceof C0APacketAnimation
                  || packet instanceof C0APacketSwingHand
            )) {
            event.setDelayed(true);
         }

         if (packet instanceof C02PacketUseEntity
            && ((C02PacketUseEntity)packet).getAction() == net.minecraft.network.play.client.C02PacketUseEntity.Action.ATTACK
            && this.stopOnAttackMaxHurtTime.getValue() >= (double)currentTarget2.hurtTime) {
            this.attackedTarget = true;
         }
      }

      switch (this.mode.getSelectedValue()) {
         case "LagTrack":
            this.setSuffix(this.maxLagPackets.getLastIntValue() + "");
            EntityPlayerSP thePlayer = mc.thePlayer;
            WorldClient theWorld = mc.theWorld;
            if (theWorld == null || thePlayer == null || thePlayer.ticksExisted < 10 || theWorld.loadedEntityList == null) {
               return;
            }

            if (packet instanceof C02PacketUseEntity) {
               this.attackSentInLag = true;
            }

            if (packet instanceof C03PacketPlayer && this.attackSentInLag) {
               this.movePacketSentAfterAttack = true;
            }

            if (this.lagTrackActive && this.countMode.getSelectedValue().equals("MovePacket") && packet instanceof C03PacketPlayer) {
               this.lagPacketCount++;
            }

            event.setDelayed(this.lagTrackActive);
            this.outgoingReady = !event.isDelayed();
            break;
         case "Gomme":
            if (packet instanceof C02PacketUseEntity) {
               this.gommeAttacked = true;
            }

            if (this.gommeDelaying) {
               event.setDelayed(true);
            }
      }
   }

   @Override
   public void onGameLoop(EventGameLoop event) {
      switch (this.mode.getSelectedValue()) {
         case "BackTrack":
            long currentDelayMs2 = (long)this.currentDelayMs;
            if (this.backtrackDurationTimer.reached(currentDelayMs2) && this.forceDelay.getValue() && ClientManagers.moduleManager.packetHandler.isDelayingIncoming()) {
               mc.getNetHandler().getNetworkManager().receivePacketAsync(new PacketFlushMarker());
            }
            break;
         case "LagTrack":
            if (mc.theWorld == null || mc.thePlayer == null || mc.thePlayer.ticksExisted < 10 || mc.theWorld.loadedEntityList == null) {
               return;
            }

            if (this.countMode.getSelectedValue().equals("Tick")) {
               if (mc.getTimer().elapsedTicks >= 1 && this.effectiveLagPackets > this.maxLagPackets.getLastIntValue()) {
                  mc.getNetHandler().getNetworkManager().receivePacketAsync(new PacketFlushMarker());
               }

               this.lagPacketCount = this.lagPacketCount + mc.getTimer().elapsedTicks;
            }
            break;
         case "Gomme":
            event.setAllowSkipRender(true);
            if (this.gommeDelaying) {
               event.setSkipRender(true);
            }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.currentTarget != null) {
         RenderUtil.begin3DRender();
         boolean flag = (
               this.mode.getSelectedValue().equals("Ping") || this.mode.getSelectedValue().equals("BackTrack") || this.mode.getSelectedValue().equals("ClassicBackTrack")
            )
            && ClientManagers.moduleManager.packetHandler.isDelayingIncoming();
         boolean flag2 = (this.mode.getSelectedValue().equals("LagTrack") || this.mode.getSelectedValue().equals("Gomme")) && ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets();
         if (flag || flag2) {
            if (this.targetESP.getSelectedValue().equals("Classic")) {
               BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
               BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderClassicTargetBox(shaderRenderPass));
            }

            if (flag2 && this.playerESP.getSelectedValue().equals("Classic") && mc.thePlayer != null && (mc.gameSettings.thirdPersonView != 0 || !this.onlyInThirdPerson.getValue())) {
               BlurShadowManager.enableOneShotPass(this.player.isBlurActive(), this.player.isShadowActive());
               RenderPassTask renderPassTask = shaderRenderPass2 -> {
                  RenderUtil.drawEntityEspBox(mc.thePlayer, this.classicColor2, (float)this.lineWidthMe.getValue(), (short)255, false);
               };
               BlurShadowManager.submitRenderTask(false, renderPassTask);
            }
         }

         RenderUtil.end3DRender();
      }
   }

   @Override
   public void onRenderEntity(EventRenderEntity event) {
      switch (this.mode.getSelectedValue()) {
         case "Ping":
         case "ClassicBackTrack":
         case "BackTrack":
            if (this.currentTarget != null && ClientManagers.moduleManager.packetHandler.isDelayingIncoming() && this.currentTarget.equals(event.getEntity())) {
               if (this.isPlayerModelEspMode(this.targetESP)) {
                  BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
                  RendererLivingEntity renderer = event.getRenderer();
                  BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderBackTrackTargetPass(event, renderer, shaderRenderPass));
               }
            }
            break;
         case "LagTrack":
         case "Gomme":
            if (ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets()) {
               if (this.currentTarget != null && this.currentTarget.equals(event.getEntity())) {
                  if (this.isPlayerModelEspMode(this.targetESP)) {
                     BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
                     RendererLivingEntity rendererLivingEntity = event.getRenderer();
                     BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderLagTrackTargetPass(event, rendererLivingEntity, shaderRenderPass2));
                  }
               }

               if (this.currentTarget != null && mc.thePlayer != null && mc.thePlayer.equals(event.getEntity())) {
                  if (this.isPlayerModelEspMode(this.playerESP) && (mc.gameSettings.thirdPersonView != 0 || !this.onlyInThirdPerson.getValue())) {
                     BlurShadowManager.enableOneShotPass(this.player.isBlurActive(), this.player.isShadowActive());
                     RendererLivingEntity renderer3 = event.getRenderer();
                     BlurShadowManager.submitRenderTask(false, shaderRenderPass3 -> {
                        this.renderPlayerModel(event, renderer3, this.playerESP.getSelectedValue().equals("PlayerModelArmor"));
                     });
                  }
               }
            }
      }
   }

   private Vec3 getServerEntityPos(EntityLivingBase entity) {
      return new Vec3((double)entity.serverPosX / 32.0, (double)entity.serverPosY / 32.0, (double)entity.serverPosZ / 32.0);
   }

   private boolean passesClickCheck() {
      return MouseInputUtil.isButtonDown(0) || !this.clickCheck.getValue();
   }

   private boolean checkHurtTimeCondition(EntityPlayerSP player2) {
      if (!this.stopBackTrackAtHurtTime.getValue()) {
         this.setWaitingForHurtTime(false);
         this.setReceivedVelocity(false);
         return true;
      } else if (this.isReceivedVelocity() && this.isWaitingForHurtTime()) {
         return false;
      } else if (!this.isReceivedVelocity()) {
         return true;
      } else {
         boolean flag3 = (double)player2.hurtTime <= this.continueWithBackTrackAtHurtTime.getValue();
         if (flag3) {
            this.setReceivedVelocity(false);
         }

         return flag3;
      }
   }

   private boolean isPlayerModelEspMode(StringValue booleansSetting) {
      return booleansSetting.getSelectedValue().equals("PlayerModel") || booleansSetting.getSelectedValue().equals("PlayerModelArmor");
   }

   private double getEffectiveReach() {
      return ClientManagers.moduleManager.killAura.isToggled() ? ClientManagers.moduleManager.killAura.currentRange : (ClientManagers.moduleManager.range.isToggled() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
   }

   private void updateLagTrackState() {
      boolean lagTrackActive2 = this.lagTrackActive;
      this.lagTrackActive = this.shouldContinueLagTrack();
      if (this.movePacketSentAfterAttack && this.attackSentInLag) {
         this.attackSentInLag = false;
         this.movePacketSentAfterAttack = false;
         this.lagTrackActive = false;
      }

      if (!this.lagTrackActive && lagTrackActive2) {
         this.betweenLagsTimer.reset();
      }

      if (!this.lagTrackActive) {
         this.lagPacketCount = 0;
      }
   }

   private boolean shouldContinueLagTrack() {
      EntityLivingBase currentTarget2 = this.currentTarget;
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (currentTarget2 != null && thePlayer != null && mc.theWorld != null) {
         double realPosX = currentTarget2.realPosX;
         double realPosY = currentTarget2.realPosY;
         double realPosZ = currentTarget2.realPosZ;
         AxisAlignedBB box = RotationUtil.createEntityBoxAt(currentTarget2, this.getServerEntityPos(currentTarget2));
         AxisAlignedBB box2 = RotationUtil.createEntityBoxAt(currentTarget2, realPosX, realPosY, realPosZ);
         Vec3 nearestPointInBox = RotationUtil.getNearestPointInBox(box);
         Vec3 nearestPointInBox2 = RotationUtil.getNearestPointInBox(box2);
         Vec3 positionEyes = thePlayer.getPositionEyes(1.0F);
         boolean flag = this.checkAbortAndStartDistance(thePlayer, this.lagTrackActive, currentTarget2);
         if (this.calculateWithOwnPingLagTrack.getValue()) {
            this.effectiveLagPackets = (int)((double)Math.max(0, this.lagPacketCount * 50 - ServerPingUtil.getPing()) / 50.0);
         } else {
            this.effectiveLagPackets = this.lagPacketCount;
         }

         if (this.passesClickCheck() && this.checkHurtTimeCondition(thePlayer)) {
            if (this.betweenLagsTimer.reached(this.delayBetweenLags.getValue()) && (this.outgoingReady && this.incomingReady || this.lagTrackActive) && flag) {
               if (this.exceedsDistanceThreshold(positionEyes.distanceTo(nearestPointInBox2), positionEyes.distanceTo(nearestPointInBox))) {
                  if (this.isWithinMaxHitRange(thePlayer, currentTarget2) && this.effectiveLagPackets <= this.maxLagPackets.getLastIntValue()) {
                     this.maxLagPackets.nextValue();
                     if (this.outgoingReady && this.incomingReady) {
                        this.lagPacketCount = 0;
                     }

                     return true;
                  }
               }
            }
         }
      }

      this.attackedTarget = false;
      return false;
   }

   private EntityLivingBase findTarget() {
      if (mc.thePlayer != null && mc.theWorld != null && (!this.notWhileScaffold.getValue() || !BlockUtil.isScaffoldActiveAndValid())) {
         EntityLivingBase activeTarget = CombatTargetUtil.getActiveTarget();
         if (!this.onlyKillAura.getValue() && activeTarget == null) {
            EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
            entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelection.getTargetFilter());
            entityTargetSelector = entityTargetSelector.maxDistance(this.preAimRange.getValue()).filterFocus();
            TargetSelectionSettings targetSelection2 = this.targetSelection;
            Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
            double d = ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null
               ? ClientManagers.moduleManager.killAura.currentRange
               : (ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
            Optional optional = entityTargetSelector.sortBy(targetSelection2.createTargetComparatorKey(previousRotation, d)).findBest();
            return optional.isPresent() && optional.get() instanceof EntityLivingBase ? (EntityLivingBase)optional.get() : null;
         } else {
            return activeTarget;
         }
      } else {
         return null;
      }
   }

   public void renderTargetModel(EventRenderEntity event, EntityLivingBase entity, RendererLivingEntity rendererLivingEntity, boolean flag) {
      double d;
      double d2;
      double d3;
      if (this.smoothRendering.getValue() && entity.interpolatedServerPos != null && entity.prevInterpolatedServerPos != null) {
         d = entity.prevInterpolatedServerPos.xCoord + (entity.interpolatedServerPos.xCoord - entity.prevInterpolatedServerPos.xCoord) * (double)mc.getTimer().renderPartialTicks;
         d2 = entity.prevInterpolatedServerPos.yCoord + (entity.interpolatedServerPos.yCoord - entity.prevInterpolatedServerPos.yCoord) * (double)mc.getTimer().renderPartialTicks;
         d3 = entity.prevInterpolatedServerPos.zCoord + (entity.interpolatedServerPos.zCoord - entity.prevInterpolatedServerPos.zCoord) * (double)mc.getTimer().renderPartialTicks;
      } else {
         d = entity.prevRawServerPos.xCoord + (entity.rawServerPos.xCoord - entity.prevRawServerPos.xCoord) * (double)mc.getTimer().renderPartialTicks;
         d2 = entity.prevRawServerPos.yCoord + (entity.rawServerPos.yCoord - entity.prevRawServerPos.yCoord) * (double)mc.getTimer().renderPartialTicks;
         d3 = entity.prevRawServerPos.zCoord + (entity.rawServerPos.zCoord - entity.prevRawServerPos.zCoord) * (double)mc.getTimer().renderPartialTicks;
      }

      event.renderEntityModel(entity, rendererLivingEntity, d, d2, d3, flag);
   }

   public void renderPlayerModel(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, boolean flag) {
      Vec3 serverPos = mc.thePlayer.getServerPos();
      double zCoord = serverPos.zCoord;
      double yCoord = serverPos.yCoord;
      double xCoord = serverPos.xCoord;
      event.renderEntityModel(mc.thePlayer, rendererLivingEntity, xCoord, yCoord, zCoord, flag);
   }

   public boolean isBacktracking() {
      return this.backtracking;
   }

   public void setPredictedTargetPos(double[] doubles) {
      this.predictedTargetPos[0] = doubles[0];
      this.predictedTargetPos[1] = doubles[1];
      this.predictedTargetPos[2] = doubles[2];
   }

   private synchronized boolean isReceivedVelocity() {
      return this.receivedVelocity;
   }

   private synchronized void setReceivedVelocity(boolean flag) {
      this.receivedVelocity = flag;
   }

   private synchronized boolean isWaitingForHurtTime() {
      return this.waitingForHurtTime;
   }

   private synchronized void setWaitingForHurtTime(boolean flag) {
      this.waitingForHurtTime = flag;
   }

   private boolean exceedsDistanceThreshold(double d, double d2) {
      return d > d2 + this.threshold.getValue();
   }

   private void renderLagTrackTargetPass(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, ShaderRenderPass shaderRenderPass) {
      this.renderTargetModel(event, this.currentTarget, rendererLivingEntity, this.targetESP.getSelectedValue().equals("PlayerModelArmor"));
   }

   private void renderBackTrackTargetPass(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, ShaderRenderPass shaderRenderPass) {
      this.renderTargetModel(event, this.currentTarget, rendererLivingEntity, this.targetESP.getSelectedValue().equals("PlayerModelArmor"));
   }

   private void renderClassicTargetBox(ShaderRenderPass shaderRenderPass) {
      RenderUtil.drawEntityEspBox(this.currentTarget, this.classicColor, (float)this.lineWidthTarget.getValue(), (short)255, this.smoothRendering.getValue());
   }

   private boolean isPlayerClassicColorVisible() {
      return this.playerESP.getSelectedValue().equals("Classic");
   }

   private boolean isPlayerEspGroupVisible() {
      return !this.mode.getSelectedValue().equals("BackTrack");
   }

   private boolean isTargetLineWidthVisible() {
      return this.targetESP.getSelectedValue().equals("Classic");
   }

   private boolean isTargetClassicColorVisible() {
      return this.targetESP.getSelectedValue().equals("Classic");
   }

   private boolean isPreAimRangeVisible() {
      return !this.onlyKillAura.getValue();
   }

   private boolean isStopAtHurtTimeVisible() {
      return !this.mode.getSelectedValue().equals("ClassicBackTrack");
   }

   private boolean isStopOnAttackMaxHurtTimeVisible() {
      return !this.abortCriterion.getSelectedValue().equals("None") && !this.mode.getSelectedValue().equals("ClassicBackTrack");
   }

   private boolean isFixPacketOrderVisible() {
      return this.abortCriterion.getSelectedValue().equals("OnAttack") && !this.mode.getSelectedValue().equals("ClassicBackTrack");
   }

   private boolean isStopOnAttackRangeVisible() {
      return this.abortCriterion.getSelectedValue().equals("OnRange") && !this.mode.getSelectedValue().equals("ClassicBackTrack");
   }

   private boolean isCheckStartDistanceVisible() {
      return !this.mode.getSelectedValue().equals("ClassicBackTrack");
   }

   private boolean isDynamicPingVisible() {
      return this.mode.getSelectedValue().equals("Ping");
   }

   private boolean isSubtractPingInPingModeVisible() {
      return this.mode.getSelectedValue().equals("Ping");
   }

   private boolean isDelayBetweenPingBacktracksVisible() {
      return this.mode.getSelectedValue().equals("Ping");
   }

   private boolean isPingDelayVisible() {
      return this.mode.getSelectedValue().equals("Ping");
   }

   private boolean isGommeLagPacketsVisible() {
      return this.mode.getSelectedValue().equals("Gomme");
   }

   private boolean isLagTrackSubtractPingVisible() {
      return this.mode.getSelectedValue().equals("LagTrack");
   }

   private boolean isLagTrackCountModeVisible() {
      return this.mode.getSelectedValue().equals("LagTrack");
   }

   private boolean isDelayBetweenLagsVisible() {
      return this.mode.getSelectedValue().equals("LagTrack");
   }

   private boolean isLagTrackMaxPacketsVisible() {
      return this.mode.getSelectedValue().equals("LagTrack");
   }

   private boolean isBackTrackDynamicVisible() {
      return this.mode.getSelectedValue().equals("BackTrack");
   }

   private boolean isBackTrackPredictVisible() {
      return this.mode.getSelectedValue().equals("BackTrack");
   }

   private boolean isForceDelayVisible() {
      return this.mode.getSelectedValue().equals("BackTrack");
   }

   private boolean isSubtractPingVisible() {
      return this.mode.getSelectedValue().equals("BackTrack");
   }

   private boolean isDelayBetweenBacktracksVisible() {
      return this.mode.getSelectedValue().equals("BackTrack");
   }

   private boolean isBackTrackTimeVisible() {
      return this.mode.getSelectedValue().equals("BackTrack");
   }

   private boolean isClassicDynamicVisible() {
      return this.mode.getSelectedValue().equals("ClassicBackTrack");
   }

   private boolean isClassicDelayVisible() {
      return this.mode.getSelectedValue().equals("ClassicBackTrack");
   }

}
