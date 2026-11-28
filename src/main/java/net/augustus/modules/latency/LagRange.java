package net.augustus.modules.latency;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.misc.Debug;
import net.augustus.events.EventPacket;
import net.augustus.events.EventGameLoop;
import net.augustus.modules.latency.helper.LagRangeState;
import net.augustus.events.listeners.RenderListener;
import net.augustus.utils.simulation.TrajectoryPredictionUtil;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.utils.combat.EntityTargetFilter;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.listeners.GameLoopListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.rotation.EntityBoundsUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.combat.EntityTargetSelector;
import net.augustus.utils.combat.CombatTargetUtil;
import net.augustus.events.EventMoveInput;
import net.augustus.render.RenderUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class LagRange extends Module implements GameLoopListener, PacketListener, MoveInputListener, RenderListener, TickListener, LoadWorldListener {
   public final DoubleValue predictTicks;
   public final DoubleValue ticks;
   private final BooleanValue correct;
   private final BooleanValue onlyOnGround;
   private final BooleanValue notInLiquid;
   private final BooleanValue stop;
   private final BooleanValue sTap;
   private final DoubleValue delayAfterAttack;
   private final DoubleValue delayBetweenLags;
   private final DoubleValue stopEarly;
   private final SettingGroup targets;
   private final BooleanValue onlyWhileKillAura;
   private final TargetSelectionSettings targetSelection;
   private final DoubleValue preAimRange;
   private final SettingGroup debug;
   private final BooleanValue debugEnabled;
   private final BooleanValue onlyInThirdPerson;
   private final DoubleValue timeDebugGetsDisplayed;
   private long debugDisplayStartMs;
   private AxisAlignedBB debugStartBounds;
   private AxisAlignedBB debugEndBounds;
   private Vec3 debugStartPos;
   private Vec3 debugEndPos;
   private int debugTotalTicks;
   private int debugElapsedTicks;
   private int charge1Ticks;
   private boolean velocityReceived;
   private boolean inHitRangeAfterCharge;
   private LagRangeState lagRangeState;
   private int unusedTickCounter;
   private int charge2Ticks;
   private Vec3 cachedTargetPos;
   private long lastAttackTimeMs;
   private long lastLagTimeMs;

   public LagRange() {
       super("LagRange", "LagDistanz", new Color(23, 233, 123), Categorys.LATENCY);
      this.predictTicks = new DoubleValue(this, "PredictTicks", 5.0, 1.0, 7.0, 0);
      this.ticks = new DoubleValue(this, "Ticks", 3.0, 1.0, 7.0, 0);
      this.correct = new BooleanValue(this, "Correct", false);
      this.onlyOnGround = new BooleanValue(this, "OnlyOnGround", false);
      this.notInLiquid = new BooleanValue(this, "NotInLiquid", false);
      this.stop = new BooleanValue(this, "Stop", false);
      BooleanValue booleanValue = new BooleanValue(this, "STap", false);
      BooleanValue stop2 = this.stop;
      BooleanValue booleanValue2 = stop2;
      this.sTap = (BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue());
      this.delayAfterAttack = new DoubleValue(this, "Delay after attack", 0.0, 0.0, 10000.0, 0);
      this.delayBetweenLags = new DoubleValue(this, "Delay between lags", 0.0, 0.0, 10000.0, 0);
      this.stopEarly = new DoubleValue(this, "Stop early", 0.0, 0.0, 3.0, 0);
      this.targets = new SettingGroup(this, "Targets");
      this.onlyWhileKillAura = new BooleanValue(this, "OnlyWhileKillAura", true, this.targets);
      TargetSelectionSettings targetSelectionSettings = (new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter(), this.targets)).withSorting();
      SettingVisibilityCondition condition2 = () -> {
         return !this.onlyWhileKillAura.getValue();
      };
      this.targetSelection = targetSelectionSettings.withVisibility(condition2);
      this.preAimRange = new DoubleValue(this, "PreAimRange", 4.0, 0.0, 15.0, 1, this.targets).visibleWhen(() -> this.isPreAimRangeVisible());
      this.debug = new SettingGroup(this, "Debug");
      this.debugEnabled = new BooleanValue(this, "Debug", false, this.debug);
      BooleanValue booleanValue3 = new BooleanValue(this, "OnlyInThirdPerson", true, this.debug);
      stop2 = this.debugEnabled;
      BooleanValue booleanValue4 = stop2;
      this.onlyInThirdPerson = (BooleanValue)booleanValue3.withVisibility(() -> booleanValue4.getValue());
      DoubleValue timeDebugGetsDisplayed2 = new DoubleValue(this, "Time debug gets displayed", 1.0, 0.1, 10.0, 1, this.debug);
      stop2 = this.debugEnabled;
      BooleanValue booleanValue5 = stop2;
      this.timeDebugGetsDisplayed = timeDebugGetsDisplayed2.visibleWhen(() -> booleanValue5.getValue());
      this.debugDisplayStartMs = 0L;
      this.debugTotalTicks = 0;
      this.debugElapsedTicks = 0;
      this.charge1Ticks = 0;
      this.velocityReceived = true;
      this.inHitRangeAfterCharge = false;
      this.lagRangeState = LagRangeState.NOTHING;
      this.unusedTickCounter = 0;
      this.cachedTargetPos = null;
      this.lastAttackTimeMs = 0L;
      this.lastLagTimeMs = 0L;
   }

   @Override
   public void onEnable() {
      this.lagRangeState = LagRangeState.NOTHING;
      this.charge1Ticks = 0;
      this.charge2Ticks = 0;
      this.unusedTickCounter = 0;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(16);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.lagRangeState == LagRangeState.NOTHING && this.inHitRangeAfterCharge && this.stop.getValue() && !this.velocityReceived && this.sTap.getValue()) {
         event.setMoveForward(-1.0F);
         event.setMoveStrafe(0.0F);
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (event.getPacket() instanceof C02PacketUseEntity) {
         this.lastAttackTimeMs = System.currentTimeMillis();
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity packet2 = (S12PacketEntityVelocity)packet;
         if (mc.thePlayer != null && packet2.getEntityID() == mc.thePlayer.getEntityId()) {
            this.velocityReceived = true;
         }
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.targetSelection.setCurrentTarget((Entity)null);
   }

   @Override
   public void onLateTick() {
      if (this.debugEnabled.getValue()) {
         if (this.debugTotalTicks > 0) {
            this.debugElapsedTicks++;
            if (this.debugTotalTicks == this.debugElapsedTicks) {
               this.debugDisplayStartMs = System.currentTimeMillis();
               this.debugEndBounds = mc.thePlayer.getEntityBoundingBox();
               this.debugEndPos = mc.thePlayer.getInterpolatedPos(1.0F);
               this.sendChatMessage("Teleported " + MathStatisticsUtil.roundToDecimalPlaces(this.debugStartPos.distanceTo(this.debugEndPos), 3) + " blocks in " + this.debugTotalTicks + " ticks");
               this.debugTotalTicks = 0;
               this.debugElapsedTicks = 0;
            }
         }
      } else {
         this.debugTotalTicks = 0;
         this.debugElapsedTicks = 0;
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.debugEnabled.getValue()
         && (double)(System.currentTimeMillis() - this.debugDisplayStartMs) < this.timeDebugGetsDisplayed.getValue() * 1000.0
         && (mc.gameSettings.thirdPersonView != 0 || !this.onlyInThirdPerson.getValue())) {
         RenderManager renderManager = mc.getRenderManager();
         GL11.glPushMatrix();
         RenderUtil.begin3DRender();
         GL11.glTranslated(-renderManager.getRenderPosX(), -renderManager.getRenderPosY(), -renderManager.getRenderPosZ());
         GlStateManager.color(1.0F, 0.0F, 0.0F, 1.0F);
         RenderUtil.drawOutlinedBoundingBox(this.debugStartBounds);
         GlStateManager.color(0.0F, 0.0F, 1.0F, 1.0F);
         RenderUtil.drawOutlinedBoundingBox(this.debugEndBounds);
         RenderUtil.end3DRender();
         GL11.glPopMatrix();
      }
   }

   @Override
   public void onGameLoop(EventGameLoop event) {
      event.setAllowSkipRender(this.correct.getValue());
      int elapsedTicks = event.getElapsedTicks();
      EntityLivingBase entity = this.findTarget();
      if (this.lagRangeState == LagRangeState.CHARGE2) {
         event.setElapsedTicks((byte)0);
         event.setRenderPartialTicks(0.0F);
         event.setSkipRender(true);
         this.lastLagTimeMs = System.currentTimeMillis();
      }

      boolean flag2 = false;
      if (elapsedTicks >= 1) {
         if (this.lagRangeState == LagRangeState.NOTHING) {
            if (this.inHitRangeAfterCharge) {
               this.inHitRangeAfterCharge = false;
            }

            if (elapsedTicks == 1 && (double)(System.currentTimeMillis() - this.lastLagTimeMs) > this.delayBetweenLags.getValue()) {
               if (this.shouldStartCharge((int)this.predictTicks.getValue(), entity)) {
                  this.charge1Ticks = 0;
                  this.lagRangeState = LagRangeState.CHARGE1;
                  this.velocityReceived = false;
               }
            }
         }

         if (this.lagRangeState == LagRangeState.CHARGE1) {
            this.charge1Ticks += elapsedTicks;
            boolean shouldReleaseEarly = this.shouldReleaseEarly(this.charge1Ticks, entity);
            if ((double)this.charge1Ticks >= this.ticks.getValue() || shouldReleaseEarly || this.velocityReceived) {
               this.inHitRangeAfterCharge = false;
               if (this.charge1Ticks >= 1 && !this.velocityReceived) {
                  this.inHitRangeAfterCharge = this.canHitTargetAfterTicks(entity, this.charge1Ticks);
               }

               this.lagRangeState = LagRangeState.TIMER1;
            }
         }

         if (this.lagRangeState == LagRangeState.CHARGE2) {
            this.charge2Ticks += elapsedTicks;
            if (this.charge2Ticks >= this.charge1Ticks || this.velocityReceived) {
               if (this.velocityReceived && ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.lagRangeDebug.getValue()) {
                  String str = "Abort " + this.charge2Ticks;
                  ClientManagers.moduleManager.debug.sendChatMessage(str);
               }

               this.lagRangeState = LagRangeState.TIMER2;
            }
         }

         if (this.lagRangeState == LagRangeState.TIMER2) {
            event.setElapsedTicks(this.charge2Ticks);
            this.charge2Ticks = 0;
            this.lagRangeState = LagRangeState.NOTHING;
            this.charge1Ticks = 0;
            flag2 = true;
         }

         if (this.lagRangeState == LagRangeState.TIMER1) {
            event.setElapsedTicks(this.charge1Ticks);
            if (this.inHitRangeAfterCharge && this.stop.getValue()) {
               this.lagRangeState = LagRangeState.CHARGE2;
               if (ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.lagRangeDebug.getValue()) {
                  String str2 = "TimerRange " + mc.thePlayer.ticksExisted;
                  ClientManagers.moduleManager.debug.sendChatMessage(str2);
               }

               this.charge2Ticks = 0;
            } else {
               this.lagRangeState = LagRangeState.NOTHING;
               this.charge1Ticks = 0;
            }

            flag2 = true;
         }
      }

      if (this.lagRangeState == LagRangeState.CHARGE1) {
         event.setElapsedTicks((byte)0);
         event.setRenderPartialTicks(0.0F);
         event.setSkipRender(true);
         this.lastLagTimeMs = System.currentTimeMillis();
      }

      if (flag2 && event.getElapsedTicks() > 1 && this.debugEnabled.getValue()) {
         this.debugTotalTicks = event.getElapsedTicks();
         this.debugStartBounds = mc.thePlayer.getEntityBoundingBox();
         this.debugStartPos = mc.thePlayer.getInterpolatedPos(1.0F);
      }

      this.targetSelection.setCurrentTarget(entity);
   }

   private EntityLivingBase findTarget() {
      EntityLivingBase activeTarget = CombatTargetUtil.getActiveTarget();
      if (!this.onlyWhileKillAura.getValue() && activeTarget == null) {
         EntityTargetSelector entityTargetSelector = EntityTargetSelector.of(mc.theWorld.loadedEntityList);
         entityTargetSelector = entityTargetSelector.withTargetFilter(this.targetSelection.getTargetFilter()).filterFocus();
         entityTargetSelector = entityTargetSelector.maxDistance(this.preAimRange.getValue());
         ToDoubleFunction toDoubleFunction = this.targetSelection.createTargetComparatorKey(ClientManagers.rotationController.getPreviousRotation(), ClientManagers.moduleManager.range.isCombatRangeActive() ? ClientManagers.moduleManager.range.getCurrentRange() : 3.0);
         Optional optional = entityTargetSelector.sortBy(toDoubleFunction).findBest();
         return optional.isPresent() && optional.get() instanceof EntityLivingBase ? (EntityLivingBase)optional.get() : null;
      } else {
         return activeTarget;
      }
   }

   private boolean shouldReleaseEarly(int n, EntityLivingBase entity) {
      if (entity != null
         && (!ClientManagers.moduleManager.killAura.isToggled() || !ClientManagers.moduleManager.killAura.rangeMode.getSelectedValue().equals("Grim") || !ClientManagers.moduleManager.killAura.grimRangeActive)
         && entity.ticksExisted >= 20) {
         n += (int)this.stopEarly.getValue();
         double[] doubles = PlayerUtil.predictPlayerTowardsTarget(n - 1, mc.thePlayer, entity);
         Vec3 vec = new Vec3(doubles[3], doubles[4] + (double)mc.thePlayer.getEyeHeight(), doubles[5]);
         float collisionBorderSize = entity.getCollisionBorderSize();
         float f = entity.width / 2.0F;
         double posX = entity.posX;
         double posY = entity.posY;
         double posZ = entity.posZ;
         int realPosRotationIncrements = entity.realPosRotationIncrements;

         for (int i = 0; i < n && realPosRotationIncrements > 0; i++) {
            posX += (entity.realPosX - posX) / (double)realPosRotationIncrements;
            posY += (entity.realPosY - posY) / (double)realPosRotationIncrements;
            posZ += (entity.realPosZ - posZ) / (double)realPosRotationIncrements;
            realPosRotationIncrements--;
         }

         AxisAlignedBB box = new AxisAlignedBB(
               posX - (double)f, posY, posZ - (double)f, posX + (double)f, posY + (double)entity.height, posZ + (double)f
            )
            .expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
         Vec3 vec2 = new Vec3(
            MathHelper.clamp_double(vec.xCoord, box.minX, box.maxX),
            MathHelper.clamp_double(vec.yCoord, box.minY, box.maxY),
            MathHelper.clamp_double(vec.zCoord, box.minZ, box.maxZ)
         );
         if (ClientManagers.moduleManager.debug.isToggled() && ClientManagers.moduleManager.debug.lagRangeDebug.getValue()) {
            Debug debug3 = ClientManagers.moduleManager.debug;
            String str = n
               + 1
               + "  "
               + vec.distanceTo(vec2)
               + " "
               + mc.thePlayer.getDistance(entity.realPosX, entity.realPosY, entity.realPosZ)
               + " "
               + (vec.distanceTo(vec2) < 3.0);
            debug3.sendChatMessage(str);
         }

         return vec.distanceTo(vec2) <= 3.0;
      } else {
         return true;
      }
   }

   private boolean shouldStartCharge(int n, EntityLivingBase entity) {
      if ((!this.onlyOnGround.getValue() || mc.thePlayer.onGround)
         && (!this.notInLiquid.getValue() || !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava())
         && entity != null
         && !(mc.thePlayer.getDistanceToEntityBox(entity) < 3.0F)
         && (!ClientManagers.moduleManager.killAura.isToggled() || !ClientManagers.moduleManager.killAura.rangeMode.getSelectedValue().equals("Grim") || !ClientManagers.moduleManager.killAura.grimRangeActive)
         && entity.ticksExisted >= 20) {
         for (int i = n; i >= 1; i--) {
            if (this.canHitTargetAfterTicks(entity, n)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean canHitTargetAfterTicks(EntityLivingBase entity, int n) {
      if ((double)(System.currentTimeMillis() - this.lastAttackTimeMs) < this.delayAfterAttack.getValue()) {
         return false;
      } else if ((!this.onlyOnGround.getValue() || mc.thePlayer.onGround)
         && (!this.notInLiquid.getValue() || !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava())
         && (!ClientManagers.moduleManager.killAura.isToggled() || !ClientManagers.moduleManager.killAura.rangeMode.getSelectedValue().equals("Grim") || !ClientManagers.moduleManager.killAura.grimRangeActive)
         && entity.ticksExisted >= 20) {
         if (mc.thePlayer.getPositionEyes(1.0F).distanceTo(RotationUtil.getInterpolatedNearestPoint(entity, 1.0F)) <= 3.0) {
            return false;
         } else {
            int n2 = mc.thePlayer.ticksExisted + n;
            int ticksExisted = mc.thePlayer.ticksExisted;
            ArrayList list = TrajectoryPredictionUtil.predictTargetPositions(mc.thePlayer, entity, ticksExisted, n2, true);
            AxisAlignedBB box = EntityBoundsUtil.createPlayerBoundingBox(mc.thePlayer.getInterpolatedPos(1.0F), true);

            for (Vec3 vec2 : (Iterable<Vec3>)(list)) {
               Vec3 vec3 = vec2.addVector(0.0, (double)entity.getEyeHeight(), 0.0);
               if (RotationUtil.clampPointToBox(vec3, box).distanceTo(vec3) <= 3.0) {
                  return false;
               }
            }

            Vec3 clampedPosition = TrajectoryPredictionUtil.getClampedPosition(list, 999999);
            int n5 = mc.thePlayer.ticksExisted + n;
            int ticksExisted2 = mc.thePlayer.ticksExisted;
            int ticksExisted3 = mc.thePlayer.ticksExisted;
            Vec3 vec4 = TrajectoryPredictionUtil.getClampedPosition(TrajectoryPredictionUtil.predictPlayerPositions(mc.thePlayer, ticksExisted3, ticksExisted2, (byte)0, n5), (short)9999).addVector(0.0, (double)mc.thePlayer.getEyeHeight(), 0.0);
            return vec4.distanceTo(RotationUtil.clampPointToBox(vec4, EntityBoundsUtil.createPlayerBoundingBox(clampedPosition, true))) <= 3.0;
         }
      } else {
         return false;
      }
   }

   private boolean isPreAimRangeVisible() {
      return !this.onlyWhileKillAura.getValue();
   }

}
