package net.augustus.modules.latency;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Consumer;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.render.ShaderRenderPass;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.RenderListener;
import net.augustus.settings.BlurShadowSettings;
import net.augustus.events.EventRenderEntity;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.render.RenderPassTask;
import net.augustus.utils.network.PacketFlushMarker;
import net.augustus.utils.simulation.EntityMovementPredictor;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.ColorSetting;
import net.augustus.events.EventPacketDelay;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.settings.options.PulseBlinkFlushConditionOption;
import net.augustus.utils.world.BlockUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.BlurShadowManager;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
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

public class PulseBlink extends Module implements PacketDelayListener, RenderListener, RenderEntityListener, TickListener, PacketListener, MotionListener {
   public final StringValue packetDirection;
   private final RandomizedRangeSettings lagBreakTicks;
   private final RandomizedRangeSettings lagTicks;
   private final StringValue modes;
   private final MultiOptionSetting flushConditions;
   private final SettingGroup targetESPs;
   public final StringValue targetESP;
   private final ColorSetting classicColor;
   private final DoubleValue lineWidthTarget;
   private final BooleanValue smoothRendering;
   public final BlurShadowSettings target;
   public final TargetSelectionSettings targetSelection;
   private final SettingGroup playerESPs;
   public final StringValue playerESP;
   private final ColorSetting classicColor2;
   private final DoubleValue lineWidthMe;
   private final BooleanValue onlyInThirdPerson;
   public final BlurShadowSettings player;
   private final ArrayList renderedTargets;
   private int movePacketCounter;
   private boolean delayingPackets;

   public PulseBlink() {
       super("PulseBlink", "Vodafone", Color.black, Categorys.LATENCY);
      this.packetDirection = new StringValue(this, "PacketDirection", "OnlyOutgoing", new String[]{"OnlyIncoming", "OnlyOutgoing", "Both"});
      this.lagBreakTicks = ((RandomizedRangeSettings)new RandomizedRangeSettings(this, "LagBreakTicks", 0.0, 0.0, 0.0, 20.0, 0).withDisplayNamePrefix("TickDelayBetweenLags")).withoutNotInARow();
      this.lagTicks = (new RandomizedRangeSettings(this, "LagTicks", 5.0, 6.0, 1.0, 50.0, 0)).withoutNotInARow();
      this.modes = (StringValue)new StringValue(this, "Modes", "DefaultCombat", new String[]{"Default", "DefaultCombat", "CombatOnly"}).withVisibility(() -> this.isCombatModesVisible());
      this.flushConditions = new MultiOptionSetting(this, "FlushConditions", PulseBlinkFlushConditionOption.values());
      this.targetESPs = new SettingGroup(this, "TargetESPs");
      this.targetESP = new StringValue(this, "TargetESP", "Classic", new String[]{"None", "Classic", "PlayerModel", "PlayerModelArmor"}, this.targetESPs);
      Color color2 = new Color(0, 255, 0, 255);
      SettingGroup targetESPs2 = this.targetESPs;
      Color color3 = color2;
      String str2 = "ClassicColorTarget";
      this.classicColor = (ColorSetting)((ColorSetting)new ColorSetting(this, str2, color3, targetESPs2).withDisplayName("ClassicColor")).withVisibility(() -> this.isTargetClassicColorVisible());
      DoubleValue lineWidthTargetSetting = new DoubleValue(this, "LineWidthTarget", 3.0, 0.0, 10.0, 0, this.targetESPs).named("LineWidth");
      SettingVisibilityCondition condition3 = () -> {
         return this.targetESP.getSelectedValue().equals("Classic");
      };
      this.lineWidthTarget = lineWidthTargetSetting.visibleWhen(condition3);
      this.smoothRendering = (BooleanValue)new BooleanValue(this, "SmoothRendering", false, this.targetESPs).withVisibility(() -> this.isSmoothRenderingVisible());
      this.target = ((BlurShadowSettings)new BlurShadowSettings(this, "Target", this.targetESPs).withVisibility(() -> this.isTargetBlurShadowVisible())).disableBlurByDefault();
      TargetSelectionSettings targetSelectionSettings = new TargetSelectionSettings(this, TargetFilterPresets.createRenderFilter(), this.targetESPs);
      SettingVisibilityCondition condition6 = () -> {
         return !this.targetESP.getSelectedValue().equals("None");
      };
      this.targetSelection = targetSelectionSettings.withVisibility(condition6);
      this.playerESPs = new SettingGroup(this, "PlayerESPs");
      this.playerESP = new StringValue(this, "PlayerESP", "Classic", new String[]{"None", "Classic", "PlayerModel", "PlayerModelArmor"}, this.playerESPs);
      color2 = new Color(0, 255, 0, 255);
      targetESPs2 = this.playerESPs;
      color3 = color2;
      str2 = "ClassicColorMe";
      this.classicColor2 = (ColorSetting)((ColorSetting)new ColorSetting(this, str2, color3, targetESPs2).withDisplayName("ClassicColor")).withVisibility(() -> this.isPlayerClassicColorVisible());
      this.lineWidthMe = new DoubleValue(this, "LineWidthMe", 3.0, 0.0, 10.0, 0, this.playerESPs).named("LineWidth").visibleWhen(() -> this.isPlayerLineWidthVisible());
      this.onlyInThirdPerson = new BooleanValue(this, "OnlyInThirdPerson", true, this.playerESPs);
      BlurShadowSettings blurShadowSettings2 = new BlurShadowSettings(this, "Player", this.playerESPs);
      SettingVisibilityCondition condition9 = () -> {
         return !this.playerESP.getSelectedValue().equals("None");
      };
      this.player = ((BlurShadowSettings)blurShadowSettings2.withVisibility(condition9)).disableBlurByDefault();
      this.renderedTargets = new ArrayList();
      this.movePacketCounter = 0;
      this.delayingPackets = true;
   }

   @Override
   public void onEnable() {
      this.rerollLagDurations();
      this.delayingPackets = false;
   }

   public static void noopHook() {
   }

   @Override
   public void onDisable() {
      this.renderedTargets.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(31);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(18);
   }

   @Override
   public void onTick() {
      this.renderedTargets.clear();
      EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
      entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelection.getTargetFilter());
      entityTargetSelector.forEach(obj -> this.renderedTargets.add((EntityLivingBase)obj));
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (mc.thePlayer != null) {
         if (PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.SPRINT_RESET) && mc.thePlayer.isSprinting() != mc.thePlayer.isServerSprintState()) {
            this.flushAndResetPulse();
         }

         if (PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.SPLASH_POTION)) {
            mc.theWorld.loadedEntityList.stream().filter(obj -> obj instanceof EntityPotion).forEach(obj2 -> this.checkNearbySplashPotion((EntityPotion)obj2));
         }
      }
   }

   @Override
   public void onPreTick() {
      if (mc.thePlayer != null
         && (!PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.STANDING_STILL) || MoveUtil.isMoving())
         && (!BlockUtil.isScaffoldActiveAndValid() || !PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.SCAFFOLD_TOGGLED))) {
         if (this.delayingPackets) {
            if (this.movePacketCounter >= this.lagTicks.getLastIntValue()) {
               this.delayingPackets = false;
               if (mc.getNetHandler() != null && mc.getNetHandler().getNetworkManager() != null) {
                  mc.getNetHandler().getNetworkManager().receivePacketAsync(new PacketFlushMarker());
               }

               this.rerollLagDurations();
            }
         } else if (this.movePacketCounter >= this.lagBreakTicks.getLastIntValue()) {
            this.delayingPackets = true;
            this.rerollLagDurations();
         }

         if (this.packetDirection.getSelectedValue().equals("OnlyOutgoing")) {
            if (this.shouldFlushForCombatMode()) {
               if (mc.getNetHandler() != null && mc.getNetHandler().getNetworkManager() != null) {
                  mc.getNetHandler().getNetworkManager().receivePacketAsync(new PacketFlushMarker());
               }

               this.flushAndResetPulse();
            }
         }
      } else {
         this.delayingPackets = false;
         this.movePacketCounter = 0;
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (event.getPacket() instanceof C03PacketPlayer) {
         this.movePacketCounter++;
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (!this.packetDirection.getSelectedValue().equals("OnlyIncoming") && thePlayer != null && mc.theWorld != null) {
         Packet packet = event.getPacket();
         if (packet instanceof C02PacketUseEntity && !this.modes.getSelectedValue().equals("Default") && this.packetDirection.getSelectedValue().equals("OnlyOutgoing") && this.delayingPackets) {
            this.flushAndResetPulse();
         }

         if (PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.INVENTORY_ACTIONS) && (packet instanceof C0EPacketClickWindow || packet instanceof C0DPacketCloseWindow)) {
            this.flushAndResetPulse();
         }

         if (PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.BREAK_BLOCKS) && packet instanceof C07PacketPlayerDigging) {
            this.flushAndResetPulse();
         }

         if (PulseBlinkFlushConditionOption.USE_ITEM.isSelected() && thePlayer.isUsingItem()) {
            this.flushAndResetPulse();
         }

         if (PulseBlinkFlushConditionOption.OPEN_GUI.isSelected() && !mc.isNotInGui()) {
            this.flushAndResetPulse();
         }

         if (PulseBlinkFlushConditionOption.SLOT_SWITCH.isSelected() && packet instanceof C09PacketHeldItemChange) {
            this.flushAndResetPulse();
         }

         if (packet instanceof C08PacketPlayerBlockPlacement && PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.PLACE_BLOCK)) {
            C08PacketPlayerBlockPlacement packet2 = (C08PacketPlayerBlockPlacement)packet;
            if (packet2.getPlacedBlockOffsetX() != 0.0F
               && packet2.getPlacedBlockOffsetY() != 0.0F
               && packet2.getPlacedBlockOffsetZ() != 0.0F
               && packet2.getPlacedBlockDirection() != 255) {
               this.flushAndResetPulse();
            }
         }

         if (this.delayingPackets) {
            event.setDelayed(true);
         }
      }
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      Packet packet = event.getPacket();
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.VELOCITY) && packet instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
         if (thePlayer != null && packet2.getEntityID() == thePlayer.getEntityId()) {
            this.flushAndResetPulse();
         }
      }

      if (PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.EXPLOSION) && packet instanceof S27PacketExplosion) {
         S27PacketExplosion packet3 = (S27PacketExplosion)packet;
         if (packet3.getMotionX() != 0.0F || packet3.getMotionY() != 0.0F || packet3.getMotionZ() != 0.0F) {
            this.flushAndResetPulse();
         }
      }

      if (PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.TELEPORT) && packet instanceof S08PacketPlayerPosLook) {
         this.flushAndResetPulse();
      }

      if (PulseBlinkFlushConditionOption.isOptionSelected(PulseBlinkFlushConditionOption.WORLD_CHANGE) && packet instanceof S07PacketRespawn) {
         this.flushAndResetPulse();
      }

      if (!this.packetDirection.getSelectedValue().equals("OnlyOutgoing") && thePlayer != null && mc.theWorld != null) {
         if (this.delayingPackets) {
            event.setDelayed(true);
         }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
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
      if (ClientManagers.moduleManager.packetHandler.isDelayingIncoming() && this.isValidEspTarget(entity)) {
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

   private boolean shouldFlushForCombatMode() {
      EntityLivingBase currentCombatTarget = this.getCurrentCombatTarget();
      boolean flag;
      if (this.modes.getSelectedValue().equals("DefaultCombat")) {
         flag = this.shouldFlushDefaultCombat(currentCombatTarget);
      } else {
         if (this.modes.getSelectedValue().equals("CombatOnly")) {
            if (this.shouldFlushCombatOnly(currentCombatTarget)) {
               return true;
            }
         }

         flag = false;
      }

      return flag;
   }

   private boolean shouldFlushDefaultCombat(EntityLivingBase entity) {
      if (entity instanceof EntityPlayer) {
         if (!EntityMovementPredictor.hasInvalidPositionHistory(entity)) {
            EntityPlayer player2 = (EntityPlayer)entity;
            float collisionBorderSize = player2.getCollisionBorderSize();
            AxisAlignedBB box = player2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            Vec3 vec = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
            Vec3 nearestPointOnEntity = RotationUtil.getNearestPointOnEntity(player2);
            double[] doubles = PlayerUtil.predictPlayerPosition(0, mc.thePlayer);
            Vec3 vec2 = new Vec3(doubles[0], doubles[1] + (double)mc.thePlayer.getEyeHeight(), doubles[2]);
            if (vec2.distanceTo(RotationUtil.clampPointToBox(vec2, box)) < vec.distanceTo(nearestPointOnEntity) && mc.thePlayer.getDistanceToEntityBox(player2) > 3.0F) {
               if (!EntityMovementPredictor.hasInvalidPositionHistory(player2)) {
                  for (int i = this.lagTicks.getLastIntValue(); i > 0; i--) {
                     double[] doubles2 = PlayerUtil.predictPlayerPosition(i - 1, mc.thePlayer);
                     Vec3 vec4 = EntityMovementPredictor.predictConstantHorizontalPosition(player2, i);
                     Vec3 vec5 = new Vec3(doubles2[0], doubles2[1] + (double)mc.thePlayer.getEyeHeight(), doubles2[2]);
                     float f = player2.width / 2.0F;
                     AxisAlignedBB box2 = new AxisAlignedBB(
                           vec4.xCoord - (double)f,
                           vec4.yCoord,
                           vec4.zCoord - (double)f,
                           vec4.xCoord + (double)f,
                           vec4.yCoord + (double)player2.height,
                           vec4.zCoord + (double)f
                        )
                        .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
                     if (vec5.distanceTo(RotationUtil.clampPointToBox(vec5, box2)) < 3.0) {
                        return false;
                     }
                  }
               }
            }

            return true;
         }
      }

      for (Entity entity2 : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity2 instanceof EntityPlayer) {
            EntityPlayer player3 = (EntityPlayer)entity2;
            if (!(player3 instanceof EntityPlayerSP) && !ClientManagers.moduleManager.midClick.isFriend(player3)) {
               if (!ClientManagers.moduleManager.antiBot.isBot(player3)
                  && !ClientManagers.moduleManager.teams.isTeammate(player3)
                  && mc.thePlayer.getPrevServerPos().distanceTo(player3.posX, player3.posY, player3.posZ) < 6.1) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean shouldFlushCombatOnly(EntityLivingBase entity) {
      if (entity instanceof EntityPlayer) {
         if (!EntityMovementPredictor.hasInvalidPositionHistory(entity)) {
            EntityPlayer player2 = (EntityPlayer)entity;
            float collisionBorderSize = player2.getCollisionBorderSize();
            AxisAlignedBB box = player2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            Vec3 vec = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
            Vec3 nearestPointOnEntity = RotationUtil.getNearestPointOnEntity(player2);
            double[] doubles = PlayerUtil.predictPlayerPosition(0, mc.thePlayer);
            Vec3 vec2 = new Vec3(doubles[0], doubles[1] + (double)mc.thePlayer.getEyeHeight(), doubles[2]);
            if (vec2.distanceTo(RotationUtil.clampPointToBox(vec2, box)) < vec.distanceTo(nearestPointOnEntity) && mc.thePlayer.getDistanceToEntityBox(player2) > 3.0F) {
               for (int i = this.lagTicks.getLastIntValue(); i > 0; i--) {
                  double[] doubles2 = PlayerUtil.predictPlayerPosition(i - 1, mc.thePlayer);
                  Vec3 vec4 = EntityMovementPredictor.predictConstantHorizontalPosition(player2, i);
                  Vec3 vec5 = new Vec3(doubles2[0], doubles2[1] + (double)mc.thePlayer.getEyeHeight(), doubles2[2]);
                  float f = player2.width / 2.0F;
                  AxisAlignedBB box2 = new AxisAlignedBB(
                        vec4.xCoord - (double)f,
                        vec4.yCoord,
                        vec4.zCoord - (double)f,
                        vec4.xCoord + (double)f,
                        vec4.yCoord + (double)player2.height,
                        vec4.zCoord + (double)f
                     )
                     .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
                  if (vec5.distanceTo(RotationUtil.clampPointToBox(vec5, box2)) < 3.0) {
                     return false;
                  }
               }
            }

            return true;
         }
      }

      return true;
   }

   private EntityLivingBase getCurrentCombatTarget() {
      return CombatTargetUtil.getActiveTarget();
   }

   private boolean isValidEspTarget(Entity entity) {
      return this.targetSelection.getTargetFilter().matches(entity);
   }

   private boolean isPlayerModelEspMode(StringValue booleansSetting) {
      return booleansSetting.getSelectedValue().equals("PlayerModel") || booleansSetting.getSelectedValue().equals("PlayerModelArmor");
   }

   public void renderTargetModel(EventRenderEntity event, EntityLivingBase entity, boolean flag) {
      double d = entity.prevInterpolatedServerPos.xCoord + (entity.interpolatedServerPos.xCoord - entity.prevInterpolatedServerPos.xCoord) * (double)mc.getTimer().renderPartialTicks;
      double d2 = entity.prevInterpolatedServerPos.yCoord + (entity.interpolatedServerPos.yCoord - entity.prevInterpolatedServerPos.yCoord) * (double)mc.getTimer().renderPartialTicks;
      double d3 = entity.prevInterpolatedServerPos.zCoord + (entity.interpolatedServerPos.zCoord - entity.prevInterpolatedServerPos.zCoord) * (double)mc.getTimer().renderPartialTicks;
      BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
      RendererLivingEntity renderer = event.getRenderer();
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderTargetModelPass(event, entity, renderer, d, d2, d3, flag, shaderRenderPass));
   }

   public void renderPlayerModel(EventRenderEntity event, Vec3 vec, boolean flag) {
      BlurShadowManager.enableOneShotPass(this.player.isBlurActive(), this.player.isShadowActive());
      RendererLivingEntity renderer = event.getRenderer();
      BlurShadowManager.submitRenderTask(false, shaderRenderPass -> renderPlayerModelPass(event, renderer, vec, flag, shaderRenderPass));
   }

   private void rerollLagDurations() {
      this.lagTicks.nextValue();
      this.lagBreakTicks.nextValue();
      this.movePacketCounter = 0;
   }

   private void flushAndResetPulse() {
      this.delayingPackets = false;
      this.movePacketCounter = 0;
      this.rerollLagDurations();
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
      RenderUtil.drawEntityEspBox(mc.thePlayer, this.classicColor2, (float)this.lineWidthMe.getValue(), (short)255, false);
   }

   private void renderClassicTargetBoxes(ShaderRenderPass shaderRenderPass) {

      for (EntityLivingBase entity : (Iterable<EntityLivingBase>)(this.renderedTargets)) {
         if (!(entity instanceof EntityPlayerSP)) {
            RenderUtil.drawEntityEspBox(entity, this.classicColor, (float)this.lineWidthTarget.getValue(), (short)255, this.smoothRendering.getValue());
            BlurShadowManager.enableOneShotPass(this.target.isBlurActive(), this.target.isShadowActive());
         }
      }
   }

   private void checkNearbySplashPotion(Entity entity) {
      double distanceToEntity = (double)mc.thePlayer.getDistanceToEntity(entity);
      double distance2DToEntity = (double)mc.thePlayer.getDistance2DToEntity(entity);
      if (distanceToEntity < 2.0 && distance2DToEntity < 1.0) {
         this.flushAndResetPulse();
      }
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

   private boolean isTargetClassicColorVisible() {
      return this.targetESP.getSelectedValue().equals("Classic");
   }

   private boolean isCombatModesVisible() {
      return this.packetDirection.getSelectedValue().equals("OnlyOutgoing");
   }


}
