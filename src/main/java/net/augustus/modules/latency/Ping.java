package net.augustus.modules.latency;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.render.Projectiles;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.events.EventRenderEntity;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.render.RenderPassTask;
import net.augustus.utils.network.PacketFlushMarker;
import net.augustus.events.listeners.AsyncTickListener;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.EventClick;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.MoveUtil;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.utils.world.BlockUtil;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.options.PingFlushConditionOption;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class Ping extends Module implements PacketDelayListener, LoadWorldListener, AttackEntityListener, RenderListener, RenderEntityListener, TickListener, MotionListener, ClickListener, AsyncTickListener {
   private final SettingGroup incomingGroup;
   private final PingDelayController incomingController;
   private final SettingGroup outgoingGroup;
   private final PingDelayController outgoingController;
   private final BooleanValue combatMode;
   private final RandomizedRangeSettings pingStartDelay;
   private final DoubleValue resetPingAtDistanceToTarget;
   private final BooleanValue failRate;
   private final RandomizedRangeSettings failInterval;
   private final BooleanValue limitDistanceToTarget;
   private final DoubleValue maxDistanceToTarget;
   public final TargetSelectionSettings combatTargetSelection;
   private final SettingGroup flushConditionsGroup;
   private final MultiOptionSetting flushConditions;
   private final SettingGroup targetESPs;
   public final StringValue targetESP;
   private final ColorSetting classicColor;
   private final DoubleValue lineWidthTarget;
   private final BooleanValue smoothRendering;
   public final BlurShadowSettings target;
   public final TargetSelectionSettings render;
   private final SettingGroup playerESPs;
   public final StringValue playerESP;
   private final ColorSetting classicColor2;
   private final DoubleValue lineWidthMe;
   public final BlurShadowSettings player;
   private final BooleanValue onlyInThirdPerson;
   private final TimeHelper flushMarkerTimer;
   private final List renderedTargets;
   private final AtomicBoolean combatPaused;
   private final TimeHelper combatStartTimer;
   private final AtomicBoolean outgoingDelayedSinceAttack;
   private int failHitCounter;
   private volatile EntityLivingBase lastAttackedTarget;
   private final TimeHelper targetTimeoutTimer;
   private final AtomicLong lastOutgoingDelayTimeMs;
   private final AtomicBoolean hurtTimeCounted;
   private volatile boolean targetMovedAway;
   private volatile boolean incomingProjectileDetected;

   public Ping() {
       super("Ping", "Verzögerung", new Color(235, 64, 52), Categorys.LATENCY);
      this.incomingGroup = new SettingGroup(this, "IncomingGroup");
      this.incomingController = new PingDelayController(this, "Incoming", this.incomingGroup);
      this.outgoingGroup = new SettingGroup(this, "OutgoingGroup");
      this.outgoingController = new PingDelayController(this, "Outgoing", this.outgoingGroup);
      this.combatMode = new BooleanValue(this, "CombatMode", false, this.outgoingGroup);
      RandomizedRangeSettings randomizedRangeSettings = (new RandomizedRangeSettings(this, "PingStartDelay", 100.0, 150.0, 0.0, 1000.0, 0, this.outgoingGroup)).withoutNotInARow();
      BooleanValue combatMode2 = this.combatMode;
      BooleanValue booleanValue = combatMode2;
      this.pingStartDelay = (RandomizedRangeSettings)randomizedRangeSettings.withVisibility(() -> booleanValue.getValue());
      DoubleValue resetPingAtDistanceToTarget2 = new DoubleValue(this, "ResetPingAtDistanceToTarget", 3.05, 3.0, 4.0, 2, this.outgoingGroup);
      combatMode2 = this.combatMode;
      BooleanValue booleanValue2 = combatMode2;
      this.resetPingAtDistanceToTarget = resetPingAtDistanceToTarget2.visibleWhen(() -> booleanValue2.getValue());
      BooleanValue booleanValue3 = new BooleanValue(this, "FailRate", false, this.outgoingGroup);
      combatMode2 = this.combatMode;
      BooleanValue booleanValue4 = combatMode2;
      this.failRate = (BooleanValue)booleanValue3.withVisibility(() -> booleanValue4.getValue());
      this.failInterval = (RandomizedRangeSettings)(new RandomizedRangeSettings(this, "FailInterval", 3.0, 4.0, 0.0, 10.0, 0, this.outgoingGroup)).withoutNotInARow().withVisibility(() -> this.isFailIntervalVisible());
      this.limitDistanceToTarget = new BooleanValue(this, "LimitDistanceToTarget", false, this.outgoingGroup);
      this.maxDistanceToTarget = new DoubleValue(this, "MaxDistanceToTarget", 6.0, 3.0, 15.0, 1, this.outgoingGroup);
      TargetSelectionSettings targetSelectionSettings = new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter(), this.outgoingGroup);
      combatMode2 = this.combatMode;
      BooleanValue booleanValue5 = combatMode2;
      this.combatTargetSelection = targetSelectionSettings.withVisibility(() -> booleanValue5.getValue());
      this.flushConditionsGroup = (SettingGroup)new SettingGroup(this, "FlushConditionsGroup").withDisplayName("FlushConditions");
      this.flushConditions = new MultiOptionSetting(this, "FlushConditions", PingFlushConditionOption.values(), this.flushConditionsGroup);
      this.targetESPs = new SettingGroup(this, "TargetESPs");
      this.targetESP = new StringValue(this, "TargetESP", "Classic", new String[]{"None", "Classic", "PlayerModel", "PlayerModelArmor"}, this.targetESPs);
      Color color2 = new Color(0, 255, 0, 255);
      SettingGroup targetESPs2 = this.targetESPs;
      Color color3 = color2;
      String str2 = "ClassicColorTarget";
      this.classicColor = (ColorSetting)((ColorSetting)new ColorSetting(this, str2, color3, targetESPs2).withDisplayName("ClassicColor")).withVisibility(() -> this.isTargetClassicColorVisible());
      this.lineWidthTarget = new DoubleValue(this, "LineWidthTarget", 3.0, 0.0, 10.0, 0, this.targetESPs).named("LineWidth").visibleWhen(() -> this.isTargetLineWidthVisible());
      combatMode2 = new BooleanValue(this, "SmoothRendering", false, this.targetESPs);
      this.smoothRendering = (BooleanValue)combatMode2.withVisibility(() -> this.isSmoothRenderingVisible());
      this.target = ((BlurShadowSettings)new BlurShadowSettings(this, "Target", this.targetESPs).withVisibility(() -> this.isTargetBlurShadowVisible())).disableBlurByDefault();
      TargetSelectionSettings targetSelectionSettings2 = new TargetSelectionSettings(this, "Render", TargetFilterPresets.createRenderFilter(), this.targetESPs);
      SettingVisibilityCondition condition10 = () -> {
         return !this.targetESP.getSelectedValue().equals("None");
      };
      this.render = targetSelectionSettings2.withVisibility(condition10);
      this.playerESPs = new SettingGroup(this, "PlayerESPs");
      this.playerESP = new StringValue(this, "PlayerESP", "Classic", new String[]{"None", "Classic", "PlayerModel", "PlayerModelArmor"}, this.playerESPs);
      color2 = new Color(0, 255, 0, 255);
      targetESPs2 = this.playerESPs;
      color3 = color2;
      str2 = "ClassicColorMe";
      this.classicColor2 = (ColorSetting)((ColorSetting)new ColorSetting(this, str2, color3, targetESPs2).withDisplayName("ClassicColor")).withVisibility(() -> this.isPlayerClassicColorVisible());
      this.lineWidthMe = new DoubleValue(this, "LineWidthMe", 3.0, 0.0, 10.0, 0, this.playerESPs).named("LineWidth").visibleWhen(() -> this.isPlayerLineWidthVisible());
      this.player = ((BlurShadowSettings)new BlurShadowSettings(this, "Player", this.playerESPs).withVisibility(() -> this.isPlayerBlurShadowVisible())).disableBlurByDefault();
      this.onlyInThirdPerson = new BooleanValue(this, "OnlyInThirdPerson", true, this.playerESPs);
      this.flushMarkerTimer = new TimeHelper();
      this.renderedTargets = new ArrayList();
      this.combatPaused = new AtomicBoolean(false);
      this.combatStartTimer = new TimeHelper();
      this.outgoingDelayedSinceAttack = new AtomicBoolean(false);
      this.failHitCounter = 0;
      this.lastAttackedTarget = null;
      this.targetTimeoutTimer = new TimeHelper();
      this.lastOutgoingDelayTimeMs = new AtomicLong(System.currentTimeMillis());
      this.hurtTimeCounted = new AtomicBoolean(false);
      this.targetMovedAway = false;
      this.incomingProjectileDetected = false;

      for (PingFlushConditionOption pingFlushConditionOption : PingFlushConditionOption.values()) {
         SettingGroup settingGroup = new SettingGroup(this, "Group" + pingFlushConditionOption.getName(), this.flushConditionsGroup);
         settingGroup = (SettingGroup)settingGroup.withDisplayName(pingFlushConditionOption.getName());
         SettingGroup settingGroup2 = settingGroup.visibleWhen(pingFlushConditionOption::isSelected);
         PingFlushConditionOption.setFlushDurationSetting(pingFlushConditionOption, new DoubleValue(this, "FlushDuration" + pingFlushConditionOption.getName(), 200.0, 20.0, 1000.0, 0, settingGroup2).named("FlushCondition"));
      }
   }

   @Override
   public void onEnable() {
      this.sendChatMessage("Ping");
      this.combatPaused.set(false);
      this.outgoingController.resetAccelerationState();
      this.incomingController.resetAccelerationState();
   }

   @Override
   public void onDisable() {
      this.sendChatMessage("Pong");
      this.renderedTargets.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(31);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(61);
      this.toggleEventSubscription(1);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      this.handleIncomingFlushTriggers(event.getPacket());
      if (this.isAnyFlushConditionActive()) {
         event.setIncomingDelayMs(this.incomingController.stepDecelerationAndGetDelay());
      } else {
         int n2 = this.incomingController.resumeAndGetDelay();
         if (n2 > 0) {
            event.setIncomingDelayMs(n2);
         }
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      this.handleOutgoingFlushTriggers(event.getPacket());
      boolean canDelay = !this.combatMode.getValue()
         || (!this.combatPaused.get() && this.combatStartTimer.reached((long)this.pingStartDelay.getLastIntValue()));
      if (canDelay && !this.isAnyFlushConditionActive()) {
         int n = this.outgoingController.resumeAndGetDelay();
         if (n > 0) {
            this.lastOutgoingDelayTimeMs.set(System.currentTimeMillis());
            this.hurtTimeCounted.set(false);
            this.targetMovedAway = false;
            event.setOutgoingDelayMs(n);
            this.outgoingDelayedSinceAttack.set(true);
         }

         return;
      }

      event.setOutgoingDelayMs(this.outgoingController.stepDecelerationAndGetDelay());
   }

   @Override
   public void onAsyncTick() {
      this.incomingController.updateAsync();
      this.outgoingController.updateAsync();
   }

   @Override
   public void onClick(EventClick event) {
      if (PingFlushConditionOption.HOLDING_BLOCKS.isSelected()
         && mc.thePlayer != null
         && mc.thePlayer.getCurrentEquippedItem() != null
         && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock) {
         PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.HOLDING_BLOCKS).reset();
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (mc.thePlayer != null) {
         if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.SPRINT_RESET) && mc.thePlayer.isSprinting() != mc.thePlayer.isServerSprintState()) {
            PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.SPRINT_RESET).reset();
         }

         if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.SPLASH_POTION)) {
            mc.theWorld.loadedEntityList.stream().filter(obj -> obj instanceof EntityPotion).forEach(obj2 -> {
               double distanceToEntity = (double)mc.thePlayer.getDistanceToEntity((Entity)obj2);
               double distance2DToEntity = (double)mc.thePlayer.getDistance2DToEntity((Entity)obj2);
               if (distanceToEntity < 2.0 && distance2DToEntity < 1.0) {
                  PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.SPLASH_POTION).reset();
               }
            });
         }

         if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.OPEN_GUI) && !mc.isNotInGui()) {
            PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.OPEN_GUI).reset();
         }

         if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.STANDING_STILL) && !MoveUtil.isMoving()) {
            PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.STANDING_STILL).reset();
         }

         if (BlockUtil.isScaffoldActiveAndValid() && PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.SCAFFOLD_TOGGLED)) {
            PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.SCAFFOLD_TOGGLED).reset();
         }

         if (PingFlushConditionOption.USE_ITEM.isSelected() && mc.thePlayer.isUsingItem()) {
            PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.USE_ITEM).reset();
         }

         if (mc.getNetHandler() != null && mc.getNetHandler().getNetworkManager() != null) {
            mc.getNetHandler().getNetworkManager().receivePacketAsync(new PacketFlushMarker());
            mc.getNetHandler().getNetworkManager().sendPacket(new PacketFlushMarker());
         }
      }
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      if (mc.theWorld != null && this.combatMode.getValue() && this.outgoingController.getConfiguredMaxDelay() > 0.0 && event.getTarget() instanceof EntityLivingBase) {
         this.lastAttackedTarget = (EntityLivingBase)event.getTarget();
         this.targetTimeoutTimer.reset();
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.lastAttackedTarget = null;
   }

   @Override
   public void onTick() {
      this.renderedTargets.clear();
      boolean flag = false;
      if (this.targetTimeoutTimer.reached(2000L)) {
         this.lastAttackedTarget = null;
      }

      EntityLivingBase lastAttackedTarget2 = this.lastAttackedTarget;
      if (this.failRate.getValue() && lastAttackedTarget2 != null) {
         if ((double)mc.thePlayer.getDistanceToEntity(lastAttackedTarget2) > 4.5 && this.failHitCounter >= this.failInterval.getLastIntValue()) {
            this.targetMovedAway = true;
         }

         if (lastAttackedTarget2.hurtTime == 10 && (System.currentTimeMillis() - this.lastOutgoingDelayTimeMs.get() <= 200L && !this.hurtTimeCounted.get() || this.failHitCounter >= this.failInterval.getLastIntValue() && this.targetMovedAway)) {
            this.failHitCounter++;
            this.hurtTimeCounted.set(true);
         }
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.PROJECTILE)) {
         this.incomingProjectileDetected = this.isProjectileHeadingTowardsPlayer();
      }

      boolean flag2 = true;

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (this.isRenderTarget(entity)) {
            this.renderedTargets.add((EntityLivingBase)entity);
         }

         if (this.isCombatTarget(entity)) {
            EntityLivingBase entity2 = (EntityLivingBase)entity;
            if (this.outgoingController.getConfiguredMaxDelay() > 0.0 && this.combatMode.getValue()) {
               double distanceToEntityBox = (double)mc.thePlayer.getDistanceToEntityBox(entity2);
               if (distanceToEntityBox < this.resetPingAtDistanceToTarget.getValue()) {
                  if (!this.combatPaused.get()) {
                     this.pingStartDelay.nextInt();
                  }

                  this.combatPaused.set(true);
                  mc.getNetHandler().getNetworkManager().sendPacket(new PacketFlushMarker());
                  this.combatStartTimer.reset();
                  flag = true;
               } else if (mc.thePlayer.getDistanceToEntityBox(entity2) < 15.0F) {
                  Vec3 vec = new Vec3(mc.thePlayer.lastTickPosX, mc.thePlayer.lastTickPosY + (double)mc.thePlayer.getEyeHeight(), mc.thePlayer.lastTickPosZ);
                  float collisionBorderSize = entity2.getCollisionBorderSize();
                  boolean flag3 = distanceToEntityBox > vec.distanceTo(RotationUtil.clampPointToBox(vec, entity2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize)));
                  float f = mc.thePlayer.width / 2.0F;
                  float collisionBorderSize2 = mc.thePlayer.getCollisionBorderSize();
                  AxisAlignedBB box2 = new AxisAlignedBB(
                        mc.thePlayer.getServerPos().xCoord - (double)f,
                        mc.thePlayer.getServerPos().yCoord,
                        mc.thePlayer.getServerPos().zCoord - (double)f,
                        mc.thePlayer.getServerPos().xCoord + (double)f,
                        mc.thePlayer.getServerPos().yCoord + (double)mc.thePlayer.height,
                        mc.thePlayer.getServerPos().zCoord + (double)f
                     )
                     .expand((double)collisionBorderSize2, (double)collisionBorderSize2, (double)collisionBorderSize2);
                  Vec3 vec3 = new Vec3(entity2.realPosX, entity2.realPosY, entity2.realPosZ);
                  Vec3 vec4 = RotationUtil.clampPointToBox(vec3, box2);
                  if (flag3 && vec3.distanceTo(vec4) < 5.0) {
                     this.combatPaused.set(true);
                     mc.getNetHandler().getNetworkManager().sendPacket(new PacketFlushMarker());
                     flag = true;
                  }
               }
            }

            if (flag2) {
               flag2 = this.isTargetOutsideMaxDistance(entity2);
            }
         }
      }

      if (!flag) {
         if (this.failRate.getValue()) {
            if (this.failHitCounter == this.failInterval.getLastIntValue()) {
               this.combatPaused.set(true);
               return;
            }

            if (this.failHitCounter > this.failInterval.getLastIntValue()) {
               this.failHitCounter = 0;
               this.failInterval.nextValue();
            }
         }

         this.combatPaused.set(flag2);
      }
   }

   private boolean isTargetOutsideMaxDistance(EntityLivingBase entity) {
      return this.limitDistanceToTarget.getValue() ? (double)mc.thePlayer.getDistanceToEntityBox(entity) >= this.maxDistanceToTarget.getValue() : false;
   }

   private boolean isProjectileHeadingTowardsPlayer() {
      if (this.outgoingController.getCurrentDelayMs() > 0) {
         ArrayList list = new ArrayList();
         EntityPlayerSP thePlayer = mc.thePlayer;
         Vec3 serverPos = thePlayer.getServerPos();
         if (serverPos == null) {
            return false;
         }

         Projectiles.simulateLoadedProjectiles(list, Math.min(serverPos.yCoord, thePlayer.posY));
         if (!list.isEmpty()) {
            float collisionBorderSize = thePlayer.getCollisionBorderSize();
            AxisAlignedBB box2 = thePlayer.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize).union(EntityBoundsUtil.createPlayerBoundingBox(serverPos, true));

            for (int i = 0; i < list.size(); i++) {
               ArrayList list2 = (ArrayList)list.get(i);

               for (int j = 0; j < list2.size(); j++) {
                  if (box2.isVecInside((Vec3)list2.get(j))) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.flushMarkerTimer.reached(3L)) {
         this.flushMarkerTimer.reset();
         mc.getNetHandler().getNetworkManager().receivePacketAsync(new PacketFlushMarker());
         mc.getNetHandler().getNetworkManager().sendPacket(new PacketFlushMarker());
      }

      RenderUtil.begin3DRender();
      if (ClientManagers.moduleManager.packetHandler.isDelayingIncoming() || ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets()) {
         if (this.targetESP.getSelectedValue().equals("Classic") && ClientManagers.moduleManager.packetHandler.isDelayingIncoming()) {
            BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass -> this.renderClassicTargetBoxes(shaderRenderPass));
         }

         if (ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets()
            && this.playerESP.getSelectedValue().equals("Classic")
            && mc.thePlayer != null
            && (mc.gameSettings.thirdPersonView != 0 || !this.onlyInThirdPerson.getValue())) {
            BlurShadowManager.enableOneShotPass(this.player.isBlurActive(), this.player.isShadowActive());
            BlurShadowManager.submitRenderTask(false, shaderRenderPass2 -> this.renderClassicPlayerBox(shaderRenderPass2));
         }
      }

      RenderUtil.end3DRender();
   }

   @Override
   public void onRenderEntity(EventRenderEntity event) {
      EntityLivingBase entity = event.getEntity();
      if (ClientManagers.moduleManager.packetHandler.isDelayingIncoming() && this.isRenderTarget(entity)) {
         if (this.isPlayerModelEspMode(this.targetESP)) {
            this.renderTargetModel(event, entity, this.targetESP.getSelectedValue().equals("PlayerModelArmor"));
         }
      }

      if (mc.thePlayer != null && ClientManagers.moduleManager.packetHandler.hasQueuedOutgoingPackets() && mc.thePlayer.equals(event.getEntity())) {
         if (this.isPlayerModelEspMode(this.playerESP) && (mc.gameSettings.thirdPersonView != 0 || !this.onlyInThirdPerson.getValue())) {
            this.renderPlayerModel(event, mc.thePlayer.getServerPos(), this.playerESP.getSelectedValue().equals("PlayerModelArmor"));
         }
      }
   }

   private void handleOutgoingFlushTriggers(Packet packet) {
      if (this.combatMode.getValue()
         && packet instanceof C02PacketUseEntity
         && ((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK
         && this.outgoingDelayedSinceAttack.compareAndSet(true, false)) {
         this.combatStartTimer.reset();
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.ATTACK) && packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK) {
         PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.ATTACK).reset();
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.INVENTORY_ACTIONS) && (packet instanceof C0EPacketClickWindow || packet instanceof C0DPacketCloseWindow)) {
         PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.INVENTORY_ACTIONS).reset();
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.BREAK_BLOCKS) && packet instanceof C07PacketPlayerDigging) {
         PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.BREAK_BLOCKS).reset();
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.PROJECTILE) && packet instanceof C03PacketPlayer && this.incomingProjectileDetected) {
         PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.PROJECTILE).reset();
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.SLOT_SWITCH) && packet instanceof C09PacketHeldItemChange) {
         PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.SLOT_SWITCH).reset();
      }

      if (packet instanceof C08PacketPlayerBlockPlacement && PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.PLACE_BLOCK)) {
         C08PacketPlayerBlockPlacement packet2 = (C08PacketPlayerBlockPlacement)packet;
         if (packet2.getPlacedBlockOffsetX() != 0.0F
            && packet2.getPlacedBlockOffsetY() != 0.0F
            && packet2.getPlacedBlockOffsetZ() != 0.0F
            && packet2.getPlacedBlockDirection() != 255) {
            PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.PLACE_BLOCK).reset();
         }
      }
   }

   private void handleIncomingFlushTriggers(Packet packet) {
      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.VELOCITY) && packet instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
         EntityPlayerSP thePlayer = mc.thePlayer;
         if (thePlayer != null && packet2.getEntityID() == thePlayer.getEntityId()) {
            PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.VELOCITY).reset();
         }
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.EXPLOSION) && packet instanceof S27PacketExplosion) {
         S27PacketExplosion packet3 = (S27PacketExplosion)packet;
         if (packet3.getMotionX() != 0.0F || packet3.getMotionY() != 0.0F || packet3.getMotionZ() != 0.0F) {
            PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.EXPLOSION).reset();
         }
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.TELEPORT) && packet instanceof S08PacketPlayerPosLook) {
         PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.TELEPORT).reset();
      }

      if (PingFlushConditionOption.isOptionSelected(PingFlushConditionOption.WORLD_CHANGE) && packet instanceof S07PacketRespawn) {
         PingFlushConditionOption.getFlushTimer(PingFlushConditionOption.WORLD_CHANGE).reset();
      }
   }

   private boolean isAnyFlushConditionActive() {

      for (PingFlushConditionOption pingFlushConditionOption : PingFlushConditionOption.values()) {
         if (!PingFlushConditionOption.getFlushTimer(pingFlushConditionOption).reached(PingFlushConditionOption.getFlushDurationSetting(pingFlushConditionOption).getValue())) {
            return true;
         }
      }

      return false;
   }

   private boolean isCombatTarget(Entity entity) {
      return this.combatTargetSelection.getTargetFilter().matches(entity);
   }

   private boolean isRenderTarget(Entity entity) {
      return this.render.getTargetFilter().matches(entity);
   }

   private boolean isPlayerModelEspMode(StringValue booleansSetting) {
      return booleansSetting.getSelectedValue().equals("PlayerModel") || booleansSetting.getSelectedValue().equals("PlayerModelArmor");
   }

   public void renderTargetModel(EventRenderEntity event, EntityLivingBase entity, boolean flag) {
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

      BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
      RendererLivingEntity renderer = event.getRenderer();
      double d4 = d3;
      double d5 = d2;
      double d6 = d;
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderTargetModelPass(event, entity, renderer, d6, d5, d4, flag, shaderRenderPass));
   }

   public void renderPlayerModel(EventRenderEntity event, Vec3 vec, boolean flag) {
      BlurShadowManager.enableOneShotPass(this.player.isBlurActive(), this.player.isShadowActive());
      RendererLivingEntity renderer = event.getRenderer();
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderPlayerModelPass(event, renderer, vec, flag, shaderRenderPass));
   }

   private static void renderPlayerModelPass(EventRenderEntity event, RendererLivingEntity rendererLivingEntity, Vec3 vec, boolean flag, ShaderRenderPass shaderRenderPass) {
      double zCoord = vec.zCoord;
      double yCoord = vec.yCoord;
      double xCoord = vec.xCoord;
      event.renderEntityModel(mc.thePlayer, rendererLivingEntity, xCoord, yCoord, zCoord, flag);
   }

   private static void renderTargetModelPass(
      EventRenderEntity event, EntityLivingBase entity, RendererLivingEntity rendererLivingEntity, double d, double d2, double d3, boolean flag, ShaderRenderPass shaderRenderPass
   ) {
      event.renderEntityModel(entity, rendererLivingEntity, d, d2, d3, flag);
   }

   private void renderClassicPlayerBox(ShaderRenderPass shaderRenderPass) {
      RenderUtil.drawEntityEspBox(mc.thePlayer, this.classicColor2, (float)this.lineWidthMe.getValue(), (short)255, true);
   }

   private void renderClassicTargetBoxes(ShaderRenderPass shaderRenderPass) {

      for (EntityLivingBase entity : (Iterable<EntityLivingBase>)(this.renderedTargets)) {
         if (!(entity instanceof EntityPlayerSP)) {
            RenderUtil.drawEntityEspBox(entity, this.classicColor, (float)this.lineWidthTarget.getValue(), (short)255, this.smoothRendering.getValue());
            BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
         }
      }
   }

   private boolean isPlayerBlurShadowVisible() {
      return !this.playerESP.getSelectedValue().equals("None");
   }

   private boolean isPlayerLineWidthVisible() {
      return this.playerESP.getSelectedValue().equals("Classic");
   }

   private boolean isPlayerClassicColorVisible() {
      return this.playerESP.getSelectedValue().equals("Classic");
   }

   private boolean isTargetBlurShadowVisible() {
      return !this.targetESP.getSelectedValue().equals("None");
   }

   private boolean isSmoothRenderingVisible() {
      return !this.targetESP.getSelectedValue().equals("None");
   }

   private boolean isTargetLineWidthVisible() {
      return this.targetESP.getSelectedValue().equals("Classic");
   }

   private boolean isTargetClassicColorVisible() {
      return this.targetESP.getSelectedValue().equals("Classic");
   }

   private boolean isFailIntervalVisible() {
      return this.failRate.getValue() && this.failRate.isVisible();
   }

}
